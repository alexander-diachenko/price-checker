package checker.model.magazine;

import checker.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class BeautyNetKorea extends AbstractMagazine {

    @Override
    protected String getValue(Document document) {
        if (!isAvailable(document)) {
            return OUT_OF_STOCK;
        }
        Element discountPrice = document.getElementById("span_product_price_sale");
        if (discountPrice != null) {
            return formatPrice(discountPrice.text());
        }
        Element price = document.getElementById("span_product_price_text");
        if(price != null) {
            return formatPrice(price.text());
        }
        return NOT_FOUND;
    }

    private String formatPrice(String price) {
        String priceUSD = StringUtils.substringBetween(price, "(", ")");
        return StringUtil.formatPrice(priceUSD);
    }

    @Override
    protected String getSiteDomain() {
        return "beautynetkorea.com";
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements availabilities = document.getElementsByAttributeValue("alt", "Out-of-stock");
        return availabilities.isEmpty();
    }
}
