import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * @author Alexander Diachenko.
 */
public class Makeup {

    private final String DISCOUNT_PRICE_SPAN = "div.product-item__price-wrap > span.product-item__discount-price > span.rus";
    private final String PRICE_SPAN = "div.product-item__price-wrap > span.product-item__price > span.rus";
    private final String ITEM_STATUS_DIV = "div.product-item__code-wrap > div.product-item__status";
    private final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";

    public String getPrice(String URL) {
        try {
            Document document = Jsoup.connect(URL).userAgent(USER_AGENT).get();
            Element discountPriceSpan = document.select(DISCOUNT_PRICE_SPAN).first();
            Element itemStatusDiv = document.select(ITEM_STATUS_DIV).first();
            if(itemStatusDiv.text().equals("Нет в наличии")) {
                return itemStatusDiv.text();
            }
            if (discountPriceSpan != null) {
                return discountPriceSpan.text();
            }
            Element priceSpan = document.select(PRICE_SPAN).first();
            return priceSpan.text();
        } catch (IOException e) {
            e.printStackTrace();
            return "Страница не найдена";
        }
    }
}
