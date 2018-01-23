package magazine;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriverException;

import java.io.IOException;

/**
 * @author Alexander Diachenko.
 */
public interface Magazine {

    String getPrice(String url) throws IOException;

    boolean isCorrectPage();

    boolean isThisWebsite(String url);

    Document getDocument(String url) throws WebDriverException;

    boolean isDiscount(Element document) throws IOException;

    boolean isAvailable(Element document) throws IOException;
}
