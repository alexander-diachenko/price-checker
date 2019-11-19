package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class Cosmetea extends AbstractMagazine {

    private static final String DISCOUNTS = "autocalc-product-special";
    private static final String NORMAL_PRICES = "autocalc-product-price";
    private static final String SITE_DOMAIN = "cosmetea.com.ua";
    private static final String OUT_OF_STOCK = "ul.description:contains(Доступность: Нет в наличии)";

    @Override
    protected String getPriceFrom(Document document) {
        Elements prices = document.getElementsByClass(DISCOUNTS);
        if (prices.isEmpty()) {
            prices = document.getElementsByClass(NORMAL_PRICES);
        }
        return prices.stream()
                .findFirst()
                .map(price -> StringUtil.formatPrice(price.text()))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return SITE_DOMAIN;
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements availabilities = document.select(OUT_OF_STOCK);
        return availabilities.isEmpty();
    }
}
