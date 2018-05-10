package magazine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import url.UrlUtils;

import java.net.MalformedURLException;
import java.util.Properties;

/**
 * @author Alexander Diachenko.
 */
public class Makeup implements Magazine {

    private Properties properties;
    private WebDriver driver;

    public Makeup(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
    }

    @Override
    public String getPrice(String url) {
        Document document = getDocument(url);
        if (!isCorrectPage(document)) {
            return "Страница не найдена";
        }
        return getValue(document);
    }

    @Override
    public boolean isCorrectPage(Document document) {
        return !document.select("h1.page-header").text().equals("Страница не найдена");
    }

    @Override
    public boolean isThisWebsite(String url) {
        try {
            return UrlUtils.getDomainName(url).equals("makeup.com.ua");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Document getDocument(String url) throws WebDriverException {
        driver.get(url);
        driver.navigate().refresh();
        final String page = driver.getPageSource();
        return Jsoup.parse(page);
    }

    private String getValue(Document document) {
        if (!isAvailable(document)) {
            return "Нет в наличии";
        } else if (isDiscount(document)) {
            return document.select(properties.getProperty("discount.price.span")).first().text();
        } else
            return document.select(properties.getProperty("normal.price.span")).first().text();
    }

    @Override
    public boolean isDiscount(Document document) {
        Element discountPrice = document.select(properties.getProperty("discount.price.span")).first();
        return discountPrice != null;
    }

    @Override
    public boolean isAvailable(Document document) {
        Element itemStatus = document.select(properties.getProperty("item.status.div")).first();
        return itemStatus.text().equals("Есть в наличии!");
    }
}
