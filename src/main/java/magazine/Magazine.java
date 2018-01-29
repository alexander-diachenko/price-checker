package magazine;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriverException;

/**
 * @author Alexander Diachenko.
 */
public interface Magazine {

    String getPrice(String url);

    boolean isCorrectPage();

    boolean isThisWebsite(String url);

    Document getDocument(String url) throws WebDriverException;

    boolean isDiscount(Element document);

    boolean isAvailable(Element document);
}
