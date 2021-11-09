package org.lambda4j.operator.binary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableFloatBinaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableFloatBinaryOperator<Exception> operator = ThrowableFloatBinaryOperator.of((value1, value2) -> value1);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableFloatBinaryOperator<Exception> operator = ThrowableFloatBinaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}