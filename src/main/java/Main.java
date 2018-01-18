
/**
 * @author Alexander Diachenko.
 */
public class Main {

    public static void main(String[] args) {
        Makeup makeup = new Makeup();
        final String price = makeup.getPrice("https://makeup.com.ua/pr11oduct/739/");
        System.out.println(price);
    }
}