package numbers;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import numbers.DecimalInput.DecimalInputTestHook;
import numbers.FloatingPointDriver.FloatingPointDriverTestHook;
import numbers.FloatingPointParser.FloatingPointParserTestHook;


/** Some example tests of parser **/

@RunWith(Parameterized.class)
public class ParametrizedInputTest {

	// For using hook methods that are not object-specific
	private static final DecimalInputTestHook decimalInputHook = new DecimalInput("").new DecimalInputTestHook();
	private static final FloatingPointParserTestHook floatingPointParserHook = new FloatingPointParser.FloatingPointParserTestHook();
	private static final FloatingPointDriverTestHook floatingPointDriverHook = new FloatingPointDriver.FloatingPointDriverTestHook();

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                 {"1.2" , true }, { "12", true }, { ".", false }, { "1.", false }, { ".1", false }, { "00001.20000", true }, { "1.2.3", false }
            });
    }

    @Parameter
    public String input;

    @Parameter(1)
    public Boolean expected;

	@Test
	public void test_has_valid_decimal_point_no_point() {
		Boolean result = decimalInputHook.hasValidDecimalPoint(this.input);

		assertTrue(result == this.expected);
    }
}
