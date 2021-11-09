package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiShortToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiShortToLongFunction<String> function =
                ObjBiShortToLongFunction.of((t, value1, value2) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiShortToLongFunction<String> function = ObjBiShortToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}