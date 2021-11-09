package org.lambda4j.consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanConsumer<Throwable> consumer = ThrowableBooleanConsumer.of(Assertions::assertNotNull);
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanConsumer<Throwable> consumer = ThrowableBooleanConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}
