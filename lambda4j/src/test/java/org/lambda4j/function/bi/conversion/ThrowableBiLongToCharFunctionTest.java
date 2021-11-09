package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiLongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiLongToCharFunction<Throwable> function = ThrowableBiLongToCharFunction.of((value1, value2) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiLongToCharFunction<Throwable> function = ThrowableBiLongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}
