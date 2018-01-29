import excel.Excel;
import excel.ExcelImpl;
import magazine.Magazine;
import magazine.Makeup;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import property.AppProperty;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author Alexander Diachenko.
 */
public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException, InterruptedException {
        Properties properties = AppProperty.getProperty();
        setSystemProperty(properties);
        ChromeOptions chromeOptions = new ChromeOptions();
        setChromeOption(chromeOptions);
        WebDriver driver = new ChromeDriver(chromeOptions);
        Magazine makeup = new Makeup(driver, properties);
        Excel excel = new ExcelImpl();
        List<List<Object>> table = excel.read(properties.getProperty("file.path"));

        final String priceColumn = properties.getProperty("price.column");
        final String linkColumn = properties.getProperty("link.column");
        for (List<Object> row : table) {
            final String url = String.valueOf(row.get(Integer.parseInt(linkColumn) - 1));
            if (!url.isEmpty()) {
                driver.get("https://www.google.com.ua/");
                final int column = Integer.parseInt(priceColumn) - 1;
                final String price = makeup.getPrice(url);
                System.out.println(price);
                insert(row, column, price);
            }
        }
        closeConnection(driver);
        excel.write(table, properties.getProperty("save.file.path"));
    }

    private static void setChromeOption(ChromeOptions chromeOptions) {
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");
    }

    private static void insert(List<Object> row, int column, String price) {
        if (row.size() > column) {
            row.set(column, price);
        } else {
            row.add(column, price);
        }
    }

    private static void setSystemProperty(Properties properties) {
        System.setProperty("webdriver.chrome.driver", properties.getProperty("chrome.driver.path"));
    }

    private static void closeConnection(WebDriver driver) {
        driver.close();
        driver.quit();
    }
}