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

package org.lambda4j.predicate.tri.obj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThrowableBiObjFloatPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate =
                ThrowableBiObjFloatPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate = ThrowableBiObjFloatPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjFloatPredicate.call((t, u, value) -> false, "", "", 0.0f));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjFloatPredicate.call((t, u, value) -> false, null, "", 0.0f));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(ThrowableBiObjFloatPredicate.call((t, u, value) -> false, "", null, 0.0f));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> ThrowableBiObjFloatPredicate.call(null, "", "", 0.0f));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate = ThrowableBiObjFloatPredicate.constant(ret);
        Assertions.assertDoesNotThrow(() -> {
            Assertions.assertEquals(ret, predicate.testThrows("", "", 0.0f));
            Assertions.assertFalse(predicate.testThrows("", "", 0.0f));
        });
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate = ThrowableBiObjFloatPredicate.alwaysTrue();
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", 0.0f)));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate = ThrowableBiObjFloatPredicate.alwaysFalse();
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0f)));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate =
                ThrowableBiObjFloatPredicate.isEqual("", "", 0.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertTrue(predicate.testThrows("", "", 0.0f)));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate =
                ThrowableBiObjFloatPredicate.isEqual("first", "", 0.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0f)));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate =
                ThrowableBiObjFloatPredicate.isEqual("", "second", 0.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0f)));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate =
                ThrowableBiObjFloatPredicate.isEqual("", "", 1.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0f)));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ThrowableBiObjFloatPredicate<String, String, Throwable> predicate =
                ThrowableBiObjFloatPredicate.isEqual("other1", "other2", 1.0f);
        Assertions.assertDoesNotThrow(() -> Assertions.assertFalse(predicate.testThrows("", "", 0.0f)));
    }
}
