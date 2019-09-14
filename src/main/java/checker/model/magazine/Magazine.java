package checker.model.magazine;

import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * @author Alexander Diachenko.
 */
public interface Magazine {

    String getPrice(String url);

    boolean isThisWebsite(String url);

    Document getDocument(String url) throws IOException;

    boolean isAvailable(Document document);
}
