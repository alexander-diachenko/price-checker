package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Rozetka extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) {
        Elements prices = document.getElementsByClass("detail-price-uah");
        return prices.stream()
                .findFirst()
                .map(price -> StringUtil.formatPrice(price.text()))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return "rozetka.com.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements buyButtons = document.getElementsByClass("btn-link-i");
        for (Element buyButton : buyButtons) {
            if ("Купить".equalsIgnoreCase(buyButton.text())) {
                return true;
            }
        }
        return false;
    }
}
