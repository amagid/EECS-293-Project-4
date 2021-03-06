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

    /** toString tests */
    
    @Test
    public void test_is_positive_decimal_input() {
        DecimalInput input = new DecimalInput("1.0");

        assertEquals(input.toString(), "+1.0");
    }
    
    @Test
    public void test_is_positive_negative_decimal_input() {
        DecimalInput input = new DecimalInput("-1.0");

        assertEquals(input.toString(), "-1.0");
    }

    /** isInteger tests */
    
    @Test
    public void test_is_integer_integer() {
        DecimalInput input = new DecimalInput("1");

        assertTrue(input.isInteger());
    }
    
    @Test
    public void test_is_integer_decimal() {
        DecimalInput input = new DecimalInput("1.0");

        assertFalse(input.isInteger());
    }

    /** isValid tests */
    
    @Test
    public void test_is_valid_valid_input() {
        DecimalInput input = new DecimalInput("1");

        assertTrue(input.isValid());
    }
    
    @Test
    public void test_is_valid_invalid_input() {
        DecimalInput input = new DecimalInput("invalid");

        assertFalse(input.isValid());
    }

    /** hasValidChars tests */
    
    @Test
    public void test_has_valid_chars_valid_input() {
        DecimalInput input = new DecimalInput("1");

        assertTrue(decimalInputHook.hasValidChars(input));
    }
    
    @Test
    public void test_has_valid_chars_invalid_input() {
        DecimalInput input = new DecimalInput("invalid");

        assertFalse(decimalInputHook.hasValidChars(input));
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

    /** splitChunks tests */
    
    @Test
    public void test_get_all_chunks_normal_input() {
        DecimalInput input = new DecimalInput("1.0");
        String[] result = decimalInputHook.splitChunks(input);

        assertEquals(result.length, 2);
        assertEquals(result[0], "1");
        assertEquals(result[1], "0");
    }
    
    @Test
    public void test_get_all_chunks_integer_input() {
        DecimalInput input = new DecimalInput("1");
        String[] result = decimalInputHook.splitChunks(input);

        assertEquals(result.length, 1);
        assertEquals(result[0], "1");
    }
    
    @Test
    public void test_get_all_chunks_does_not_validate_num_chunks() {
        DecimalInput input = new DecimalInput("1.2.3.4");
        String[] result = decimalInputHook.splitChunks(input);

        assertEquals(result.length, 4);
        assertEquals(result[0], "1");
        assertEquals(result[1], "2");
        assertEquals(result[2], "3");
        assertEquals(result[3], "4");
    }

    /** hasValidLeadingPadding tests **/

    @Test
    public void test_has_valid_leading_padding_no_padding() {
        boolean result = decimalInputHook.hasValidLeadingPadding("1.2");

        assertTrue(result);
    }
    
    @Test
    public void test_has_valid_leading_padding_invalid_front_padding() {
        boolean result = decimalInputHook.hasValidLeadingPadding("_1.2");

        assertFalse(result);
    }
    
    @Test
    public void test_has_valid_leading_padding_invalid_middle_padding() {
        boolean result = decimalInputHook.hasValidLeadingPadding("1_._2");

        assertFalse(result);
    }

    /** hasNoEdgePadding tests **/

    @Test
    public void test_has_no_edge_padding_no_padding() {
        boolean result = decimalInputHook.hasNoEdgePadding("1.2");

        assertTrue(result);
    }
    
    @Test
    public void test_has_no_edge_padding_invalid_front_padding() {
        boolean result = decimalInputHook.hasNoEdgePadding("_1.2");

        assertFalse(result);
    }
    
    @Test
    public void test_has_no_edge_padding_invalid_back_padding() {
        boolean result = decimalInputHook.hasNoEdgePadding("1.2_");

        assertFalse(result);
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

    /** isNotWithinString tests **/

    @Test
    public void test_is_not_within_string_empty_string() {
        boolean result = decimalInputHook.isNotWithinString('a', "");

        assertTrue(result);
    }
    
    @Test
    public void test_is_not_within_string_input_at_string_start() {
        boolean result = decimalInputHook.isNotWithinString('a', "abc");

        assertFalse(result);
    }
    
    @Test
    public void test_is_not_within_string_input_at_string_end() {
        boolean result = decimalInputHook.isNotWithinString('a', "cba");

        assertFalse(result);
    }
    
    @Test
    public void test_is_not_within_string_input_in_string_middle() {
        boolean result = decimalInputHook.isNotWithinString('a', "bac");

        assertFalse(result);
    }
    
    @Test
    public void test_is_not_within_string_input_not_in_string() {
        boolean result = decimalInputHook.isNotWithinString('a', "bcd");

        assertTrue(result);
    }

    /** charToRegex tests **/

    @Test
    public void test_get_regex_of_basic_character() {
        String result = decimalInputHook.charToRegex('a');

        assertEquals(result, "a");
    }
    
    @Test
    public void test_get_regex_of_escapes_period() {
        String result = decimalInputHook.charToRegex('.');

        assertEquals(result, "\\.");
    }
}