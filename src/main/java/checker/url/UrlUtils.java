package checker.url;

import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Alexander Diachenko.
 */
public class UrlUtils {

    /**
     * Return domain name for given checker.url
     * @param url String checker.url
     * @return domain name for given checker.url
     * @throws MalformedURLException then checker.url is invalid
     */
    public static String getDomainName(String url) throws MalformedURLException {
        return new URL(url).getHost();
    }

    /**
     * Check if the checker.url is valid
     * @param url String checker.url
     * @return true if valid
     */
    public static boolean isValid(String url) {
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        return urlValidator.isValid(url);
    }
}
