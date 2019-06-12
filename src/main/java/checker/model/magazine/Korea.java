package checker.model.magazine;

import checker.util.StringUtil;
import checker.util.UrlUtils;
import org.apache.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriverException;

import java.net.MalformedURLException;

/**
 * @author Alexander Diachenko
 */
public class Korea implements Magazine {

    private final static Logger logger = Logger.getLogger(Korea.class);

    @Override
    public String getPrice(String url) {
        Document document = getDocument(url);
        if (document.data().isEmpty()) {
            return "Страница не найдена";
        }
        return getValue(document);
    }

    private String getValue(Document document) {
        if (!isAvailable(document)) {
            return "Нет в наличии";
        }
        Elements prices = document.getElementsByClass("price");
        return prices.stream().findFirst().map(price -> StringUtil.formatPrice(price.text())).orElse("Нет в наличии");
    }

    @Override
    public boolean isThisWebsite(String url) {
        try {
            return UrlUtils.isValid(url) && UrlUtils.getDomainName(url).equals("korea.in.ua");
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public Document getDocument(String url) throws WebDriverException {
        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
        } catch (HttpStatusException ex) {
            return new Document("");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new Document("");
    }

    @Override
    public boolean isAvailable(Document document) {
        Elements availabilities = document.getElementsByClass("availability");
        for (Element availability : availabilities) {
            if ("В наличии".equalsIgnoreCase(availability.text())) {
                return true;
            }
        }
        return false;
    }
}
