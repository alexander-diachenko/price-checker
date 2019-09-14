package integration;

import checker.model.magazine.Korea;
import checker.model.magazine.Magazine;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko.
 */
public class KoreaButikTest {

    private Magazine korea = new Korea();

    @Test
    public void shouldReturnPageNotFound() {
        String price = korea.getPrice(korea.getDocument("https://korea-butik.com/qwe"));
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() throws IOException {
        Document document = korea.getDocument("https://korea-butik.com");
        assertFalse(document.children().isEmpty());
    }
}
