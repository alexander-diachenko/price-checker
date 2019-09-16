package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class CosmeteaTest {

    private Magazine cosmetea;
    private DocumentCreator creator;

    @Before
    public void setup() {
        cosmetea = new Cosmetea();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = cosmetea.getPrice(creator.createDocumentFromFile("xml/cosmetea/Cosmetea_discount.xml"));
        assertEquals("418", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = cosmetea.getPrice(creator.createDocumentFromFile("xml/cosmetea/Cosmetea_normal.xml"));
        assertEquals("250", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = cosmetea.getPrice(creator.createDocumentFromFile("xml/cosmetea/Cosmetea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }
}
