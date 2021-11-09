package org.lambda4j.consumer.bi.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ObjByteConsumerTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjByteConsumer<String> consumer = ObjByteConsumer.of((t, value) -> {
            Assertions.assertNotNull(t);
            Assertions.assertNotNull(value);
        });
        Assertions.assertNotNull(consumer);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjByteConsumer<String> consumer = ObjByteConsumer.of(null);
        Assertions.assertNull(consumer);
    }
}