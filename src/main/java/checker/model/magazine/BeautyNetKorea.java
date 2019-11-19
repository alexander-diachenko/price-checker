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

    private static final String DISCOUNT = "span_product_price_sale";
    private static final String NORMAL_PRICE = "span_product_price_text";
    private static final String SITE_DOMAIN = "beautynetkorea.com";
    private static final String OUT_OF_STOCK = "Out-of-stock";
    private static final String ALT = "alt";

    @Override
    protected String getPriceFrom(Document document) {
        Element discountPrice = document.getElementById(DISCOUNT);
        if (discountPrice != null) {
            return formatPrice(discountPrice.text());
        }
        Element price = document.getElementById(NORMAL_PRICE);
        if (price != null) {
            return formatPrice(price.text());
        }
        throw new IllegalStateException();
    }

    private String formatPrice(String price) {
        String priceUSD = StringUtils.substringBetween(price, "(", ")");
        return StringUtil.formatPrice(priceUSD);
    }

    @Override
    protected String getSiteDomain() {
        return SITE_DOMAIN;
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements availabilities = document.getElementsByAttributeValue(ALT, OUT_OF_STOCK);
        return availabilities.isEmpty();
    }
}
