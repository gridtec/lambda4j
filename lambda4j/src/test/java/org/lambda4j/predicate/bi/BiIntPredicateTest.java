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

package org.lambda4j.predicate.bi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BiIntPredicateTest {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        BiIntPredicate predicate = BiIntPredicate.of((value1, value2) -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        BiIntPredicate predicate = BiIntPredicate.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(BiIntPredicate.call((value1, value2) -> false, 0, 0));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> BiIntPredicate.call(null, 0, 0));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        BiIntPredicate predicate = BiIntPredicate.constant(ret);
        Assertions.assertEquals(ret, predicate.test(0, 0));
        Assertions.assertFalse(predicate.test(0, 0));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        BiIntPredicate predicate = BiIntPredicate.alwaysTrue();
        Assertions.assertTrue(predicate.test(0, 0));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        BiIntPredicate predicate = BiIntPredicate.alwaysFalse();
        Assertions.assertFalse(predicate.test(0, 0));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        BiIntPredicate predicate = BiIntPredicate.isEqual(0, 0);
        Assertions.assertTrue(predicate.test(0, 0));
    }

    @Test
    void isEqual_givenDifferentFirstValue_returnsFalse() {
        BiIntPredicate predicate = BiIntPredicate.isEqual(1, 0);
        Assertions.assertFalse(predicate.test(0, 0));
    }

    @Test
    void isEqual_givenDifferentSecondValue_returnsFalse() {
        BiIntPredicate predicate = BiIntPredicate.isEqual(0, 1);
        Assertions.assertFalse(predicate.test(0, 0));
    }

    @Test
    void isEqual_givenDifferentAll_returnsFalse() {
        BiIntPredicate predicate = BiIntPredicate.isEqual(1, 1);
        Assertions.assertFalse(predicate.test(0, 0));
    }
}
