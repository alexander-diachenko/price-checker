package integration;

import checker.model.magazine.Korea;
import checker.model.magazine.Magazine;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexander Diachenko.
 */
public class KoreaTest {

    private Magazine korea = new Korea();

    @Test
    public void shouldReturnPageNotFound() {
        String price = korea.getPrice("https://korea.in.ua/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() {
        Document document = korea.getDocument("https://korea.in.ua/missha-all-around-safe-block-essence-sun-spf45-pa-solntsezaschitnaya-essentsiya-dlya-litsa-i-tela/p1029");
        assertFalse(document.data().isEmpty());
    }
}
