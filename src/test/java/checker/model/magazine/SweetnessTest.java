package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class SweetnessTest {

    private Magazine sweetnes;
    private DocumentCreator creator;

    @Before
    public void setup() {
        sweetnes = new Sweetnes();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = sweetnes.getPrice(creator.createDocumentFromFile("xml/cosmetea/Cosmetea_discount.xml"));
        assertEquals("418", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = sweetnes.getPrice(creator.createDocumentFromFile("xml/cosmetea/Cosmetea_normal.xml"));
        assertEquals("250", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = sweetnes.getPrice(creator.createDocumentFromFile("xml/cosmetea/Cosmetea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }
}
