package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.DecimalInput.DecimalInputTestHook;



public class DecimalInputTest {

	// For using hook methods that are not object-specific
	private static final DecimalInputTestHook decimalInputHook = new DecimalInput("").new DecimalInputTestHook();

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
	public void test_padding_bad_underscore() {
		assertFalse(decimalInputHook.hasValidMiddlePadding("12_34"));
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
}