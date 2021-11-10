package org.lambda4j.function.bi.obj;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class ThrowableObjBooleanFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableObjBooleanFunction<String, String, Throwable> function =
                ThrowableObjBooleanFunction.of((t, value) -> t);
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableObjBooleanFunction<String, String, Throwable> function = ThrowableObjBooleanFunction.of(null);
        Assertions.assertNull(function);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void lift_givenExpression_returnsFunctionalInterface(String ret) {
        ThrowableObjBooleanFunction<String, Optional<String>, Throwable> function =
                ThrowableObjBooleanFunction.lift((t, value) -> ret);
        Assertions.assertNotNull(function);
        Optional<String> optional = Assertions.assertDoesNotThrow(() -> function.applyThrows("", false));
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
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableObjBooleanFunction.lift(null));
    }
}
