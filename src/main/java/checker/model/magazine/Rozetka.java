package checker.model.magazine;

import checker.util.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Rozetka extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) {
        Elements prices = document.getElementsByClass("detail-price-uah");
        if(!prices.isEmpty()) {
            return getPriceFrom(prices);
        }
        return NOT_FOUND;
    }

    private String getPriceFrom(Elements elements) {
        return StringUtil.formatPrice(elements.stream().findFirst().orElseThrow(IllegalStateException::new).text());
    }

    @Override
    protected String getSiteDomain() {
        return "rozetka.com.ua";
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements buyButtons = document.getElementsByClass("btn-link-i");
        for (Element buyButton : buyButtons) {
            if("Купить".equalsIgnoreCase(buyButton.text())) {
                return true;
            }
        }
        return false;
    }
}
