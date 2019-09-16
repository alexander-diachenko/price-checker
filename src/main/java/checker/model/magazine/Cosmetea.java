package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class Cosmetea extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) throws IllegalStateException {
        Elements prices = document.getElementsByClass("autocalc-product-special");
        if (prices.isEmpty()) {
            prices = document.getElementsByClass("autocalc-product-price");
        }
        return prices.stream()
                .findFirst()
                .map(price -> StringUtil.formatPrice(price.text()))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return "cosmetea.com.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        Element availability = document.select("ul.description:contains(Доступность: На складе)").first();
        return availability != null;
    }
}
