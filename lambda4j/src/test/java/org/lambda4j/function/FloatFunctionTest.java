package org.lambda4j.function;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class FloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        FloatFunction<String> function = FloatFunction.of(Float::toString);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        FloatFunction<String> function = FloatFunction.of(null);
        Assertions.assertNull(function);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void lift_givenExpression_returnsFunctionalInterface(String ret) {
        FloatFunction<Optional<String>> function = FloatFunction.lift(value -> ret);
        Assertions.assertNotNull(function);
        Optional<String> optional = Assertions.assertDoesNotThrow(() -> function.apply(0.0f));
        Assertions.assertNotNull(optional);
        if (ret == null) {
            Assertions.assertFalse(optional.isPresent());
            Assertions.assertThrows(NoSuchElementException.class, optional::get);
        } else {
            Assertions.assertTrue(optional.isPresent());
            Assertions.assertEquals(ret, optional.get());
        }
    }

    @Test
    void lift_givenNull_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> FloatFunction.lift(null));
    }
}
