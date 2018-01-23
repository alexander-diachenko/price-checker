package magazine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import property.AppProperty;
import url.UrlUtils;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * @author Alexander Diachenko.
 */
public class Makeup implements Magazine {

    private Document document;
    private WebDriver driver;

    public Makeup(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public String getPrice(String url) throws IOException {
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

    private String getValue() throws IOException {
        if (!isAvailable(document)) {
            return "Нет в наличии";
        } else if (isDiscount(document)) {
            return document.select(AppProperty.getProperty("discount.price.span")).first().text();
        } else
            return document.select(AppProperty.getProperty("normal.price.span")).first().text();
    }

    @Override
    public boolean isDiscount(Element document) throws IOException {
        Element discountPriceSpan = document.select(AppProperty.getProperty("discount.price.span")).first();
        return discountPriceSpan != null;
    }

    @Override
    public boolean isAvailable(Element document) throws IOException {
        Element itemStatusDiv = document.select(AppProperty.getProperty("item.status.div")).first();
        return itemStatusDiv.text().equals("Есть в наличии!");
    }
}
