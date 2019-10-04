package checker.model.magazine;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;

import java.util.Optional;

/**
 * @author Alexander Diachenko.
 */
public class Makeup extends AbstractMagazine {

    protected String getPriceFrom(Document document) {
        return document.getElementsByAttributeValue("data-variant-id", getDataVariantId(url)).stream()
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
