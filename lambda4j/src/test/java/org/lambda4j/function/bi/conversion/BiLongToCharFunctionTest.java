package org.lambda4j.function.bi.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiLongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiLongToCharFunction function = BiLongToCharFunction.of((value1, value2) -> 'c');
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiLongToCharFunction function = BiLongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}