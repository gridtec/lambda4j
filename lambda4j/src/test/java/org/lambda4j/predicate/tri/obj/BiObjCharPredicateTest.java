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

class BiObjCharPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.of((t, u, value) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjCharPredicate.call((t, u, value) -> false, "", "", 'c'));
    }

    @Test
    void call_givenNullFirstValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjCharPredicate.call((t, u, value) -> false, null, "", 'c'));
    }

    @Test
    void call_givenNullSecondValue_executesFunctionalInterface() {
        Assertions.assertFalse(BiObjCharPredicate.call((t, u, value) -> false, "", null, 'c'));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiObjCharPredicate.call(null, "", "", 'c'));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", "", 'c'));
        Assertions.assertFalse(predicate.test("", "", 'c'));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", "", 'c'));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", "", 'c'));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.isEqual("", "", 'c');
        Assertions.assertTrue(predicate.test("", "", 'c'));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.isEqual("first", "", 'c');
        Assertions.assertFalse(predicate.test("", "", 'c'));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.isEqual("", "second", 'c');
        Assertions.assertFalse(predicate.test("", "", 'c'));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.isEqual("", "", 'd');
        Assertions.assertFalse(predicate.test("", "", 'c'));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiObjCharPredicate<String, String> predicate = BiObjCharPredicate.isEqual("other1", "other2", 'd');
        Assertions.assertFalse(predicate.test("", "", 'c'));
    }
}
