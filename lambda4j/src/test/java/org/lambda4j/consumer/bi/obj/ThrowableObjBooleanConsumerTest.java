package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableObjBooleanConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanConsumer<String, Exception> consumer = ThrowableObjBooleanConsumer.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanConsumer<String, Exception> consumer = ThrowableObjBooleanConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}