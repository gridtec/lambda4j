package org.lambda4j.supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBooleanSupplierTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBooleanSupplier<Exception> supplier = ThrowableBooleanSupplier.of(() -> false);
        Assertions.assertNotNull(supplier);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBooleanSupplier<Exception> supplier = ThrowableBooleanSupplier.of(null);
        Assertions.assertNull(supplier);
    }
}