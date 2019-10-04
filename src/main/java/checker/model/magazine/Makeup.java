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

    protected String getPriceFrom(Document document) {
        Elements elementsByAttributeValue = document.getElementsByAttributeValue("data-variant-id", getDataVariantId(url));
        if (elementsByAttributeValue.isEmpty()) {
            Elements discounts = document.select("span.product-item__price > span.rus");
            return discounts.stream()
                    .findFirst()
                    .map(Element::text)
                    .orElseThrow(IllegalStateException::new);
        }
        return elementsByAttributeValue.stream()
                .findFirst()
                .map(element -> element.attr("data-price"))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return "makeup.com.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        return Optional.ofNullable(document.getElementById("product_enabled"))
                .filter(availability -> StringUtils.containsIgnoreCase(availability.text(), "Есть в наличии"))
                .isPresent();
    }

    private String getDataVariantId(String url) {
        String[] split = url.split("/");
        return split[split.length - 1];
    }
}
