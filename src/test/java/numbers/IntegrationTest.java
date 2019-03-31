package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.DecimalInput.DecimalInputTestHook;
import numbers.FloatingPointDriver.FloatingPointDriverTestHook;
import numbers.FloatingPointParser.FloatingPointParserTestHook;


/** Some example tests of parser **/
public class IntegrationTest {

	// For using hook methods that are not object-specific
	private static final DecimalInputTestHook decimalInputHook = new DecimalInput("").new DecimalInputTestHook();
	private static final FloatingPointParserTestHook floatingPointParserHook = new FloatingPointParser.FloatingPointParserTestHook();
	private static final FloatingPointDriverTestHook floatingPointDriverHook = new FloatingPointDriver.FloatingPointDriverTestHook();

	/* Test 2e+5 -> valid */
	@Test
	public void test_simulated_input() {
		String input = "2e+5\n";
		String result = floatingPointDriverHook.simulateInput(input);
		System.out.println("============" + result);
		assertTrue(result == input);
	}
}