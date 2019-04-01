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
		FloatingPointParser parser = null;

		// Validate that input is not null
		if (input != null) {

			// Attempt to read a line of input
			String line = null;
			try {
				line = input.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			// Ensure input was not empty
			if (line == null)
				throw new IllegalArgumentException("No input detected.");
			else {

				// Clear whitespace from input
				StringBuilder builder = new StringBuilder();
				boolean foundNumber = false;
				boolean outOfNumber = false;
				for (int i = 0; i < line.length(); i++) {
					if (!Character.isWhitespace(line.charAt(i))) {
						builder.append(line.charAt(i));
						foundNumber = true;
						if (outOfNumber) {
							throw new NumberFormatException("Illegal embedded whitespace in input.");
						}
					} else if (foundNumber && !outOfNumber) {
						outOfNumber = true;
					}
				}

				// Ensure some non-whitespace input was found
				line = builder.toString();
				if (line.length() != 0) {
					// Shift exponential to upper case
					builder = new StringBuilder();
					for (int i = 0; i < line.length(); i++) {
						if (!Character.isUpperCase(line.charAt(i)))
							builder.append(Character.toUpperCase(line.charAt(i)));
						else
							builder.append(line.charAt(i));
					}
					// Initialize and store parser
					parser = FloatingPointParser.build(line);
				} else {
					throw new NumberFormatException("Received only whitespace.");
				}
			}
		}

		// Ensure some numerical input was received
		if (parser == null) {
			throw new IllegalArgumentException("No numerical input detected");
		} else {
			return parser;
		}
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
