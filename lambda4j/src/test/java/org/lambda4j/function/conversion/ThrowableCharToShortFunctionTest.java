package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharToShortFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharToShortFunction<Exception> function = ThrowableCharToShortFunction.of(value -> (short) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharToShortFunction<Exception> function = ThrowableCharToShortFunction.of(null);
        Assertions.assertNull(function);
    }
}