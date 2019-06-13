package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Rozetka extends AbstractMagazine {

    @Override
    protected String getValue(Document document) {
        if (!isAvailable(document)) {
            return OUT_OF_STOCK;
        }
        Elements prices = document.getElementsByClass("detail-price-uah");
        for (Element price : prices) {
            return StringUtil.formatPrice(price.text());
        }
        return null;
    }

    @Override
    protected String getSiteDomain() {
        return "rozetka.com.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements buyButtons = document.getElementsByClass("btn-link-i");
        for (Element buyButton : buyButtons) {
            return "Купить".equalsIgnoreCase(buyButton.text());
        }
        return false;
    }
}
