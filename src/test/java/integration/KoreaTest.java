package integration;

import checker.model.magazine.Korea;
import checker.model.magazine.Magazine;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * @author Alexander Diachenko.
 */
public class KoreaTest {

    private Magazine korea = new Korea();

    @Test
    public void shouldReturnPageNotFound() {
        String price = korea.getPrice(korea.getDocument("https://korea.in.ua/qwe"));
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() throws IOException {
        Document document = korea.getDocument("https://korea.in.ua/");
        assertFalse(document.children().isEmpty());
    }
}
