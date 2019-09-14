package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.Rozetka;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko.
 */
public class RozetkaTest {

    private Magazine rozetka = new Rozetka();

    @Test
    public void shouldReturnPageNotFound() {
        String price = rozetka.getPrice(rozetka.getDocument("https://rozetka.com.ua/qwe"));
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() throws IOException {
        Document document = rozetka.getDocument("https://rozetka.com.ua/");
        assertFalse(document.children().isEmpty());
    }
}
