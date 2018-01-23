import excel.Excel;
import excel.ExcelImpl;
import magazine.Magazine;
import magazine.Makeup;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import property.AppProperty;

import java.io.IOException;
import java.util.List;

/**
 * @author Alexander Diachenko.
 */
public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException, InterruptedException {
        setSystemProperty();
        WebDriver driver = new ChromeDriver();
        Magazine makeup = new Makeup(driver);
        Excel excel = new ExcelImpl();
        List<List<Object>> table = excel.read(AppProperty.getProperty("file.path"));

        for (List<Object> row : table) {
            final String url = String.valueOf(row.get(Integer.parseInt(AppProperty.getProperty("link.column")) - 1));
            if (!url.isEmpty()) {
                driver.get("https://www.google.com.ua/");
                final String priceColumn = AppProperty.getProperty("price.column");
                final int column = Integer.parseInt(priceColumn) - 1;
                insert(row, column, makeup.getPrice(url));
            }
        }
        closeConnection(driver);
        excel.write(table, AppProperty.getProperty("save.file.path"));
    }

    private static void insert(List<Object> row, int column, String price) {
        if (row.size() > column) {
            row.set(column, price);
        } else {
            row.add(column, price);
        }
    }

    private static void setSystemProperty() throws IOException {
        System.setProperty("webdriver.chrome.driver", AppProperty.getProperty("chrome.driver.path"));
    }

    private static void closeConnection(WebDriver driver) {
        driver.close();
        driver.quit();
    }
}