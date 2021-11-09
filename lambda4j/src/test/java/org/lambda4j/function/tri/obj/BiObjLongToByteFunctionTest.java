package org.lambda4j.function.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiObjLongToByteFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjLongToByteFunction<String, String> function =
                BiObjLongToByteFunction.of((t, u, value) -> Byte.parseByte(t));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjLongToByteFunction<String, String> function = BiObjLongToByteFunction.of(null);
        Assertions.assertNull(function);
    }
}