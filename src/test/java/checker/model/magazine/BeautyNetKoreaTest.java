package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class BeautyNetKoreaTest {

    private BeautyNetKorea beautyNetKorea;
    private DocumentCreator creator;

    @Before
    public void setup() {
        beautyNetKorea = new BeautyNetKorea();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = beautyNetKorea.getValue(creator.createDocumentFromFile("BeautyNetKorea_discount.xml"));
        assertEquals("4.47", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = beautyNetKorea.getValue(creator.createDocumentFromFile("BeautyNetKorea_normal.xml"));
        assertEquals("1.43", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = beautyNetKorea.getValue(creator.createDocumentFromFile("BeautyNetKorea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }
}
