package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class Korea extends AbstractMagazine {

    protected String getValue(Document document) {
        if (!isAvailable(document)) {
            return OUT_OF_STOCK;
        }
        Elements prices = document.getElementsByClass("price");
        return prices.stream().findFirst().map(price -> StringUtil.formatPrice(price.text())).orElse(NOT_FOUND);
    }

    @Override
    protected String getSiteDomain() {
        return "korea.in.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements availabilities = document.getElementsByClass("availability");
        for (Element availability : availabilities) {
            if ("В наличии".equalsIgnoreCase(availability.text())) {
                return true;
            }
        }
        return false;
    }
}
