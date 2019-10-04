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
        Optional<Element> first = Optional.ofNullable(document.select("div.summary > p > span > span").first());
        if (first.isPresent()) {
            return StringUtil.formatPrice(first.get().text());
        }

        Optional<Element> second = Optional.ofNullable(document.select("div.summary > p > span > ins > span").first());
        if (second.isPresent()) {
            return StringUtil.formatPrice(second.get().text());
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
