package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanToShortFunction<Throwable> function = ThrowableBooleanToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanToShortFunction<Throwable> function = ThrowableBooleanToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}
