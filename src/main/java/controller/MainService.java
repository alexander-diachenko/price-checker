package controller;

import excel.Excel;
import excel.ExcelImpl;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;
import magazine.Magazine;
import magazine.Makeup;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * @author Alexander Diachenko.
 */
public class MainService extends Service<Void> {

    private final static Logger logger = Logger.getLogger(MainService.class);

    private String filePath;
    private Integer urlColumn;
    private Integer insertColumn;
    private String saveDirectoryPath;
    private ProgressIndicator progressIndicator;

    public MainService(String filePath, Integer urlColumn, Integer insertColumn, String saveDirectoryPath, ProgressIndicator progressIndicator) {
        this.filePath = filePath;
        this.urlColumn = urlColumn;
        this.insertColumn = insertColumn;
        this.saveDirectoryPath = saveDirectoryPath;
        this.progressIndicator = progressIndicator;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Excel excel = new ExcelImpl();
                try {
                    List<Magazine> magazines = getMagazines();
                    List<List<Object>> table = excel.read(filePath);
                    for (int index = 0; index < table.size(); index++) {
                        List<Object> row = table.get(index);
                        if (row.size() < urlColumn) {
                            continue;
                        }
                        String url = String.valueOf(row.get(urlColumn - 1));
                        for (Magazine magazine : magazines) {
                            if (!url.isEmpty() && magazine.isThisWebsite(url)) {
                                String price = magazine.getPrice(url);
                                System.out.println((index + 1) + ") " + url + " -> " + price);
                                insert(row, insertColumn - 1, price);
                            }
                        }
                        int finalIndex = index;
                        Platform.runLater(() -> progressIndicator.setProgress(0.99 / table.size() * (finalIndex + 1)));
                    }
                    excel.write(table, saveDirectoryPath + "\\prices.xlsx");
                } catch (Exception exception) {
                    logger.error(exception.getMessage(), exception);
                    throw exception;
                }
                return null;
            }
        };
    }

    private void insert(List<Object> row, int column, String price) {
        while (row.size() <= column) {
            row.add("");
        }
        row.set(column, price);
    }

    private static List<Magazine> getMagazines() {
        List<Magazine> magazines = new ArrayList<>();
        Magazine makeup = new Makeup();
        magazines.add(makeup);
        return magazines;
    }
}
