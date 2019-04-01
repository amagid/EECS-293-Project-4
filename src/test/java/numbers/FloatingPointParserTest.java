package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.FloatingPointParser.FloatingPointParserTestHook;


/** Some example tests of parser **/
public class FloatingPointParserTest {

	// For using hook methods that are not object-specific
	private static final FloatingPointParserTestHook floatingPointParserHook = new FloatingPointParser.FloatingPointParserTestHook();

    /** default exponent tests **/
    
    @Test
    public void test_constructor_empty_input_assigns_zero_exponent() {
        DecimalInput generatedExpo = floatingPointParserHook.getDefaultGeneratedExponent();
        DecimalInput expectedExpo = new DecimalInput("0");

        assertTrue(generatedExpo.toString().equals(expectedExpo.toString()));
    }
}