package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.DecimalInput.DecimalInputTestHook;



public class DecimalInputTest {

	// For using hook methods that are not object-specific
	private static final DecimalInputTestHook decimalInputHook = new DecimalInput("").new DecimalInputTestHook();

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