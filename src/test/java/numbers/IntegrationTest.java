package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.FloatingPointDriver.FloatingPointDriverTestHook;


/** Integration Tests **/
public class IntegrationTest {

	// For using hook methods that are not object-specific
	private static final FloatingPointDriverTestHook floatingPointDriverHook = new FloatingPointDriver.FloatingPointDriverTestHook();

	private static void assertInvalidInput(String input) {
		try {
			Double result = floatingPointDriverHook.simulateInput(input);

			assertNull(result);
		} catch (NullPointerException e) {}
		 catch (NumberFormatException e) {}
		catch (Exception e) {
			fail();
		}
	}

	/** Numbers MUST contain digits */
	@Test
	public void test_simulated_input_empty() {
		String input = "";

		assertInvalidInput(input);
	}

	@Test
	public void test_simulated_input_integer() {
		String input = "0";
		
		assertInvalidInput(input);
	}

	/** Numbers MAY begin with + or - */
	@Test
	public void test_simulated_input_zero() {
		String input = "0.0";
		Double expected = 0.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_positive_zero() {
		String input = "+0.0";
		Double expected = 0.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_negative_zero() {
		String input = "-0.0";
		Double expected = -0.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	/** Numbers MUST contain decimal, exponent, or both */
	@Test
	public void test_simulated_input_integer_with_exponent() {
		String input = "1e1";
		Double expected = 10.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_decimal_base_and_exponent() {
		String input = "2.0e+4";
		Double expected = 20000.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	/** Exponents MUST begin with either e or E */
	@Test
	public void test_simulated_input_integer_with_capital_exponent() {
		String input = "1E1";
		Double expected = 10.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	/** Exponents MAY begin with + or - */

	@Test
	public void test_simulated_input_integer_with_plus_exponent() {
		String input = "1e+1";
		Double expected = 10.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_integer_with_minus_exponent() {
		String input = "1e-1";
		Double expected = 0.1;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	/** Numbers MAY be arbitrarily large */

	@Test
	public void test_simulated_input_large_base_no_commas() {
		String input = "12345678987654321.0";
		Double expected = 12345678987654321.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	/** Numbers MAY use underscores in place of commas */

	@Test
	public void test_simulated_input_large_base_underscores() {
		String input = "1_234_567.0";
		Double expected = 1234567.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	/** Numbers MUST NOT use multiple underscores in a row */

	@Test
	public void test_simulated_input_large_base_wide_underscores() {
		String input = "1__234______567.0";
		Double expected = 1234567.0;

		assertInvalidInput(input);
	}

	/** Numbers MUST NOT use underscores where they would be invalid in normal writing */

	@Test
	public void test_simulated_input_invalid_underscores() {
		String input = "1_2.0";
		
		assertInvalidInput(input);
	}
	
	@Test
	public void test_simulated_input_invalid_exponent_underscore() {
		String input = "1e1_0";

		assertInvalidInput(input);
	}

	/** Exponents MUST NOT have decimal points */

	@Test
	public void test_simulated_input_decimal_exponent() {
		String input = "2.0e+4.2";
		
		assertInvalidInput(input);
	}

	/** Decimal Points MUST have a digit to the left AND right */

	@Test
	public void test_simulated_input_no_right_digit() {
		String input = "2.";
		
		assertInvalidInput(input);
	}

	@Test
	public void test_simulated_input_no_left_digit() {
		String input = ".2";
		
		assertInvalidInput(input);
	}

	@Test
	public void test_simulated_input_no_digits() {
		String input = ".";
		
		assertInvalidInput(input);
	}

	/** Numbers MUST NOT be empty */

	@Test
	public void test_simulated_input_empty_base() {
		String input = "e4";
		
		assertInvalidInput(input);
	}

	/** Exponents MUST NOT be empty */

	@Test
	public void test_simulated_input_empty_exponent() {
		String input = "2.0e";
		
		assertInvalidInput(input);
	}

	/** Numbers MAY have whitespace to the left and right */

	@Test
	public void test_simulated_input_short_whitespace_left() {
		String input = " 1.0";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_short_whitespace_right() {
		String input = "1.0 ";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_short_whitespace_both() {
		String input = " 1.0 ";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_long_whitespace_left() {
		String input = "   \t   1.0";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_long_whitespace_right() {
		String input = "1.0   \t   ";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_long_whitespace_both() {
		String input = "   \t   1.0   \t   ";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	/** Numbers MUST NOT have whitespace embedded within them */

	@Test
	public void test_simulated_input_whitespace_inside() {
		String input = "1 . 0";
		
		assertInvalidInput(input);
	}

	/** Numbers MAY be padded with zeros */

	@Test
	public void test_simulated_input_left_pad_zeros_short() {
		String input = "01.0";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_right_pad_zeros_short() {
		String input = "1.00";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_left_pad_zeros_long() {
		String input = "00000000001.0";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_right_pad_zeros_long() {
		String input = "1.0000000000";
		Double expected = 1.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	/** Exponents MAY be left-padded with zeros */

	@Test
	public void test_simulated_input_exponent_left_pad_zeros_short() {
		String input = "1e01";
		Double expected = 10.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_exponent_left_pad_zeros_long() {
		String input = "1e00000000001";
		Double expected = 10.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}
}