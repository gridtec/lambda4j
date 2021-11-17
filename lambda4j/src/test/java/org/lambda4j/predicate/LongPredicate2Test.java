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

class LongPredicate2Test {

    @Test
    void of_givenExpression_returnsFunctionalInterface() {
        LongPredicate2 predicate = LongPredicate2.of(value -> false);
        Assertions.assertNotNull(predicate);
    }

    @Test
    void of_givenNull_returnsNull() {
        LongPredicate2 predicate = LongPredicate2.of(null);
        Assertions.assertNull(predicate);
    }

    @Test
    void call_givenExpression_executesFunctionalInterface() {
        Assertions.assertFalse(LongPredicate2.call(value -> false, 0L));
    }

    @Test
    void call_givenNullExpression_throwsException() {
        Assertions.assertThrows(NullPointerException.class, () -> LongPredicate2.call(null, 0L));
    }

    @Test
    void constant_givenValue_returnsAlwaysValue() {
        boolean ret = false;
        LongPredicate2 predicate = LongPredicate2.constant(ret);
        Assertions.assertEquals(ret, predicate.test(0L));
        Assertions.assertFalse(predicate.test(0L));
    }

    @Test
    void alwaysTrue_givenNothing_returnsAlwaysValue() {
        LongPredicate2 predicate = LongPredicate2.alwaysTrue();
        Assertions.assertTrue(predicate.test(0L));
    }

    @Test
    void alwaysFalse_givenNothing_returnsAlwaysValue() {
        LongPredicate2 predicate = LongPredicate2.alwaysFalse();
        Assertions.assertFalse(predicate.test(0L));
    }

    @Test
    void isEqual_givenSame_returnsTrue() {
        LongPredicate2 predicate = LongPredicate2.isEqual(0L);
        Assertions.assertTrue(predicate.test(0L));
    }

    @Test
    void isEqual_givenDifferent_returnsFalse() {
        LongPredicate2 predicate = LongPredicate2.isEqual(1L);
        Assertions.assertFalse(predicate.test(0L));
    }
}
