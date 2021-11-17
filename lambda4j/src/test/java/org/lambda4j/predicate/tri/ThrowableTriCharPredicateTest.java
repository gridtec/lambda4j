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

package org.lambda4j.predicate.tri;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableTriCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriCharPredicate<Throwable> predicate =
                ThrowableTriCharPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriCharPredicate<Throwable> predicate = ThrowableTriCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableTriCharPredicate.call((value1, value2, value3) -> false, 'c', 'c', 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableTriCharPredicate.call(null, 'c', 'c', 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableTriCharPredicate<Throwable> predicate = ThrowableTriCharPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows('c', 'c', 'c'));
            Assertions.assertFalse(predicate.testThrows('c', 'c', 'c'));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableTriCharPredicate<Throwable> predicate = ThrowableTriCharPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows('c', 'c', 'c')));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableTriCharPredicate<Throwable> predicate = ThrowableTriCharPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c', 'c', 'c')));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableTriCharPredicate<Throwable> predicate = ThrowableTriCharPredicate.isEqual('c', 'c', 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows('c', 'c', 'c')));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableTriCharPredicate<Throwable> predicate = ThrowableTriCharPredicate.isEqual('d', 'c', 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c', 'c', 'c')));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableTriCharPredicate<Throwable> predicate = ThrowableTriCharPredicate.isEqual('c', 'd', 'c');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c', 'c', 'c')));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableTriCharPredicate<Throwable> predicate = ThrowableTriCharPredicate.isEqual('c', 'c', 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c', 'c', 'c')));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableTriCharPredicate<Throwable> predicate = ThrowableTriCharPredicate.isEqual('d', 'd', 'd');
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows('c', 'c', 'c')));
    }
}
