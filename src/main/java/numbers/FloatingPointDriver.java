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
		FloatingPointParser parser = parseInput(input);
		return parser.isValidInput() ? Optional.of(parser.parseDouble()) : Optional.empty();
	}

	// Reads from the input reader and builds a parser
	private final FloatingPointParser parseInput(BufferedReader input) {
		// Validate that input is not null
		assert input != null;
	
		// Attempt to read a line of input, throw error if no input
		FloatingPointParser parser = null;
		String line = getNonEmptyInputLine(input);

		// Clear whitespace from input line
		String number = trimEdgeWhitespace(line);

		// It's zero complexity now. You can rightly go eat a dick.
		validateNumberFormatting(number);

		return FloatingPointParser.build(number);
	}

	private void validateNumberFormatting(String number) {
		// Check for internal whitespace and reject input if there is any
		if (hasInternalWhitespace(number)) {
			throw new NumberFormatException("Illegal internal whitespace in input.");
		}

		// Ensure some non-whitespace input was found
		if (number.length() == 0) {
			throw new NumberFormatException("Received only whitespace.");
		}
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

	private String trimEdgeWhitespace(String inputLine) {
		StringBuilder builder = new StringBuilder();
		int endIndex = numberEndIndex(inputLine);
		boolean inNumber = false;
		for (int i = 0; i < endIndex; i++) {
			if (inNumber || !Character.isWhitespace(inputLine.charAt(i))) {
				inNumber = true;
				builder.append(inputLine.charAt(i));
			}
		}

		return builder.toString();		
	}

	private int numberEndIndex(String inputLine) {
		int endIndex = inputLine.length();

		while (endIndex > 0 && Character.isWhitespace(inputLine.charAt(endIndex - 1))) {
			endIndex--;
		}

		return endIndex;
	}

	private boolean hasInternalWhitespace(String number) {
		for (int i = 0; i < number.length(); i++) {
			if (Character.isWhitespace(number.charAt(i))) {
				return true;
			}
		}
		return false;
	}
	
	public static class FloatingPointDriverTestHook {
		Double simulateInput(String input) { 
			System.setIn(new ByteArrayInputStream(input.getBytes()));

			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
					
			FloatingPointDriver driver = new FloatingPointDriver();
			Optional<Double> result = driver.runFloatingPointParser(inputReader);


			return result.isPresent() ? result.get() : null;
		}

		FloatingPointParser getFloatingPointParser(String input) {
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(input.getBytes())));

			return new FloatingPointDriver().getFloatingPointParser(inputReader);
		}

		String trimEdgeWhitespace(String input) {
			return new FloatingPointDriver().trimEdgeWhitespace(input);
		}

		int numberEndIndex(String input) {
			return new FloatingPointDriver().numberEndIndex(input);
		}

		boolean hasInternalWhitespace(String input) {
			return new FloatingPointDriver().hasInternalWhitespace(input);
		}
	}
}
