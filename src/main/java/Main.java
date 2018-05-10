import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import excel.Excel;
import excel.ExcelImpl;
import magazine.Magazine;
import magazine.Makeup;
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

    public static void main(String[] args) {
        Properties properties = AppProperty.getProperty();
        WebDriver driver = getDriver(BrowserVersion.CHROME, true);
        List<Magazine> magazines = getMagazines(driver, properties);
        Excel excel = new ExcelImpl();
        Controller controller = new Controller(properties, driver, excel, magazines);
        controller.run();
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