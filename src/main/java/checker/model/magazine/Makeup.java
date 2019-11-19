package checker.model.magazine;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

/**
 * @author Alexander Diachenko.
 */
public class Makeup extends AbstractMagazine {

    private static final String DATA_VARIANT_ID = "data-variant-id";
    private static final String DISCOUNTS = "span.product-item__price > span.rus";
    private static final String NORMAL_PRICE = "data-price";
    private static final String SITE_DOMAIN = "makeup.com.ua";
    private static final String PRODUCT_STATUS = "product_enabled";
    private static final String IN_STOCK = "Есть в наличии";

    protected String getPriceFrom(Document document) {
        Elements elementsByAttributeValue = document.getElementsByAttributeValue(DATA_VARIANT_ID, getDataVariantId(url));
        if (elementsByAttributeValue.isEmpty()) { //TODO как нибудь разобратся что тут происходит
            Elements discounts = document.select(DISCOUNTS);
            return discounts.stream()
                    .findFirst()
                    .map(Element::text)
                    .orElseThrow(IllegalStateException::new);
        }
        return elementsByAttributeValue.stream()
                .findFirst()
                .map(element -> element.attr(NORMAL_PRICE))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return SITE_DOMAIN;
    }

    @Override
    public boolean isAvailable(Document document) {
        return Optional.ofNullable(document.getElementById(PRODUCT_STATUS))
                .filter(availability -> StringUtils.containsIgnoreCase(availability.text(), IN_STOCK))
                .isPresent();
    }

    private String getDataVariantId(String url) {
        String[] split = url.split("/");
        return split[split.length - 1];
    }
}
