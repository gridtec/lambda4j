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

class ThrowableTriLongPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableTriLongPredicate<Throwable> predicate =
                ThrowableTriLongPredicate.of((value1, value2, value3) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableTriLongPredicate.call((value1, value2, value3) -> false, 0L, 0L, 0L));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ThrowableTriLongPredicate.call(null, 0L, 0L, 0L));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows(0L, 0L, 0L));
            Assertions.assertFalse(predicate.testThrows(0L, 0L, 0L));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0L, 0L, 0L)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0L, 0L, 0L)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.isEqual(0L, 0L, 0L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows(0L, 0L, 0L)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.isEqual(1L, 0L, 0L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0L, 0L, 0L)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.isEqual(0L, 1L, 0L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0L, 0L, 0L)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.isEqual(0L, 0L, 1L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0L, 0L, 0L)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableTriLongPredicate<Throwable> predicate = ThrowableTriLongPredicate.isEqual(1L, 1L, 1L);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows(0L, 0L, 0L)));
    }
}
