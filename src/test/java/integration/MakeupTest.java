package integration;

import magazine.Magazine;
import magazine.Makeup;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Diachenko.
 */
public class MakeupTest {

    private WebDriver driver;
    private Magazine makeup;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", getResource("chromedriver.exe").getAbsolutePath());
        driver = new ChromeDriver();
        makeup = new Makeup(driver);
    }

    @Test
    public void getPriceTest_invalidUrl() {
        final String price = makeup.getPrice("qwe");
        assertEquals("Не правельный URL", price);
    }

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
    public void getPriceTest_notMakeup() {
        final String price = makeup.getPrice("http://www.google.com.ua/");
        assertEquals("Сайт не makeup", price);
    }

    @Test
    public void getPriceTest() {
        final String price = makeup.getPrice("https://makeup.com.ua/product/1801/#/option/393587/");
        assertThat(Integer.valueOf(price), CoreMatchers.instanceOf(Integer.class));
    }

    @After
    public void close() {
        driver.close();
        driver.quit();
    }

    private File getResource(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource("chromedriver.exe").getFile());
    }
}
