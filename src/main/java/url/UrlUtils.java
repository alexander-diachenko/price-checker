package url;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Alexander Diachenko.
 */
public class UrlUtils {

    public static String getDomainName(String url) {
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }
}
