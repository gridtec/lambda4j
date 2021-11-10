package org.lambda4j.function.bi.obj;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class ThrowableObjFloatFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjFloatFunction<String, String, Throwable> function = ThrowableObjFloatFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjFloatFunction<String, String, Throwable> function = ThrowableObjFloatFunction.of(null);
        Assertions.assertNull(function);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void lift_givenExpression_returnsFunctionalInterface(String ret) {
        ThrowableObjFloatFunction<String, Optional<String>, Throwable> function =
                ThrowableObjFloatFunction.lift((t, value) -> ret);
        Assertions.assertNotNull(function);
        Optional<String> optional = Assertions.assertDoesNotThrow(() -> function.applyThrows("", 0.0f));
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
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjFloatFunction.lift(null));
    }
}
