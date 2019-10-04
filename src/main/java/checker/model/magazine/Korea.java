package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

/**
 * @author Alexander Diachenko
 */
public class Korea extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) {
        Optional<Element> discountPrice = Optional.ofNullable(document.select("div.summary > p > span > span").first());
        if (discountPrice.isPresent()) {
            return StringUtil.formatPrice(discountPrice.get().text());
        }

        Optional<Element> price = Optional.ofNullable(document.select("div.summary > p > span > ins > span").first());
        if (price.isPresent()) {
            return StringUtil.formatPrice(price.get().text());
        }
        throw new IllegalStateException();
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
