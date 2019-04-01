## DecimalInput

#### hasValidDecimalPoint()

Changed condition to verify there are **1 or 2** chunks and that all chunks are not empty.

#### getRegexOf()

Added backslash to escape DECIMAL character constant ('.').

#### getAllChunks()

Added -1 second argument to .split() to preserve trailing empty strings. This allows detection of invalid "123." case.

#### hasValidPadding()

Changed default return of ternary operator from **false** to **true** to properly handle integer exponents (only check "left" part of number when there is no right part)

#### isNumberPositive()

Changed secondary condition from **number[0] == '+'** to **number[0] != '-'** to express that numbers are positive when no sign is present.

#### removeSign()

Added **'+'** to list of signs so it will be properly recognized and removed from numbers.

## FloatingPointParser

#### DEFAULT_EXPONENT

Changed default exponent to **0** instead of **1** since when no exponent is supplied, the number should be multiplied by **1**, not **10**.

#### Constructor

Changed EXPONENTIAL to a **char[] EXPONENTIALS** containing both **'e'** and **'E'** so that capital E exponents will be properly recognized." Updated constructor and parseDouble to work with this new array format.

## FloatingPointDriver

#### getFloatingPointParser

Modified whitespace check to throw NumberFormatException when number contains internally embedded whitespace.