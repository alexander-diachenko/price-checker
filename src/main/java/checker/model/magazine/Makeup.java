package checker.model.magazine;

import org.apache.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriverException;
import checker.util.UrlUtils;

import java.net.MalformedURLException;

/**
 * @author Alexander Diachenko.
 */
public class Makeup implements Magazine {

    private final static Logger logger = Logger.getLogger(Makeup.class);

    private String url;

    @Override
    public String getPrice(String url) {
        this.url = url;
        Document document = getDocument(url);
        if (document.data().isEmpty()) {
            return "Страница не найдена";
        }
        return getValue(document);
    }

    @Override
    public boolean isThisWebsite(String url) {
        try {
            return UrlUtils.isValid(url) && UrlUtils.getDomainName(url).equals("makeup.com.ua");
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

    private String getValue(Document document) {
        if (!isAvailable(document)) {
            return "Нет в наличии";
        }
        Elements elementsByAttributeValue = document.getElementsByAttributeValue("data-variant-id", getDataVariantId(url));
        if (elementsByAttributeValue.isEmpty()) {
            return document.select("span.product-item__price > span.rus").text();
        }
        return elementsByAttributeValue.stream().findFirst().map(element -> element.attr("data-price")).orElse("Не найдено");
    }

    @Override
    public boolean isAvailable(Document document) {
        final Element status = document.getElementById("product_enabled");
        return status.text().equalsIgnoreCase("Есть в наличии!");
    }

    private String getDataVariantId(String url) {
        String[] split = url.split("/");
        return split[split.length - 1];
    }
}
