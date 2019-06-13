package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.Rozetka;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko.
 */
public class RozetkaTest {

    private Magazine rozetka = new Rozetka();

    @Test
    public void shouldReturnPageNotFound() {
        String price = rozetka.getPrice("https://rozetka.com.ua/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() {
        Document document = rozetka.getDocument("https://rozetka.com.ua/nina_ricci_3137370205029/p2240292/\n");
        assertFalse(document.data().isEmpty());
    }
}
