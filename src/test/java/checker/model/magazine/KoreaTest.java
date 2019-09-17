package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko
 */
public class KoreaTest {

    private Magazine korea;
    private DocumentCreator creator;

    @Before
    public void setup() {
        korea = new Korea();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = korea.getPrice(creator.createDocumentFromFile("xml/korea/Korea_discount.xml"));
        assertEquals("380.00", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = korea.getPrice(creator.createDocumentFromFile("xml/korea/Korea_normal.xml"));
        assertEquals("430.00", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = korea.getPrice(creator.createDocumentFromFile("xml/korea/Korea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(korea.isThisWebsite("https://korea.in.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(korea.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(korea.isThisWebsite("qwe"));
    }
}
