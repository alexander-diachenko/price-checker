package checker.model.magazine;

import checker.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

/**
 * @author Alexander Diachenko
 */
public class RoseRoseShop extends AbstractMagazine {

    protected String getPriceFrom(Document document) {
        Elements prices = document.getElementsByAttributeValue("itemprop", "price");
        return prices.stream()
                .findFirst()
                .map(price -> StringUtil.formatPrice(price.text()))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return "www.roseroseshop.com";
    }

    @Override
    public boolean isAvailable(Document document) {
        return Optional.ofNullable(document.getElementById("button-cart"))
                .isPresent();
    }
}
