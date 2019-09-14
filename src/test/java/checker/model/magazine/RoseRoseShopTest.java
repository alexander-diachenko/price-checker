package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class RoseRoseShopTest {

    private RoseRoseShop roseRoseShop;
    private DocumentCreator creator;

    @Before
    public void setup() {
        roseRoseShop = new RoseRoseShop();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = roseRoseShop.getPrice(creator.createDocumentFromFile("xml/roseRoseShop/RoseRoseShop_discount.xml"));
        assertEquals("1.28", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = roseRoseShop.getPrice(creator.createDocumentFromFile("xml/roseRoseShop/RoseRoseShop_normal.xml"));
        assertEquals("0.88", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = roseRoseShop.getPrice(creator.createDocumentFromFile("xml/roseRoseShop/RoseRoseShop_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }
}
