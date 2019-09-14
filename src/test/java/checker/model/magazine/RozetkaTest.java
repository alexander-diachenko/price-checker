package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class RozetkaTest {

    private Rozetka rozetka;
    private DocumentCreator creator;

    @Before
    public void setup() {
        rozetka = new Rozetka();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = rozetka.getValue(creator.createDocumentFromFile("xml/rozetka/Rozetka_discount.xml"));
        assertEquals("631", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = rozetka.getValue(creator.createDocumentFromFile("xml/rozetka/Rozetka_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void shouldReturnSelectedProductDiscountPrice() {
        String price = rozetka.getValue(creator.createDocumentFromFile("xml/rozetka/Rozetka_select.xml"));
        assertEquals("75", price);
    }
}
