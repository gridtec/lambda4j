package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBiLongToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBiLongToCharFunction<String, Exception> function =
                ThrowableObjBiLongToCharFunction.of((t, value1, value2) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBiLongToCharFunction<String, Exception> function = ThrowableObjBiLongToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}