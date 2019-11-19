package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Rozetka extends AbstractMagazine {

    private static final String DISCOUNTS = "detail-price-uah";
    private static final String SITE_DOMAIN = "rozetka.com.ua";
    private static final String BTN_LINK_I = "btn-link-i";
    private static final String BUY_TEXT = "Купить";

    @Override
    protected String getPriceFrom(Document document) {
        Elements prices = document.getElementsByClass(DISCOUNTS);
        return prices.stream()
                .findFirst()
                .map(price -> StringUtil.formatPrice(price.text()))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return SITE_DOMAIN;
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements buyButtons = document.getElementsByClass(BTN_LINK_I);
        for (Element buyButton : buyButtons) {
            if (BUY_TEXT.equalsIgnoreCase(buyButton.text())) {
                return true;
            }
        }
        return false;
    }
}
