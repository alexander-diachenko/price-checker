package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.Makeup;
import org.jsoup.nodes.Document;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author Alexander Diachenko.
 */
public class MakeupTest {

    private Magazine makeup = new Makeup();

    @Test
    public void shouldReturnPageNotFound() {
        String price = makeup.getPrice("https://makeup.com.ua/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() {
        Document document = makeup.getDocument("https://makeup.com.ua/product/20652/");
        assertFalse(document.data().isEmpty());
    }
}
