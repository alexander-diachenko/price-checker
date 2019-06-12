package checker.service;

import checker.model.excel.Excel;
import checker.model.excel.ExcelImpl;
import checker.model.magazine.*;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;

import java.util.*;

/**
 * @author Alexander Diachenko.
 */
public class MainService extends Service<Void> {

    private String filePath;
    private Integer urlColumn;
    private Integer insertColumn;
    private String savedFilePath;
    private ProgressIndicator progressIndicator;

    public MainService(String filePath, Integer urlColumn, Integer insertColumn, String savedFilePath, ProgressIndicator progressIndicator) {
        this.filePath = filePath;
        this.urlColumn = urlColumn;
        this.insertColumn = insertColumn;
        this.savedFilePath = savedFilePath;
        this.progressIndicator = progressIndicator;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Excel excel = new ExcelImpl();
                List<Magazine> magazines = getMagazines();
                List<List<Object>> table = excel.read(filePath);
                for (int index = 0; index < table.size(); index++) {
                    List<Object> row = table.get(index);
                    if (row.size() < urlColumn) {
                        continue;
                    }
                    String url = String.valueOf(row.get(urlColumn - 1));
                    for (Magazine magazine : magazines) {
                        if (magazine.isThisWebsite(url)) {
                            String price = magazine.getPrice(url);
                            insert(row, insertColumn - 1, price);
                        }
                    }
                    int finalIndex = index;
                    Platform.runLater(() -> progressIndicator.setProgress(0.99 / table.size() * (finalIndex + 1)));
                }
                excel.write(table, savedFilePath);
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
        Magazine korea = new Korea();
        Magazine roseRoseShop = new RoseRoseShop();
        BeautyNetKorea beautyNetKorea = new BeautyNetKorea();
        NowZenith nowZenith = new NowZenith();
        magazines.add(makeup);
        magazines.add(korea);
        magazines.add(roseRoseShop);
        magazines.add(beautyNetKorea);
        magazines.add(nowZenith);
        return magazines;
    }
}
