package integration;

import checker.model.magazine.BeautyNetKorea;
import checker.model.magazine.Magazine;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Diachenko.
 */
public class BeautyNetKoreaTest {

    private Magazine beautyNetKorea = new BeautyNetKorea();

    @Test
    public void getPriceTest_pageNotFound() {
        final String price = beautyNetKorea.getPrice("https://beautynetkorea.com/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void getPriceTest_unavailable() {
        final String price = beautyNetKorea.getPrice("https://beautynetkorea.com/product/cp-1-bright-complex-intense-nourishing-shampoo-100ml-weight-125g/7039/category/407//p48");
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void getDiscountPriceTest() {
        final String price = beautyNetKorea.getPrice("https://beautynetkorea.com/product/etude-house-baking-powder-pore-cleansing-foam-160ml-weight-205g/4168/category/30/");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }
    @Test
    public void getNormalPriceTest() {
        final String price = beautyNetKorea.getPrice("https://beautynetkorea.com/product/big-sale-mizon-joyful-time-essence-mask-16-type-10pcs-23g-exp20200214-/8374/?cate_no=98&display_group=1");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }
}
