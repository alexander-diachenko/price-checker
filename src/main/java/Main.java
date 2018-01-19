import excel.Excel;
import excel.ExcelImpl;
import magazine.Makeup;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.List;

/**
 * @author Alexander Diachenko.
 */
public class Main {

    private static final String CHROME_DRIVER_PATH = "C:\\Selenium\\chromedriver.exe";
    private static final String FILE_PATH = "D:\\Downloads\\links.xlsx";
    private static final String SAVE_FILE_PATH = "D:\\Downloads\\prices.xlsx";
    private static final int LINKS_COLUMN = 0;
    private static final int PRICE_COLUMN = 1;

    public static void main(String[] args) throws IOException, InvalidFormatException {
        setSystemProperty();
        WebDriver driver = new ChromeDriver();
        Makeup makeup = new Makeup(driver);
        Excel excel = new ExcelImpl();
        List<List<Object>> table = excel.read(FILE_PATH);
        for (List<Object> row : table) {
            final String url = String.valueOf(row.get(LINKS_COLUMN));
            if (!url.isEmpty()) {
                final String price = makeup.getPrice(url);
                System.out.println(price);
                row.add(PRICE_COLUMN, price);
            }
        }
        closeConnection(driver);
        excel.write(table, SAVE_FILE_PATH);
    }

    private static void setSystemProperty() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
    }

    private static void closeConnection(WebDriver driver) {
        driver.close();
        driver.quit();
    }
}