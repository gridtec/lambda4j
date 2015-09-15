/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.consumer.primitives.bi;

import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Represents an operation that accepts two {@code short}-valued arguments and returns no result. This is the primitive
 * type specialization of {@link BiConsumer} for {@code short}. Unlike most other functional interfaces, {@code
 * ShortBiConsumer} is expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(short, short)}.
 *
 * @see java.util.function.BiConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ShortBiConsumer {

    /**
     * Performs this operation on the given arguments.
     *
     * @param value1 The first argument for the operation to be consumed
     * @param value2 The second argument for the operation to be consumed
     */
    void accept(short value1, short value2);

    /**
     * Returns a composed {@link ShortBiConsumer} that applies the given {@code before} {@link ShortUnaryOperator}s to
     * its input, and then applies this operation to the result. If evaluation of either of the given operations throws
     * an exception, it is relayed to the caller of the composed function.
     *
     * @param before1 The first before {@code ShortUnaryOperator} to apply before this operation is applied
     * @param before2 The second before {@code ShortUnaryOperator} to apply before this operation is applied
     * @return A composed {@code ShortBiConsumer} that applies the given {@code before} {@code ShortUnaryOperator}s to
     * its input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ShortBiConsumer)
     */
    default ShortBiConsumer compose(final ShortUnaryOperator before1, final ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiConsumer} that applies the given {@code before} {@link ToShortFunction}s to its
     * input, and then applies this operation to the result. If evaluation of either of the given operations throws an
     * exception, it is relayed to the caller of the composed function.
     *
     * @param <T> The type of the argument to the first before operation
     * @param <U> The type of the argument to the second before operation
     * @param before1 The first before {@code ToShortFunction} to apply before this operation is applied
     * @param before2 The second before {@code ToShortFunction} to apply before this operation is applied
     * @return A composed {@code TriConsumer} that applies the given {@code before} {@code ToShortFunction}s to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ShortBiConsumer)
     */
    default <T, U> BiConsumer<T, U> compose(final ToShortFunction<? super T> before1,
            final ToShortFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsShort(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link ShortBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation. If evaluation of either operation throws an exception, it is relayed to the caller of the
     * composed function. If performing this operation throws an exception, the {@code after} operation will not be
     * performed.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@link ShortBiConsumer} that performs, in sequence, this operation followed by the {@code
     * after} operation.
     * @throws NullPointerException If given after operation is {@code null}
     * @see #compose(ShortUnaryOperator, ShortUnaryOperator)
     * @see #compose(ToShortFunction, ToShortFunction)
     */
    default ShortBiConsumer andThen(final ShortBiConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link ShortBiConsumer}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortBiConsumer} with JRE specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code ShortBiConsumer}.
     */
    default BiConsumer<Short, Short> boxed() {
        return this::accept;
    }
}
