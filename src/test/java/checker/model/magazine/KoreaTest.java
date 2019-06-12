package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class KoreaTest {

    private Korea korea;
    private DocumentCreator creator;

    @Before
    public void setup() {
        korea = new Korea();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = korea.getValue(creator.createDocumentFromFile("Korea_discount.xml"));
        assertEquals("295", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = korea.getValue(creator.createDocumentFromFile("Korea_normal.xml"));
        assertEquals("310", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = korea.getValue(creator.createDocumentFromFile("Korea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }
}
