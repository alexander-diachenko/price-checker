import excel.Excel;
import magazine.Magazine;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Properties;

/**
 * @author Alexander Diachenko.
 */
public class Controller {

    private final static Logger logger = Logger.getLogger(Controller.class);

    private final Properties properties;
    private final WebDriver driver;
    private final Excel excel;
    private List<Magazine> magazines;

    public Controller(Properties properties, WebDriver driver, Excel excel, List<Magazine> magazines) {
        this.properties = properties;
        this.driver = driver;
        this.excel = excel;
        this.magazines = magazines;
    }

    public void run() {
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
                        System.out.println((index + 1) + ") " + url + " -> " + price + " грн");
                        insert(row, column, price);
                    }
                }
            }
            excel.write(table, properties.getProperty("save.file.path"));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            driver.close();
        }
    }

    private static void insert(List<Object> row, int column, String price) {
        if (row.size() > column) {
            row.set(column, price);
        } else {
            row.add(column, price);
        }
    }
}
