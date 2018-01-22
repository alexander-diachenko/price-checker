package magazine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import url.UrlUtils;

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
        try {
            if (!isMakeup(url)) {
                return "Сайт не makeup";
            }
            document = getDocument(url);
            if (!isCorrectPage()) {
                return "Страница не найдена";
            }
            return getValue(PRICE_SPAN);
        } catch (WebDriverException e) {
            e.printStackTrace();
            return "Не правельный URL";
        }
    }

    private boolean isCorrectPage() {
        return !document.select("h1.page-header").text().equals("Страница не найдена");
    }

    private boolean isMakeup(String url) {
        return UrlUtils.getDomainName(url).equals("makeup.com.ua");
    }

    private Document getDocument(String URL) throws WebDriverException {
        driver.get(URL);
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

    private boolean isAvailable(Element document) throws NotFoundException {
        Element itemStatusDiv = document.select(ITEM_STATUS_DIV).first();
        boolean available;
        try {
            available = itemStatusDiv.text().equals("Есть в наличии!");
        } catch (NullPointerException e) {
            throw new NotFoundException();
        }
        return available;
    }
}
