package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko
 */
public class NowZenithTest {

    private Magazine nowZenith;
    private DocumentCreator creator;

    @Before
    public void setup() {
        nowZenith = new NowZenith();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = nowZenith.getPrice(creator.createDocumentFromFile("xml/nowZenith/NowZenith_discount.xml"));
        assertEquals("6.19", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = nowZenith.getPrice(creator.createDocumentFromFile("xml/nowZenith/NowZenith_normal.xml"));
        assertEquals("4.60", price);
    }

    @Test
    public void shouldReturnNotFound() {
        String price = nowZenith.getPrice(creator.createDocumentFromFile("xml/nowZenith/NowZenith_notfound.xml"));
        assertEquals("Не найдено", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(nowZenith.isThisWebsite("http://www.nowzenith.com"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(nowZenith.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(nowZenith.isThisWebsite("qwe"));
    }
}
