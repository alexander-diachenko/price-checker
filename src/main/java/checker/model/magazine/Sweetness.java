package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Optional;

/**
 * @author Alexander Diachenko
 */
public class Sweetness extends AbstractMagazine {

    private static final String DISCOUNT = "our_price_display";
    private static final String SITE_DOMAIN = "sweetness.com.ua";
    private static final String ADD_TO_CART = "add_to_cart";

    @Override
    protected String getPriceFrom(Document document) {
        Optional<Element> price = Optional.ofNullable(document.getElementById(DISCOUNT));
        if (price.isPresent()) {
            return StringUtil.formatPrice(price.get().text());
        }
        throw new IllegalStateException();
    }

    @Override
    protected String getSiteDomain() {
        return SITE_DOMAIN;
    }

    @Override
    public boolean isAvailable(Document document) {
        return Optional.ofNullable(document.getElementById(ADD_TO_CART)).isPresent();
    }
}
