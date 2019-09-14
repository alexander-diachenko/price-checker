package checker.model.magazine;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko.
 */
public class Makeup extends AbstractMagazine {

    protected String getPriceFrom(Document document) {
        Elements elementsByAttributeValue = document.getElementsByAttributeValue("data-variant-id", getDataVariantId(url));
        if (elementsByAttributeValue.isEmpty()) {
            return document.select("span.product-item__price > span.rus").text();
        }
        return elementsByAttributeValue.stream().findFirst().map(element -> element.attr("data-price")).orElse(null);
    }

    @Override
    protected String getSiteDomain() {
        return "makeup.com.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        final Element status = document.getElementById("product_enabled");
        return StringUtils.containsIgnoreCase(status.text(), "Есть в наличии");
    }

    private String getDataVariantId(String url) {
        String[] split = url.split("/");
        return split[split.length - 1];
    }
}
