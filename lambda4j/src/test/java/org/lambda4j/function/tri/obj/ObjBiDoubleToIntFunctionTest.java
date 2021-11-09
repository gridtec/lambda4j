package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjBiDoubleToIntFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiDoubleToIntFunction<String> function =
                ObjBiDoubleToIntFunction.of((t, value1, value2) -> Integer.parseInt(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiDoubleToIntFunction<String> function = ObjBiDoubleToIntFunction.of(null);
        Assertions.assertNull(function);
    }
}