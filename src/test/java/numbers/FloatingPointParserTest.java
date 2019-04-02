package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.FloatingPointParser.FloatingPointParserTestHook;


/** Some example tests of parser **/
public class FloatingPointParserTest {

	// For using hook methods that are not object-specific
	private static final FloatingPointParserTestHook floatingPointParserHook = new FloatingPointParser.FloatingPointParserTestHook();

    /** constructor tests **/
    
    @Test
    public void test_constructor_empty_input_assigns_zero_exponent() {
        DecimalInput generatedExpo = floatingPointParserHook.getExpo("");
        DecimalInput expectedExpo = new DecimalInput("0");

        assertTrue(generatedExpo.toString().equals(expectedExpo.toString()));
    }
    
    @Test
    public void test_constructor_no_expo_assigns_default_exponent() {
        DecimalInput generatedExpo = floatingPointParserHook.getExpo("1.0");
        DecimalInput expectedExpo = floatingPointParserHook.getDefaultExponent();

        assertEquals(generatedExpo, expectedExpo);
    }
    
    @Test
    public void test_constructor_only_base_assigns_base() {
        DecimalInput generatedBase = floatingPointParserHook.getBase("1.0");
        DecimalInput expectedBase = new DecimalInput("1.0");

        assertEquals(generatedBase.toString(), expectedBase.toString());
    }
    
    @Test
    public void test_constructor_recognizes_lower_case_e() {
        DecimalInput generatedExpo = floatingPointParserHook.getExpo("1.0e2");
        DecimalInput expectedExpo = new DecimalInput("2");

        assertEquals(generatedExpo.toString(), expectedExpo.toString());
    }
    
    @Test
    public void test_constructor_recognizes_upper_case_E() {
        DecimalInput generatedExpo = floatingPointParserHook.getExpo("1.0E2");
        DecimalInput expectedExpo = new DecimalInput("2");

        assertEquals(generatedExpo.toString(), expectedExpo.toString());
    }


}