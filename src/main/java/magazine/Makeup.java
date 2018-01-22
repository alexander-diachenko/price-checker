package magazine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import url.UrlUtils;

import java.net.MalformedURLException;

/**
 * @author Alexander Diachenko.
 */
public class Makeup {

    private final String DISCOUNT_PRICE_SPAN = "div.product-item__price-wrap > span.product-item__discount-price > span.rus";
    private final String PRICE_SPAN = "div.product-item__price-wrap > span.product-item__price > span.rus";
    private final String ITEM_STATUS_DIV = "div.product-item__code-wrap > div.product-item__status";
    private Document document;
    private WebDriver driver;

    public Makeup(WebDriver driver) {
        this.driver = driver;
    }

    public String getPrice(String url) {
        if (!UrlUtils.isValid(url)) {
            return "Не правельный URL";
        }
        if (!isMakeup(url)) {
            return "Сайт не makeup";
        }
        document = getDocument(url);
        if (!isCorrectPage()) {
            return "Страница не найдена";
        }
        return getValue(PRICE_SPAN);
    }


    private boolean isCorrectPage() {
        return !document.select("h1.page-header").text().equals("Страница не найдена");
    }

    private boolean isMakeup(String url) {
        try {
            return UrlUtils.getDomainName(url).equals("makeup.com.ua");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Document getDocument(String url) throws WebDriverException {
        driver.get(url);
        final String page = driver.getPageSource();
        return Jsoup.parse(page);
    }

    private String getValue(String cssQuery) {
        if (!isAvailable(document)) {
            return "Нет в наличии";
        } else if (isDiscount(document)) {
            return document.select(DISCOUNT_PRICE_SPAN).first().text();
        } else
            return document.select(cssQuery).first().text();
    }

    private boolean isDiscount(Element document) {
        Element discountPriceSpan = document.select(DISCOUNT_PRICE_SPAN).first();
        return discountPriceSpan != null;
    }

    private boolean isAvailable(Element document) {
        Element itemStatusDiv = document.select(ITEM_STATUS_DIV).first();
        return itemStatusDiv.text().equals("Есть в наличии!");
    }
}
