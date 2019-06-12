package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.RoseRoseShop;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Diachenko.
 */
public class RoseRoseShopTest {

    private Magazine roseRoseShop = new RoseRoseShop();

    @Test
    public void getPriceTest_pageNotFound() {
        final String price = roseRoseShop.getPrice("https://www.roseroseshop.com/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void getPriceTest_unavailable() {
        final String price = roseRoseShop.getPrice("https://www.roseroseshop.com/etude-house-sample-moistfull-collagen-skin-samples-5ml-x-4ea.html");
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void getNormalPriceTest() {
        final String price = roseRoseShop.getPrice("https://www.roseroseshop.com/etude-house-sample-baking-powder-bb-deep-cleansing-foam-sample-30ml.html");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }

    @Test
    public void getDiscountPriceTest() {
        final String price = roseRoseShop.getPrice("https://www.roseroseshop.com/holika-holika-aloe-99-soothing-gel-55ml-new.html");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }
}
