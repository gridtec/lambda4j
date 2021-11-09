package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriIntToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriIntToByteFunction<Exception> function =
                ThrowableTriIntToByteFunction.of((value1, value2, value3) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriIntToByteFunction<Exception> function = ThrowableTriIntToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}