## DecimalInput

#### hasValidDecimalPoint()

Changed condition to verify there are **1 or 2** chunks and that all chunks are not empty.

#### getRegexOf()

Added backslash to escape DECIMAL character constant ('.').

#### getAllChunks()

Added -1 second argument to .split() to preserve trailing empty strings. This allows detection of invalid "123." case.

#### hasValidPadding()

Changed default return of ternary operator from **false** to **true** to properly handle integer exponents (only check "left" part of number when there is no right part)

#### isNumberPositive

Changed secondary condition from **number[0] == '+'** to **number[0] != '-'** to express that numbers are positive when no sign is present.