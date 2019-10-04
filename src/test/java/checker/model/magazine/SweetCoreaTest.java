package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko
 */
public class SweetCoreaTest {

    private Magazine sweetCorea;
    private DocumentCreator creator;

    @Before
    public void setup() {
        sweetCorea = new SweetCorea();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = sweetCorea.getPrice(creator.createDocumentFromFile("xml/sweetCorea/SweetCorea_normal.xml"));
        assertEquals("6.300", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = sweetCorea.getPrice(creator.createDocumentFromFile("xml/sweetCorea/SweetCorea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnNotFound() {
        String price = sweetCorea.getPrice(creator.createDocumentFromFile("xml/sweetCorea/SweetCorea_notfound.xml"));
        assertEquals("Не найдено", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(sweetCorea.isThisWebsite("http://www.sweetcorea.com"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(sweetCorea.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(sweetCorea.isThisWebsite("qwe"));
    }
}
