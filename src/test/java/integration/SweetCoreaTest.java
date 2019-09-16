package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.SweetCorea;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko.
 */
public class SweetCoreaTest {

    private Magazine sweetCorea = new SweetCorea();

    @Test
    public void shouldReturnPageNotFound() {
        String price = sweetCorea.getPrice(sweetCorea.getDocument("http://www.sweetcorea.com/qwe"));
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() throws IOException {
        Document document = sweetCorea.getDocument("http://www.sweetcorea.com");
        assertFalse(document.children().isEmpty());
    }
}
