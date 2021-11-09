package org.lambda4j.function.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriBooleanFunction<String, Exception> function =
                ThrowableTriBooleanFunction.of((value1, value2, value3) -> Boolean.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriBooleanFunction<String, Exception> function =
                ThrowableTriBooleanFunction.of((ThrowableTriBooleanFunction<String, Exception>) null);
        Assertions.assertNull(function);
    }
}