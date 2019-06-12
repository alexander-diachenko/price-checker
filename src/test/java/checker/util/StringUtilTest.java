package checker.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Alexander Diachenko
 */
public class StringUtilTest {

    @Test
    public void shouldReturnSimpleNumberWhenPassedSimpleNumber() {
        String formatted = StringUtil.formatPrice("1");
        assertEquals("1", formatted);
    }

    @Test
    public void shouldReturnSimpleNumberWhenPassedSimpleString() {
        String formatted = StringUtil.formatPrice("a1b");
        assertEquals("1", formatted);
    }

    @Test
    public void shouldReturnSimpleNumberWhenPassedStringWithSpaces() {
        String formatted = StringUtil.formatPrice("a 1 b");
        assertEquals("1", formatted);
    }

    @Test
    public void shouldReturnDecimalWhenPassedMixedString() {
        String formatted = StringUtil.formatPrice("a 1 b.z 2");
        assertEquals("1.2", formatted);
    }

    @Test
    public void shouldReturnDecimalWhenPassedMixedStringWithEndingDot() {
        String formatted = StringUtil.formatPrice("aa 345 ff.");
        assertEquals("345", formatted);
    }

    @Test
    public void shouldReturnDecimalWhenPassedMixedStringWithLeadingDot() {
        String formatted = StringUtil.formatPrice("aa. 345 ff");
        assertEquals("345", formatted);
    }
}
