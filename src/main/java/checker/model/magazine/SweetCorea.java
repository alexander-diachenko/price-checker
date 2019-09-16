package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class SweetCorea extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) throws IllegalStateException {
        Elements prices = document.getElementsByClass("price");
        return prices.stream()
                .findFirst()
                .map(price -> StringUtil.formatPrice(price.text()))
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    protected String getSiteDomain() {
        return "www.sweetcorea.com";
    }

    @Override
    public boolean isAvailable(Document document) {
        Element description = document.select("div.description:contains(Out Of Stock)").first();
        return description == null;
    }
}
