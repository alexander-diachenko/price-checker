package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class NowZenith extends AbstractMagazine {

    @Override
    protected String getValue(Document document) {
        if (!isAvailable(document)) {
            return OUT_OF_STOCK;
        }
        Elements discounts = document.getElementsByClass("special-price");
        for (Element discount : discounts) {
            return StringUtil.formatPrice(discount.text());
        }
        Elements prices = document.getElementsByClass("product-price");
        for (Element price : prices) {
            return StringUtil.formatPrice(price.text());
        }
        return NOT_FOUND;
    }

    @Override
    protected String getSiteDomain() {
        return "www.nowzenith.com";
    }

    @Override
    public boolean isAvailable(Document document) {
        return true;
    }
}
