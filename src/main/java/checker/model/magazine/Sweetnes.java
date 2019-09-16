package checker.model.magazine;

import org.jsoup.nodes.Document;

/**
 * @author Alexander Diachenko
 */
public class Sweetnes extends AbstractMagazine {

    @Override
    protected String getPriceFrom(Document document) throws IllegalStateException {
        return null;
    }

    @Override
    protected String getSiteDomain() {
        return null;
    }

    @Override
    public boolean isAvailable(Document document) {
        return false;
    }
}
