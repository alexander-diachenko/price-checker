package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class MakeupTest {

    private Makeup makeup;
    private DocumentCreator creator;

    @Before
    public void setup() {
        makeup = new Makeup();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        makeup.url = "https://makeup.com.ua/product/632463/#/option/1558727/";
        String price = makeup.getValue(creator.createDocumentFromFile("Makeup_discount.xml"));
        assertEquals("209", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        makeup.url = "https://makeup.com.ua/product/1801/#/option/393587/";
        String price = makeup.getValue(creator.createDocumentFromFile("Makeup_normal.xml"));
        assertEquals("218", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        makeup.url = "https://makeup.com.ua/product/20652/";
        String price = makeup.getValue(creator.createDocumentFromFile("Makeup_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }
}