package org.lambda4j.function.tri;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

class ThrowableTriCharFunctionTest {
    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriCharFunction<String, Throwable> function =
                ThrowableTriCharFunction.of((value1, value2, value3) -> Character.toString(value1));
        Assertions.assertNotNull(function);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriCharFunction<String, Throwable> function =
                ThrowableTriCharFunction.of((ThrowableTriCharFunction<String, Throwable>) null);
        Assertions.assertNull(function);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void lift_givenExpression_returnsFunctionalInterface(String ret) {
        ThrowableTriCharFunction<Optional<String>, Throwable> function =
                ThrowableTriCharFunction.lift((value1, value2, value3) -> ret);
        Assertions.assertNotNull(function);
        Optional<String> optional = Assertions.assertDoesNotThrow(() -> function.applyThrows('c', 'c', 'c'));
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
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableTriCharFunction.lift(null));
    }
}
