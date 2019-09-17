package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko
 */
public class SweetnessTest {

    private Magazine sweetness;
    private DocumentCreator creator;

    @Before
    public void setup() {
        sweetness = new Sweetness();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = sweetness.getPrice(creator.createDocumentFromFile("xml/sweetness/Sweetness_discount.xml"));
        assertEquals("217", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = sweetness.getPrice(creator.createDocumentFromFile("xml/sweetness/Sweetness_normal.xml"));
        assertEquals("175", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = sweetness.getPrice(creator.createDocumentFromFile("xml/sweetness/Sweetness_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(sweetness.isThisWebsite("https://sweetness.com.ua"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(sweetness.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(sweetness.isThisWebsite("qwe"));
    }
}
