package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko
 */
public class MakeupTest {

    private Makeup makeup;
    private DocumentCreator creator;

    @Before
    public void setup() {
        makeup = new Makeup();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        makeup.url = "https://makeup.com.ua/product/632463/#/option/1558727/";
        String price = makeup.getPrice(creator.createDocumentFromFile("xml/makeup/Makeup_discount.xml"));
        assertEquals("209", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        makeup.url = "https://makeup.com.ua/product/1801/#/option/393587/";
        String price = makeup.getPrice(creator.createDocumentFromFile("xml/makeup/Makeup_normal.xml"));
        assertEquals("218", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        makeup.url = "https://makeup.com.ua/product/20652/";
        String price = makeup.getPrice(creator.createDocumentFromFile("xml/makeup/Makeup_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(makeup.isThisWebsite("https://makeup.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(makeup.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(makeup.isThisWebsite("qwe"));
    }
}
