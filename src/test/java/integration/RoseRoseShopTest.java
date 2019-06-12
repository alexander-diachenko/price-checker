package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.RoseRoseShop;
import org.jsoup.nodes.Document;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexander Diachenko.
 */
public class RoseRoseShopTest {

    private Magazine roseRoseShop = new RoseRoseShop();

    @Test
    public void shouldReturnPageNotFound() {
        String price = roseRoseShop.getPrice("https://www.roseroseshop.com/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void shouldReturnNotEmptyDocument() {
        Document document = roseRoseShop.getDocument("https://www.roseroseshop.com/etude-house-sample-moistfull-collagen-skin-samples-5ml-x-4ea.html");
        assertFalse(document.data().isEmpty());
    }
}
