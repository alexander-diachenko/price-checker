package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko
 */
public class RoseRoseShopTest {

    private Magazine roseRoseShop;
    private DocumentCreator creator;

    @Before
    public void setup() {
        roseRoseShop = new RoseRoseShop();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = roseRoseShop.getPrice(creator.createDocumentFromFile("xml/roseRoseShop/RoseRoseShop_discount.xml"));
        assertEquals("1.28", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = roseRoseShop.getPrice(creator.createDocumentFromFile("xml/roseRoseShop/RoseRoseShop_normal.xml"));
        assertEquals("0.88", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = roseRoseShop.getPrice(creator.createDocumentFromFile("xml/roseRoseShop/RoseRoseShop_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnNotFound() {
        String price = roseRoseShop.getPrice(creator.createDocumentFromFile("xml/roseRoseShop/RoseRoseShop_notfound.xml"));
        assertEquals("Не найдено", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(roseRoseShop.isThisWebsite("https://www.roseroseshop.com"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(roseRoseShop.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(roseRoseShop.isThisWebsite("qwe"));
    }
}
