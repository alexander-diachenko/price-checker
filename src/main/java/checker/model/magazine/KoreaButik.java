package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class KoreaButik extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) {
        Elements prices = document.getElementsByAttributeValue("data-qaid", "product_price");
        return prices.stream()
                .findFirst()
                .map(price -> StringUtil.formatPrice(price.text()))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return "korea-butik.com";
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements presenceData = document.getElementsByAttributeValue("data-qaid", "presence_data");
        return "В наличии".equalsIgnoreCase(presenceData.text());
    }
}
