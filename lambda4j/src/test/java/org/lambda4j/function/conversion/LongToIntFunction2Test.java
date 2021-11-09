package org.lambda4j.function.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LongToIntFunction2Test {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongToIntFunction2 function = LongToIntFunction2.of(value -> 0);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongToIntFunction2 function = LongToIntFunction2.of(null);
        Assertions.assertNull(function);
    }
}