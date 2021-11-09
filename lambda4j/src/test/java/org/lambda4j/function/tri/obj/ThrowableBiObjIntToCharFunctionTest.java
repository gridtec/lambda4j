package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjIntToCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjIntToCharFunction<String, String, Exception> function =
                ThrowableBiObjIntToCharFunction.of((t, u, value) -> t.charAt(0));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjIntToCharFunction<String, String, Exception> function =
                ThrowableBiObjIntToCharFunction.of(null);
        Assertions.assertNull(function);
    }
}