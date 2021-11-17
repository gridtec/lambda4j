/*
 * Copyright (c) 2021 The lambda4j authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lambda4j.predicate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableCharPredicate<Throwable> predicate = ThrowableCharPredicate.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableCharPredicate<Throwable> predicate = ThrowableCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableCharPredicate.call(value -> false, 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableCharPredicate.call(null, 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableCharPredicate<Throwable> predicate = ThrowableCharPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows('c'));
            Assertions.assertFalse(predicate.testThrows('c'));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableCharPredicate<Throwable> predicate = ThrowableCharPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows('c')));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableCharPredicate<Throwable> predicate = ThrowableCharPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c')));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableCharPredicate<Throwable> predicate = ThrowableCharPredicate.isEqual('c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows('c')));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        ThrowableCharPredicate<Throwable> predicate = ThrowableCharPredicate.isEqual('d');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c')));
    }
}
