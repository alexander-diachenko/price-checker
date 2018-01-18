
/**
 * @author Alexander Diachenko.
 */
public class Main {

    public static void main(String[] args) {
        Makeup makeup = new Makeup();
        final String price = makeup.getPrice("https://makeup.com.ua/product/1801/#/option/393581/");
        System.out.println(price);
    }
}