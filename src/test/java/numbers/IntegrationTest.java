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
		} catch (NullPointerException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

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

	@Test
	public void test_simulated_input_integer_with_exponent() {
		String input = "1e1";
		Double expected = 10.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_integer_with_capital_exponent() {
		String input = "1E1";
		Double expected = 10.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

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

	@Test
	public void test_simulated_input_large_base_no_commas() {
		String input = "1234567.0";
		Double expected = 1234567.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_large_base_commas() {
		String input = "1,234,567.0";
		Double expected = 1234567.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_large_base_underscores() {
		String input = "1_234_567.0";
		Double expected = 1234567.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_large_base_wide_underscores() {
		String input = "1__234______567.0";
		Double expected = 1234567.0;

		assertInvalidInput(input);
	}

	@Test
	public void test_simulated_input_invalid_commas() {
		String input = "1,2.0";
		
		assertInvalidInput(input);
	}

	@Test
	public void test_simulated_input_invalid_underscores() {
		String input = "1_2.0";
		
		assertInvalidInput(input);
	}

	@Test
	public void test_simulated_input_invalid_exponent() {
		String input = "1e1,0";

		assertInvalidInput(input);
	}

	@Test
	public void test_simulated_input_decimal_base_and_exponent() {
		String input = "2.0e+4";
		Double expected = 20000.0;

		Double result = floatingPointDriverHook.simulateInput(input);

		assertEquals(result, expected);
	}

	@Test
	public void test_simulated_input_decimal_exponent() {
		String input = "2.0e+4.2";
		
		assertInvalidInput(input);
	}

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

	@Test
	public void test_simulated_input_empty_base() {
		String input = "e4";
		
		assertInvalidInput(input);
	}

	@Test
	public void test_simulated_input_empty_exponent() {
		String input = "2.0e";
		
		assertInvalidInput(input);
	}
}