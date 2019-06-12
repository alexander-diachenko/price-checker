package integration;

import checker.model.magazine.Magazine;
import checker.model.magazine.NowZenith;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Diachenko.
 */
public class NowZenithTest {

    private Magazine nowZenith = new NowZenith();

    @Test
    public void getPriceTest_pageNotFound() {
        final String price = nowZenith.getPrice("http://www.nowzenith.com/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void getDiscountPriceTest() {
        final String price = nowZenith.getPrice("http://www.nowzenith.com/innisfree-skinny-microcara-zero-3-5g-01-black.html");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }

    @Test
    public void getNormalPriceTest() {
        final String price = nowZenith.getPrice("http://www.nowzenith.com/3w-clinic-collagen-and-luxury-gold-revitalizing-comfort-gold-essence-150ml.html");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }
}
