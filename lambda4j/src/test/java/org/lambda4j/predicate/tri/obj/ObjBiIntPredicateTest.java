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

class ObjBiIntPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.of((t, value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(ObjBiIntPredicate.call((t, value1, value2) -> false, "", 0, 0));
    }

    @Test
    void call_givenNullValue_executesFunctionalInterface() {
        Assertions.assertFalse(ObjBiIntPredicate.call((t, value1, value2) -> false, null, 0, 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> ObjBiIntPredicate.call(null, "", 0, 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test("", 0, 0));
        Assertions.assertFalse(predicate.test("", 0, 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test("", 0, 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test("", 0, 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.isEqual("", 0, 0);
        Assertions.assertTrue(predicate.test("", 0, 0));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.isEqual("first", 0, 0);
        Assertions.assertFalse(predicate.test("", 0, 0));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.isEqual("", 1, 0);
        Assertions.assertFalse(predicate.test("", 0, 0));
    }

    @Test
    void isEqual_givenDifferentThirdValue_returnsFalse() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.isEqual("", 0, 1);
        Assertions.assertFalse(predicate.test("", 0, 0));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        ObjBiIntPredicate<String> predicate = ObjBiIntPredicate.isEqual("other", 1, 1);
        Assertions.assertFalse(predicate.test("", 0, 0));
    }
}
