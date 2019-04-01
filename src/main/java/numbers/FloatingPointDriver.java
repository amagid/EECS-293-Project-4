package numbers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.util.Optional;

/* Driver to build and run FloatingPointParser on input readers */
public final class FloatingPointDriver {
	
	public static void main(String[] args) {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		FloatingPointDriver driver = new FloatingPointDriver();
		Optional<Double> result = driver.runFloatingPointParser(input);
		printOutput(result);
	}

	// Prints out the given Double (or "Invalid Input" if given empty result)
	public final static void printOutput(Optional<Double> result) {
		System.out.println(result.isPresent() ? result.get() : "Invalid Input");
	}

	// Retrieves input from the given BufferedReader and parses it to a Double
	public final Optional<Double> runFloatingPointParser(BufferedReader input) {
		FloatingPointParser parser = getFloatingPointParser(input);
		return parser.isValidInput() ? Optional.of(parser.parseDouble()) : Optional.empty();
	}

	// Reads from the input reader and builds a parser
	private final FloatingPointParser getFloatingPointParser(BufferedReader input) {
		// Validate that input is not null
		assert input != null;
	
		// Attempt to read a line of input, throw error if no input
		FloatingPointParser parser = null;
		String line = getNonEmptyInputLine(input);

		// Clear whitespace from input line
		String number = clearWhitespace(line);

		// Ensure some non-whitespace input was found
		if (number.length() == 0) {
			throw new NumberFormatException("Received only whitespace.");
		}

		return FloatingPointParser.build(number);
	}

	private String getNonEmptyInputLine(BufferedReader input) {
		String line = null;
		try {
			line = input.readLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Ensure input was not empty
		if (line == null) {
			throw new IllegalArgumentException("No input detected.");
		}
		return line;
	}

	private String clearWhitespace(String inputLine) {
		StringBuilder builder = new StringBuilder();
		boolean foundNumber = false;
		boolean outOfNumber = false;
		for (int i = 0; i < inputLine.length(); i++) {
			if (!Character.isWhitespace(inputLine.charAt(i))) {
				builder.append(inputLine.charAt(i));
				foundNumber = true;
				if (outOfNumber) {
					throw new NumberFormatException("Illegal embedded whitespace in input.");
				}
			} else if (foundNumber && !outOfNumber) {
				outOfNumber = true;
			}
		}

		return builder.toString();		
	}
	
	public static class FloatingPointDriverTestHook {
		Double simulateInput(String input) { 
			System.setIn(new ByteArrayInputStream(input.getBytes()));

			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
					
			FloatingPointDriver driver = new FloatingPointDriver();
			Optional<Double> result = driver.runFloatingPointParser(inputReader);


			return result.isPresent() ? result.get() : null;
		}
	}
}
