package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * @author Alexander Diachenko
 */
public class Sweetness extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) throws IllegalStateException {
        Element price = document.getElementById("our_price_display");
        return StringUtil.formatPrice(price.text());
    }

    @Override
    protected String getSiteDomain() {
        return "sweetness.com.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        Element availability = document.getElementById("availability_value");
        return !"Нет в наличии товара".equalsIgnoreCase(availability.text().trim());
    }
}
