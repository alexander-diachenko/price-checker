package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class NowZenithTest {

    private NowZenith nowZenith;
    private DocumentCreator creator;

    @Before
    public void setup() {
        nowZenith = new NowZenith();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnDiscountPrice() {
        String price = nowZenith.getPrice(creator.createDocumentFromFile("xml/nowZenith/NowZenith_discount.xml"));
        assertEquals("6.19", price);
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = nowZenith.getPrice(creator.createDocumentFromFile("xml/nowZenith/NowZenith_normal.xml"));
        assertEquals("4.60", price);
    }
}
