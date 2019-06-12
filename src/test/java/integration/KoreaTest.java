package integration;

import checker.model.magazine.Korea;
import checker.model.magazine.Magazine;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Diachenko.
 */
public class KoreaTest {

    private Magazine korea = new Korea();

    @Test
    public void getPriceTest_pageNotFound() {
        final String price = korea.getPrice("https://korea.in.ua/qwe");
        assertEquals("Страница не найдена", price);
    }

    @Test
    public void getPriceTest_unavailable() {
        final String price = korea.getPrice("https://korea.in.ua/tony-moly-magic-food-banana-bananovaya-nochnaya-maska/p48\n");
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void getPriceTest() {
        final String price = korea.getPrice("https://korea.in.ua/bb-krem-missha-m-perfect-cover-bb-cream-spf42-50-ml-ton-21-svetlyij-bezh/p194");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }
}
