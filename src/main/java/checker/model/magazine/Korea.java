package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

/**
 * @author Alexander Diachenko
 */
public class Korea extends AbstractMagazine {

    private static final String DISCOUNT_PRICE = "div.summary > p > span > span";
    private static final String NORMAL_PRICE = "div.summary > p > span > ins > span";
    private static final String SITE_DOMAIN = "korea.in.ua";
    private static final String OUT_OF_STOCK = "out-of-stock";

    @Override
    protected String getPriceFrom(Document document) {
        Optional<Element> discountPrice = Optional.ofNullable(document.select(DISCOUNT_PRICE).first());
        if (discountPrice.isPresent()) {
            return StringUtil.formatPrice(discountPrice.get().text());
        }

        Optional<Element> price = Optional.ofNullable(document.select(NORMAL_PRICE).first());
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
        Elements availabilities = document.getElementsByClass(OUT_OF_STOCK);
        return availabilities.isEmpty();
    }
}
