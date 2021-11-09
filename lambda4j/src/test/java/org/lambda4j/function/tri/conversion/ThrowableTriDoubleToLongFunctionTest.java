package org.lambda4j.function.tri.conversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriDoubleToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriDoubleToLongFunction<Throwable> function =
                ThrowableTriDoubleToLongFunction.of((value1, value2, value3) -> 0L);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriDoubleToLongFunction<Throwable> function = ThrowableTriDoubleToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}
