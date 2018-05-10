import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import excel.Excel;
import excel.ExcelImpl;
import magazine.Magazine;
import magazine.Makeup;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import url.AppProperty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Alexander Diachenko.
 */
public class Main {

    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hhmmss");
        System.setProperty("current.date", dateFormat.format(new Date()));
    }

    private final static Logger logger = Logger.getLogger(Main.class);
    private static WebDriver driver;

    public static void main(String[] args) {
        Properties properties = AppProperty.getProperty();
        List<Magazine> magazines = getMagazines(properties);
        Excel excel = new ExcelImpl();
        try {
            List<List<Object>> table = excel.read(properties.getProperty("file.path"));

            final String priceColumn = properties.getProperty("price.column");
            final String linkColumn = properties.getProperty("link.column");
            for (int i = 0, tableSize = table.size(); i < tableSize; i++) {
                driver.get("https://www.google.com.ua/");
                List<Object> row = table.get(i);
                final String url = String.valueOf(row.get(Integer.parseInt(linkColumn) - 1));
                for (Magazine magazine : magazines) {
                    if (magazine.isThisWebsite(url)) {
                        if (!url.isEmpty()) {
                            int column = Integer.parseInt(priceColumn) - 1;
                            String price = magazine.getPrice(url);
                            System.out.println((i + 1) + ") " + url + " -> " + price + " грн");
                            insert(row, column, price);
                        }
                    }
                }
            }

            excel.write(table, properties.getProperty("save.file.path"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            closeConnection(driver);
        }
    }

    private static List<Magazine> getMagazines(Properties properties) {
        List<Magazine> magazines = new ArrayList<>();
        driver = getDriver(BrowserVersion.CHROME, true);
        Magazine makeup = new Makeup(driver, properties);
        magazines.add(makeup);
        return magazines;
    }

    private static void insert(List<Object> row, int column, String price) {
        if (row.size() > column) {
            row.set(column, price);
        } else {
            row.add(column, price);
        }
    }

    private static void closeConnection(WebDriver driver) {
        driver.close();
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