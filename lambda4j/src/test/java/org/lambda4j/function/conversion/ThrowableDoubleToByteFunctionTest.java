package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableDoubleToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableDoubleToByteFunction<Throwable> function = ThrowableDoubleToByteFunction.of(value -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableDoubleToByteFunction<Throwable> function = ThrowableDoubleToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
