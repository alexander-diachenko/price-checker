package checker.magazine;

import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriverException;

/**
 * @author Alexander Diachenko.
 */
public interface Magazine {

    String getPrice(String url);

    boolean isThisWebsite(String url);

    Document getDocument(String url) throws WebDriverException;

    boolean isAvailable(Document document);
}
