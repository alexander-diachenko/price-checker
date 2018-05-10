package controller;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import excel.Excel;
import excel.ExcelImpl;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import magazine.Magazine;
import magazine.Makeup;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import url.AppProperty;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Alexander Diachenko.
 */
public class MainService extends Service<Void> {

    private final static Logger logger = Logger.getLogger(MainService.class);

    private Properties properties;
    private WebDriver driver;
    private Excel excel;
    private List<Magazine> magazines;
    private ProgressIndicator progressIndicator;

    public MainService(ProgressIndicator progressIndicator) {
        this.progressIndicator = progressIndicator;
        properties = AppProperty.getProperty();
        driver = getDriver(BrowserVersion.CHROME, true);
        excel = new ExcelImpl();
        magazines = getMagazines(driver, properties);
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                progressIndicator.setProgress(0.01);
                try {
                    List<List<Object>> table = excel.read(properties.getProperty("file.path"));
                    final String priceColumn = properties.getProperty("price.column");
                    final String linkColumn = properties.getProperty("link.column");
                    for (int index = 0, tableSize = table.size(); index < tableSize; index++) {
                        List<Object> row = table.get(index);
                        final String url = String.valueOf(row.get(Integer.parseInt(linkColumn) - 1));
                        for (Magazine magazine : magazines) {
                            if (!url.isEmpty() && magazine.isThisWebsite(url)) {
                                int column = Integer.parseInt(priceColumn) - 1;
                                String price = magazine.getPrice(url);
                                System.out.println((index + 1) + ") " + url + " -> " + price);
                                insert(row, column, price);
                            }
                        }
                        progressIndicator.setProgress(index / 100);
                    }
                    excel.write(table, properties.getProperty("save.file.path"));
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                } finally {
                    driver.close();
                }
                return null;
            }
        };
    }

    private static void insert(List<Object> row, int column, String price) {
        if (row.size() > column) {
            row.set(column, price);
        } else {
            row.add(column, price);
        }
    }

    private static List<Magazine> getMagazines(WebDriver driver, Properties properties) {
        List<Magazine> magazines = new ArrayList<>();
        Magazine makeup = new Makeup(driver, properties);
        magazines.add(makeup);
        return magazines;
    }

    private static WebDriver getDriver(BrowserVersion browserVersion, boolean javascript) {
        return new HtmlUnitDriver(browserVersion, javascript) {
            @Override
            protected WebClient newWebClient(BrowserVersion version) {
                WebClient webClient = super.newWebClient(version);
                webClient.getOptions().setThrowExceptionOnScriptError(false);
                return webClient;
            }
        };
    }
}
