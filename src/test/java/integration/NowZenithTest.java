package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.NowZenith;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Alexander Diachenko.
 */
public class NowZenithTest {

    private Magazine nowZenith = new NowZenith();

    @Test
    public void shouldReturnPageNotFound() {
        String price = nowZenith.getPrice(nowZenith.getDocument("http://www.nowzenith.com/qwe"));
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() throws IOException {
        Document document = nowZenith.getDocument("http://www.nowzenith.com/");
        assertFalse(document.children().isEmpty());
    }
}
