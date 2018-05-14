import org.junit.Test;
import checker.url.UrlUtils;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Alexander Diachenko.
 */
public class UrlUtilsTest {

    @Test
    public void isValidTest() {
        final boolean isValid = UrlUtils.isValid("http://www.google.com.ua/");
        assertTrue(isValid);
    }

    @Test
    public void isValidTest_invalidUrl() {
        final boolean isValid = UrlUtils.isValid("qwe");
        assertFalse(isValid);
    }

    @Test
    public void getDomainNameTest() throws MalformedURLException {
        final String domainName = UrlUtils.getDomainName("https://www.google.com.ua/search?source=hp&ei=3ORlWtPWCYSfsAH2iJ3QCQ&q=qwe&oq=qwe&gs_l=psy-ab.3...2008.2301.0.2493.0.0.0.0.0.0.0.0..0.0....0...1c.1.64.psy-ab..0.0.0....0.hNBqxwRARLg");
        assertEquals("www.google.com.ua", domainName);
    }

    @Test(expected = MalformedURLException.class)
    public void getDomainNameTest_invalidUrl() throws MalformedURLException {
        UrlUtils.getDomainName("qwe");
    }
}
