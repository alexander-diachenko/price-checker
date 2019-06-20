package checker.model.magazine;

import org.jsoup.nodes.Document;

/**
 * @author Alexander Diachenko.
 */
public interface Magazine {

    String getPrice(String url);

    boolean isThisWebsite(String url);

    Document getDocument(String url);

    boolean isAvailable(Document document);
}
