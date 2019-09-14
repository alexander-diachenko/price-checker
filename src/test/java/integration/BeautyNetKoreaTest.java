package integration;

import checker.model.magazine.BeautyNetKorea;
import checker.model.magazine.Magazine;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko.
 */
public class BeautyNetKoreaTest {

    private Magazine beautyNetKorea = new BeautyNetKorea();

    @Test
    public void shouldReturnPageNotFound() {
        String price = beautyNetKorea.getPrice(beautyNetKorea.getDocument("https://beautynetkorea.com/qwe"));
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() throws IOException {
        Document document = beautyNetKorea.getDocument("https://beautynetkorea.com/");
        assertFalse(document.children().isEmpty());
    }
}
