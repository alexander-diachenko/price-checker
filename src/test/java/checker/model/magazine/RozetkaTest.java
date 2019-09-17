package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko
 */
public class RozetkaTest {

    private Magazine rozetka;
    private DocumentCreator creator;

    @Before
    public void setup() {
        rozetka = new Rozetka();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = rozetka.getPrice(creator.createDocumentFromFile("xml/rozetka/Rozetka_discount.xml"));
        assertEquals("631", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = rozetka.getPrice(creator.createDocumentFromFile("xml/rozetka/Rozetka_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnSelectedProductDiscountPrice() {
        String price = rozetka.getPrice(creator.createDocumentFromFile("xml/rozetka/Rozetka_select.xml"));
        assertEquals("75", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(rozetka.isThisWebsite("https://rozetka.com.ua"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(rozetka.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(rozetka.isThisWebsite("qwe"));
    }
}
