package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Optional;

/**
 * @author Alexander Diachenko
 */
public class RoseRoseShop extends AbstractMagazine {

    private static final String ITEMPROP = "itemprop";
    private static final String NORMAL_PRICE = "price";
    private static final String SITE_DOMAIN = "www.roseroseshop.com";
    private static final String BUTTON_CART = "button-cart";

    protected String getPriceFrom(Document document) {
        Elements prices = document.getElementsByAttributeValue(ITEMPROP, NORMAL_PRICE);
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
        return Optional.ofNullable(document.getElementById(BUTTON_CART))
                .isPresent();
    }
}
