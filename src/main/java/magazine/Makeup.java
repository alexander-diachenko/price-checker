package magazine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;

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

    public String getPrice(String URL) {
        try {
            document = getDocument(URL);
            if (!isAvailable(document)) {
                return "Нет в наличии";
            }
            if (isDiscount(document)) {
                return getValue(DISCOUNT_PRICE_SPAN);
            }
            return getValue(PRICE_SPAN);
        } catch (Exception e) {
            e.printStackTrace();
            return "Страница не найдена";
        }
    }

    private Document getDocument(String URL) {

        driver.get(URL);
        final String page = driver.getPageSource();
        return Jsoup.parse(page);
    }

    private String getValue(String cssQuery) {
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
