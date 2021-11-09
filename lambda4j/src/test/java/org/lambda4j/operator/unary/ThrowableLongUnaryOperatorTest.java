package org.lambda4j.operator.unary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableLongUnaryOperatorTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableLongUnaryOperator<Exception> operator = ThrowableLongUnaryOperator.of(value -> value);
        Assertions.assertNotNull(operator);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableLongUnaryOperator<Exception> operator = ThrowableLongUnaryOperator.of(null);
        Assertions.assertNull(operator);
    }
}