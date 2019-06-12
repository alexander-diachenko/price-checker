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
        final String price = korea.getPrice("https://korea.in.ua/missha-all-around-safe-block-essence-sun-spf45-pa-solntsezaschitnaya-essentsiya-dlya-litsa-i-tela/p1029");
        assertEquals("Нет в наличии", price);
    }

    @Test
    public void getDiscountPriceTest() {
        final String price = korea.getPrice("https://korea.in.ua/elizavecca-green-piggy-collagen-jella-pack-kollagenovaya-maska/p51");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }

    @Test
    public void getNormalPriceTest() {
        final String price = korea.getPrice("https://korea.in.ua/elizavecca-milky-piggy-carbonated-bubble-clay-mask-glinyanaya-pennaya-maska/p27");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }
}
