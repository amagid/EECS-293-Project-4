package numbers;

import static org.junit.Assert.*;

import org.junit.Test;

import numbers.FloatingPointParser.FloatingPointParserTestHook;


/** Some example tests of parser **/
public class FloatingPointParserTest {

	// For using hook methods that are not object-specific
	private static final FloatingPointParserTestHook floatingPointParserHook = new FloatingPointParser.FloatingPointParserTestHook();

    /** constructor & getExpo tests **/
    
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
        DecimalInput generatedExpo = floatingPointParserHook.getExpo("1e2");
        DecimalInput expectedExpo = new DecimalInput("2");

        assertEquals(generatedExpo.toString(), expectedExpo.toString());
    }
    
    @Test
    public void test_constructor_recognizes_upper_case_E() {
        DecimalInput generatedExpo = floatingPointParserHook.getExpo("1E2");
        DecimalInput expectedExpo = new DecimalInput("2");

        assertEquals(generatedExpo.toString(), expectedExpo.toString());
    }

    /** parseDouble tests */
    
    @Test
    public void test_parse_double_base_and_exponent() {
        FloatingPointParser parser = FloatingPointParser.build("1e2");
        Double expected = 1e2;
        Double result = parser.parseDouble();

        assertEquals(result, expected);
    }

    /** isValidInput tests */
    
    @Test
    public void test_is_valid_input_valid_input() {
        FloatingPointParser parser = FloatingPointParser.build("1e2");
        boolean expected = true;
        boolean result = parser.isValidInput();

        assertEquals(result, expected);
    }

    /** containsAtLeastOneFloatingChunk tests */
    
    @Test
    public void test_contains_at_least_one_floating_chunk_valid_input() {
        boolean expected = true;
        boolean result = floatingPointParserHook.containsAtLeastOneFloatingChunk("1e2");

        assertEquals(result, expected);
    }

    /** hasValidBase tests */
    
    @Test
    public void test_has_valid_base_valid_input() {
        boolean expected = true;
        boolean result = floatingPointParserHook.hasValidBase("1e2");

        assertEquals(result, expected);
    }

    /** hasValidIntegerExponent tests */
    
    @Test
    public void test_has_valid_integer_exponent_valid_input() {
        boolean expected = true;
        boolean result = floatingPointParserHook.hasValidIntegerExponent("1e2");

        assertEquals(result, expected);
    }

    /** build tests */
    
    @Test
    public void test_build_null_input() {
        FloatingPointParser expected = floatingPointParserHook.getInvalidParser();
        FloatingPointParser result = FloatingPointParser.build(null);

        assertEquals(result, expected);
    }
    
    @Test
    public void test_build_valid_input() {
        boolean expected = true;
        boolean result = FloatingPointParser.build("1e2").isValidInput();

        assertEquals(result, expected);
    }


}