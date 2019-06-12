package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.NowZenith;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexander Diachenko.
 */
public class NowZenithTest {

    private Magazine nowZenith = new NowZenith();

    @Test
    public void shouldReturnPageNotFound() {
        String price = nowZenith.getPrice("http://www.nowzenith.com/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() {
        Document document = nowZenith.getDocument("http://www.nowzenith.com/innisfree-skinny-microcara-zero-3-5g-01-black.html");
        assertFalse(document.data().isEmpty());
    }
}
