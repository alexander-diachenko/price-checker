package integration;

import checker.model.magazine.BeautyNetKorea;
import checker.model.magazine.Magazine;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Alexander Diachenko.
 */
public class BeautyNetKoreaTest {

    private Magazine beautyNetKorea = new BeautyNetKorea();

    @Test
    public void shouldReturnPageNotFound() {
        String price = beautyNetKorea.getPrice("https://beautynetkorea.com/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() {
        Document document = beautyNetKorea.getDocument("https://beautynetkorea.com/product/cp-1-bright-complex-intense-nourishing-shampoo-100ml-weight-125g/7039/category/407//p48");
        assertFalse(document.data().isEmpty());
    }
}
