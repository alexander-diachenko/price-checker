package magazine;

import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriverException;

/**
 * @author Alexander Diachenko.
 */
public interface Magazine {

    String getPrice(String url);

    boolean isCorrectPage(Document document);

    boolean isThisWebsite(String url);

    Document getDocument(String url) throws WebDriverException;

    boolean isDiscount(Document document);

    boolean isAvailable(Document document);
}
