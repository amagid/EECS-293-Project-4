package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.FloatingPointDriver.FloatingPointDriverTestHook;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;


/** Some example tests of parser **/
public class FloatingPointDriverTest {

	// For using hook methods that are not object-specific
	private static final FloatingPointDriverTestHook floatingPointDriverHook = new FloatingPointDriver.FloatingPointDriverTestHook();

	@Test
	public void test_get_floating_point_parser_valid_input() {
		String input = "1.0";
		FloatingPointParser result = floatingPointDriverHook.getFloatingPointParser(input);

		assertTrue(result.isValidInput());
	}

	@Test(expected = NumberFormatException.class)
	public void test_get_floating_point_parser_internal_whitespace() {
		String input = "1 . 0";
		FloatingPointParser result = floatingPointDriverHook.getFloatingPointParser(input);
	}

	@Test(expected = NumberFormatException.class)
	public void test_get_floating_point_parser_only_whitespace() {
		String input = "    ";
		FloatingPointParser result = floatingPointDriverHook.getFloatingPointParser(input);
	}
}