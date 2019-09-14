package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class KoreaButik extends AbstractMagazine {

    @Override
    protected String getValue(Document document) {
        if (!isAvailable(document)) {
            return OUT_OF_STOCK;
        }
        Elements price = document.getElementsByAttributeValue("data-qaid", "product_price");
        return StringUtil.formatPrice(price.text());
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
