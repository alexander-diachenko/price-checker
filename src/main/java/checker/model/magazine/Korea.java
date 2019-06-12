package checker.model.magazine;

import checker.util.UrlUtils;
import org.apache.log4j.Logger;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriverException;

import java.net.MalformedURLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Element price = document.getElementsByClass("price").stream().findFirst().orElseGet(null);
        if(price == null) {
            return "Нет в наличии";
        }
        return formatPrice(price.text());
    }

    private String formatPrice(String price) {
        StringBuilder result = new StringBuilder();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(price);
        while(m.find()) {
            result.append(m.group());
        }
        return result.toString();
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
        Element availability = document.getElementsByClass("availability").stream().findFirst().orElseGet(null);
        if(availability == null) {
            return false;
        }
        return "В наличии".equalsIgnoreCase(availability.text());
    }
}
