package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.DecimalInput.DecimalInputTestHook;



public class DecimalInputTest {

	// For using hook methods that are not object-specific
	private static final DecimalInputTestHook decimalInputHook = new DecimalInput("").new DecimalInputTestHook();

    /** constructor tests */
    
    @Test
    public void test_constructor_assigns_isPositive_and_number() {
        DecimalInput input = new DecimalInput("1.0");

        assertTrue(decimalInputHook.getIsPositive(input));
        assertEquals(decimalInputHook.getNumber(input), "1.0");
    }

	/** hasValidMiddlePadding tests **/
	/* Example: 1_234 -> valid */
	@Test
	public void test_padding_nominal() {
		assertTrue(decimalInputHook.hasValidMiddlePadding("1_234"));
	}

	/* Example: 1__234 -> valid */
	@Test
	public void test_padding_long_underscore() {
		assertTrue(decimalInputHook.hasValidMiddlePadding("1__234"));
	}

	/* Example: 12_34 -> invalid */
	@Test
	public void test_padding_bad_underscore_position_3() {
		assertFalse(decimalInputHook.hasValidMiddlePadding("12_34"));
	}

	/* Example: 123_4 -> invalid */
	@Test
	public void test_padding_bad_underscore_position_4() {
		assertFalse(decimalInputHook.hasValidMiddlePadding("123_4"));
	}

    // Testing to ensure underscore validation is stable in larger numbers
	/* Example: 1_234_567_898_765_432 -> valid */
	@Test
	public void test_padding_good_underscore_big_number_position_1() {
		assertTrue(decimalInputHook.hasValidMiddlePadding("1_234_567_898_765_432"));
    }
    
	/* Example: 12_345_678_987_654_32 -> invalid */
	@Test
	public void test_padding_bad_underscore_big_number_position_2() {
		assertFalse(decimalInputHook.hasValidMiddlePadding("12_345_678_987_654_32"));
    }
    
	/* Example: 123_456_789_876_543_2 -> invalid */
	@Test
	public void test_padding_bad_underscore_big_number_position_3() {
		assertFalse(decimalInputHook.hasValidMiddlePadding("123_456_789_876_543_2"));
	}
    
	/* Example: 12_34_567_898_765_432 -> invalid */
	@Test
	public void test_padding_bad_underscore_big_number_mostly_good() {
		assertFalse(decimalInputHook.hasValidMiddlePadding("12_34_567_898_765_432"));
	}

	/* Example: _1_234 -> invalid */
	@Test
	public void test_padding_leading_underscore() {
		assertFalse(decimalInputHook.hasValidMiddlePadding("_1_234"));
    }

    /** hasValidDecimalPoint tests **/
	@Test
	public void test_has_valid_decimal_point_valid_number() {
		Boolean result = decimalInputHook.hasValidDecimalPoint("1.2");

		assertTrue(result);
    }

	@Test
	public void test_has_valid_decimal_point_no_point() {
		Boolean result = decimalInputHook.hasValidDecimalPoint("12");

		assertTrue(result);
    }

	@Test
	public void test_has_valid_decimal_point_only_point() {
		Boolean result = decimalInputHook.hasValidDecimalPoint(".");

		assertFalse(result);
    }

	@Test
	public void test_has_valid_decimal_point_no_right_digit() {
		Boolean result = decimalInputHook.hasValidDecimalPoint("1.");

		assertFalse(result);
    }

	@Test
	public void test_has_valid_decimal_point_no_left_digit() {
		Boolean result = decimalInputHook.hasValidDecimalPoint(".1");

		assertFalse(result);
    }

	@Test
	public void test_has_valid_decimal_point_zero_padded() {
		Boolean result = decimalInputHook.hasValidDecimalPoint("00001.20000");

		assertTrue(result);
    }

	@Test
	public void test_has_valid_decimal_point_extra_point() {
		Boolean result = decimalInputHook.hasValidDecimalPoint("1.2.3");

		assertFalse(result);
    }

    /** hasValidPadding tests **/

    @Test
    public void test_has_valid_padding_no_padding() {
        boolean result = decimalInputHook.hasValidPadding("1.2");

        assertTrue(result);
    }
    
    @Test
    public void test_has_valid_padding_invalid_front_padding() {
        boolean result = decimalInputHook.hasValidPadding("1_02.3");

        assertFalse(result);
    }
    
    @Test
    public void test_has_valid_padding_invalid_left_padding() {
        boolean result = decimalInputHook.hasValidPadding("___1.2");

        assertFalse(result);
    }
    
    @Test
    public void test_has_valid_padding_invalid_back_padding() {
        boolean result = decimalInputHook.hasValidPadding("1.2_345");

        assertFalse(result);
    }
    
    @Test
    public void test_has_valid_padding_valid_front_padding() {
        boolean result = decimalInputHook.hasValidPadding("01_234_567.2");

        assertTrue(result);
    }

    /** isNumberPositive tests **/
    
    @Test
    public void test_is_number_positive_zero() {
        boolean result = decimalInputHook.isNumberPositive("0");

        assertTrue(result);
    }
    
    @Test
    public void test_is_number_positive_short_int() {
        boolean result = decimalInputHook.isNumberPositive("12");

        assertTrue(result);
    }
    
    @Test
    public void test_is_number_positive_long_int() {
        boolean result = decimalInputHook.isNumberPositive("12345678987654321");

        assertTrue(result);
    }
    
    @Test
    public void test_is_number_positive_with_plus() {
        boolean result = decimalInputHook.isNumberPositive("+0");

        assertTrue(result);
    }
    
    @Test
    public void test_is_number_positive_with_minus() {
        boolean result = decimalInputHook.isNumberPositive("-0");

        assertFalse(result);
    }
    
    @Test
    public void test_is_number_positive_empty() {
        boolean result = decimalInputHook.isNumberPositive("");

        assertFalse(result);
    }
    
    @Test
    public void test_is_number_positive_only_minus() {
        boolean result = decimalInputHook.isNumberPositive("-");

        assertFalse(result);
    }

    /** removeSign tests **/

    @Test
    public void test_remove_sign_empty_input() {
        String result = decimalInputHook.removeSign("");

        assertEquals(result, "");
    }

    @Test
    public void test_remove_sign_only_plus() {
        String result = decimalInputHook.removeSign("+");

        assertEquals(result, "");
    }

    @Test
    public void test_remove_sign_only_minus() {
        String result = decimalInputHook.removeSign("-");

        assertEquals(result, "");
    }

    @Test
    public void test_remove_sign_short_int_no_sign() {
        String result = decimalInputHook.removeSign("1");

        assertEquals(result, "1");
    }

    @Test
    public void test_remove_sign_short_int_sign() {
        String result = decimalInputHook.removeSign("+1");

        assertEquals(result, "1");
    }

    @Test
    public void test_remove_sign_long_int_no_sign() {
        String result = decimalInputHook.removeSign("12345678987654321");

        assertEquals(result, "12345678987654321");
    }
}