package integration;

import checker.magazine.Magazine;
import checker.magazine.Makeup;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Diachenko.
 */
public class MakeupTest {

    private Magazine makeup = new Makeup();

    @Test
    public void getPriceTest_pageNotFound() {
        final String price = makeup.getPrice("https://makeup.com.ua/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void getPriceTest_unavailable() {
        final String price = makeup.getPrice("https://makeup.com.ua/product/20652/");
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void getPriceTest() {
        final String price = makeup.getPrice("https://makeup.com.ua/product/1801/#/option/393587/");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }
}
