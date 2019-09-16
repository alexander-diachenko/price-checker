package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class NowZenith extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) {
        Elements discounts = document.getElementsByClass("special-price");
        if(!discounts.isEmpty()) {
            return getPriceFrom(discounts);

        }
        Elements prices = document.getElementsByClass("product-price");
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
        return "www.nowzenith.com";
    }

    @Override
    public boolean isAvailable(Document document) {
        return true;
    }
}
