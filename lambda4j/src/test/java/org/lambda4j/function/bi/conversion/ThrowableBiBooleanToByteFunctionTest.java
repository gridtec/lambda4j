package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiBooleanToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiBooleanToByteFunction<Throwable> function =
                ThrowableBiBooleanToByteFunction.of((value1, value2) -> (byte) 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiBooleanToByteFunction<Throwable> function = ThrowableBiBooleanToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}
