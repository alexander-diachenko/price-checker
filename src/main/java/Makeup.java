import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author Alexander Diachenko.
 */
public class Makeup {

    private final String DISCOUNT_PRICE_SPAN = "div.product-item__price-wrap > span.product-item__discount-price > span.rus";
    private final String PRICE_SPAN = "div.product-item__price-wrap > span.product-item__price > span.rus";
    private final String ITEM_STATUS_DIV = "div.product-item__code-wrap > div.product-item__status";
    private final String VOLUME_RADIO_DIV = "div.product-item__row > div.product-item__volume-radio";
    private final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
    private Document document;

    public String getPrice(String URL) {
        try {
            document = Jsoup.connect(URL).userAgent(USER_AGENT).get();
            if (hasOptions()) {
                System.out.println("has option");
            }
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

    private boolean hasOptions() {
        return document.select(VOLUME_RADIO_DIV).size() > 0;
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
