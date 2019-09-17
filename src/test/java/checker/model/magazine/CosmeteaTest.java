package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko
 */
public class CosmeteaTest {

    private Magazine cosmetea;
    private DocumentCreator creator;

    @Before
    public void setup() {
        cosmetea = new Cosmetea();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = cosmetea.getPrice(creator.createDocumentFromFile("xml/cosmetea/Cosmetea_discount.xml"));
        assertEquals("418", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = cosmetea.getPrice(creator.createDocumentFromFile("xml/cosmetea/Cosmetea_normal.xml"));
        assertEquals("250", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = cosmetea.getPrice(creator.createDocumentFromFile("xml/cosmetea/Cosmetea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(cosmetea.isThisWebsite("https://cosmetea.com.ua"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(cosmetea.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(cosmetea.isThisWebsite("qwe"));
    }
}
