package checker.model.magazine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class SweetCoreaTest {

    private Magazine sweetCorea;
    private DocumentCreator creator;

    @Before
    public void setup() {
        sweetCorea = new SweetCorea();
        creator = new DocumentCreator();
    }

    @Test
    public void shouldReturnNormalPrice() {
        String price = sweetCorea.getPrice(creator.createDocumentFromFile("xml/sweetcorea/sweetcorea_normal.xml"));
        assertEquals("6.300", price);
    }

    @Test
    public void shouldReturnOutOfStock() {
        String price = sweetCorea.getPrice(creator.createDocumentFromFile("xml/sweetcorea/sweetcorea_outofstock.xml"));
        assertEquals("Нет в наличии", price);
    }
}
