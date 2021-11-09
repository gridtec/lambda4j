package org.lambda4j.function.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjDoubleToLongFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjDoubleToLongFunction<String> function = ObjDoubleToLongFunction.of((t, value) -> Long.parseLong(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjDoubleToLongFunction<String> function = ObjDoubleToLongFunction.of(null);
        Assertions.assertNull(function);
    }
}