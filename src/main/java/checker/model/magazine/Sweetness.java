package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.Optional;

/**
 * @author Alexander Diachenko
 */
public class Sweetness extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) throws IllegalStateException {
        Optional<Element> price = Optional.ofNullable(document.getElementById("our_price_display"));
        if (price.isPresent()) {
            return StringUtil.formatPrice(price.get().text());
        }
        throw new IllegalStateException();
    }

    @Override
    protected String getSiteDomain() {
        return "sweetness.com.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        return Optional.ofNullable(document.getElementById("add_to_cart")).isPresent();
    }
}
