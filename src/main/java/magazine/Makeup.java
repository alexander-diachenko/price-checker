package magazine;

import org.apache.log4j.Logger;
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
    private Document document;
    private WebDriver driver;

    public Makeup(WebDriver driver, Properties properties) {
        this.driver = driver;
        this.properties = properties;
    }

    @Override
    public String getPrice(String url) {
        if (!UrlUtils.isValid(url)) {
            return "Не правельный URL";
        }
        if (!isThisWebsite(url)) {
            return "Сайт не makeup";
        }
        document = getDocument(url);
        if (!isCorrectPage()) {
            return "Страница не найдена";
        }
        return getValue();
    }

    @Override
    public boolean isCorrectPage() {
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
        final String page = driver.getPageSource();
        return Jsoup.parse(page);
    }

    private String getValue() {
        if (!isAvailable(document)) {
            return "Нет в наличии";
        } else if (isDiscount(document)) {
            return document.select(properties.getProperty("discount.price.span")).first().text();
        } else
            return document.select(properties.getProperty("normal.price.span")).first().text();
    }

    @Override
    public boolean isDiscount(Element document) {
        Element discountPrice = document.select(properties.getProperty("discount.price.span")).first();
        return discountPrice != null;
    }

    @Override
    public boolean isAvailable(Element document) {
        Element itemStatus = document.select(properties.getProperty("item.status.div")).first();
        return itemStatus.text().equals("Есть в наличии!");
    }
}
