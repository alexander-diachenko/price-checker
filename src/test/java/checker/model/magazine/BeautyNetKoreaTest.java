package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexander Diachenko
 */
public class BeautyNetKoreaTest {

    private Magazine beautyNetKorea;
    private DocumentCreator creator;

    @Before
    public void setup() {
        beautyNetKorea = new BeautyNetKorea();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = beautyNetKorea.getPrice(creator.createDocumentFromFile("xml/beautyNewKorea/BeautyNetKorea_discount.xml"));
        assertEquals("4.47", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = beautyNetKorea.getPrice(creator.createDocumentFromFile("xml/beautyNewKorea/BeautyNetKorea_normal.xml"));
        assertEquals("1.43", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = beautyNetKorea.getPrice(creator.createDocumentFromFile("xml/beautyNewKorea/BeautyNetKorea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnNotFound() {
        String price = beautyNetKorea.getPrice(creator.createDocumentFromFile("xml/beautyNewKorea/BeautyNetKorea_notfound.xml"));
        assertEquals("Не найдено", price);
    }

    @Test
    public void shouldReturnTrueWhenIsThisWebSiteCalled() {
        assertTrue(beautyNetKorea.isThisWebsite("https://beautynetkorea.com"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithGoogleDomain() {
        assertFalse(beautyNetKorea.isThisWebsite("https://www.google.com.ua/"));
    }

    @Test
    public void shouldReturnFalseWhenIsThisWebSiteCalledWithIncorrectDomain() {
        assertFalse(beautyNetKorea.isThisWebsite("qwe"));
    }
}
