## DecimalInput

#### hasValidDecimalPoint()

Changed condition to verify there are **1 or 2** chunks and that all chunks are not empty.

#### getRegexOf()

Added backslash to escape DECIMAL character constant ('.').

#### getAllChunks()

Added -1 second argument to .split() to preserve trailing empty strings. This allows detection of invalid "123." case.