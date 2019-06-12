package checker.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Alexander Diachenko
 */
public class StringUtil {

    public static String formatPrice(String price) {
        StringBuilder result = new StringBuilder();
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(price);
        while(m.find()) {
            result.append(m.group());
        }
        return result.toString();
    }
}
