package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiLongToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiLongToByteFunction<Throwable> function =
                ThrowableBiLongToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiLongToByteFunction<Throwable> function = ThrowableBiLongToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
