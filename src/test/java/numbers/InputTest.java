package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.DecimalInput.DecimalInputTestHook;
import numbers.FloatingPointDriver.FloatingPointDriverTestHook;
import numbers.FloatingPointParser.FloatingPointParserTestHook;


/** Some example tests of parser **/
public class InputTest {

	// For using hook methods that are not object-specific
	private static final DecimalInputTestHook decimalInputHook = new DecimalInput("").new DecimalInputTestHook();
	private static final FloatingPointParserTestHook floatingPointParserHook = new FloatingPointParser.FloatingPointParserTestHook();
	private static final FloatingPointDriverTestHook floatingPointDriverHook = new FloatingPointDriver.FloatingPointDriverTestHook();

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

	/* Test 1.2 -> valid */
	@Test
	public void test_simulated_input() {
		String input = "1.2\n";
		String result = floatingPointDriverHook.simulateInput(input);
		System.out.println("============" + result);
		assertTrue(result == input);
	}
}
