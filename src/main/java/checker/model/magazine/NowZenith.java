package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class NowZenith extends AbstractMagazine {

    private static final String DISCOUNTS = "special-price";
    private static final String NORMAL_PRICES = "product-price";
    private static final String SITE_DOMAIN = "www.nowzenith.com";

    @Override
    protected String getPriceFrom(Document document) {
        Elements discounts = document.getElementsByClass(DISCOUNTS);
        if(!discounts.isEmpty()) {
            return getPriceFrom(discounts);

        }
        Elements prices = document.getElementsByClass(NORMAL_PRICES);
        if(!prices.isEmpty()) {
            return getPriceFrom(prices);

        }
        throw new IllegalStateException();
    }

    private String getPriceFrom(Elements elements) {
        return StringUtil.formatPrice(elements.stream().findFirst().orElseThrow(IllegalStateException::new).text());
    }

    @Override
    protected String getSiteDomain() {
        return SITE_DOMAIN;
    }

    @Override
    public boolean isAvailable(Document document) {
        return true;
    }
}
