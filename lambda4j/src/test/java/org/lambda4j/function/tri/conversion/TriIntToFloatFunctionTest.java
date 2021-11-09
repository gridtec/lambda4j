package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TriIntToFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        TriIntToFloatFunction function = TriIntToFloatFunction.of((value1, value2, value3) -> 0.0f);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        TriIntToFloatFunction function = TriIntToFloatFunction.of(null);
        Assertions.assertNull(function);
    }
}