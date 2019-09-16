package integration;

import checker.model.magazine.Cosmetea;
import checker.model.magazine.Magazine;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko.
 */
public class CosmeteaTest {

    private Magazine korea = new Cosmetea();

    @Test
    public void shouldReturnPageNotFound() {
        String price = korea.getPrice(korea.getDocument("https://cosmetea.com.ua/qwe"));
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() throws IOException {
        Document document = korea.getDocument("https://cosmetea.com.ua/");
        assertFalse(document.children().isEmpty());
    }
}
