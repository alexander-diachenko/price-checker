package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Alexander Diachenko
 */
public class Korea extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) {
        Element price = document.select("div.summary > p > span > span").first();
        if (price == null) {
            price = document.select("div.summary > p > span > ins > span").first();
        }
        return StringUtil.formatPrice(price.text());
    }

    @Override
    protected String getSiteDomain() {
        return "korea.in.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements availabilities = document.getElementsByClass("out-of-stock");
        return availabilities.isEmpty();
    }
}
