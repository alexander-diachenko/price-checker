package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class KoreaButikTest {

    private KoreaButik koreaButik;
    private DocumentCreator creator;

    @Before
    public void setup() {
        koreaButik = new KoreaButik();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = koreaButik.getValue(creator.createDocumentFromFile("xml/koreaButik/KoreaButik_discount.xml"));
        assertEquals("337,50", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = koreaButik.getValue(creator.createDocumentFromFile("xml/koreaButik/KoreaButik_normal.xml"));
        assertEquals("145", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = koreaButik.getValue(creator.createDocumentFromFile("xml/koreaButik/KoreaButik_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }
}
