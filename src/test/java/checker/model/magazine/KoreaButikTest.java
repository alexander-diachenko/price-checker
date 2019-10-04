package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko
 */
public class KoreaButikTest {

    private Magazine koreaButik;
    private DocumentCreator creator;

    @Before
    public void setup() {
        koreaButik = new KoreaButik();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = koreaButik.getPrice(creator.createDocumentFromFile("xml/koreaButik/KoreaButik_discount.xml"));
        assertEquals("337.50", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = koreaButik.getPrice(creator.createDocumentFromFile("xml/koreaButik/KoreaButik_normal.xml"));
        assertEquals("145", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = koreaButik.getPrice(creator.createDocumentFromFile("xml/koreaButik/KoreaButik_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnNotFound() {
        String price = koreaButik.getPrice(creator.createDocumentFromFile("xml/koreaButik/KoreaButik_notfound.xml"));
        assertEquals("Не найдено", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(koreaButik.isThisWebsite("https://korea-butik.com"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(koreaButik.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(koreaButik.isThisWebsite("qwe"));
    }
}
