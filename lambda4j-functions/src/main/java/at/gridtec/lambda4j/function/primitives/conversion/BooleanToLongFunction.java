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
package at.gridtec.lambda4j.function.primitives.conversion;

import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts a boolean-valued argument and produces a long-valued result. This is the {@code
 * boolean}-to-{@code long} primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(boolean)}.
 *
 * @see Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanToLongFunction {

    /**
     * Calls the given {@link BooleanToLongFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value The argument to the function
     * @return The result from the given {@code BooleanToLongFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static long call(@Nonnull final BooleanToLongFunction function, boolean value) {
        Objects.requireNonNull(function);
        return function.applyAsLong(value);
    }

    /**
     * Creates a {@link BooleanToLongFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanToLongFunction} which always returns a given value.
     */
    @Nonnull
    static BooleanToLongFunction constant(long ret) {
        return value -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value The argument to the function
     * @return The result from this function, which is its result.
     */
    long applyAsLong(boolean value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link BooleanToLongFunction} that first applies the {@code before} operation to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before The operation to apply before this function is applied
     * @return A composed {@code BooleanToLongFunction} that first applies the {@code before} operation to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a primitive specialization of {@link UnaryOperator}. Therefore the
     * given operation handles primitive types. In this case this is {@code byte}.
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BooleanToLongFunction compose(@Nonnull final BooleanUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsLong(before.applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link ToLongFunction} that first applies the {@code before} operation to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the before function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ToLongFunction} that first applies the {@code before} operation to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default <T> ToLongFunction<T> compose(@Nonnull final Predicate<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsLong(before.test(t));
    }

    /**
     * Returns a composed {@link BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(BooleanUnaryOperator)
     * @see #compose(Predicate)
     */
    @Nonnull
    default <R> BooleanFunction<R> andThen(@Nonnull final LongFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsLong(value));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BooleanUnaryOperator andThenToBoolean(@Nonnull final LongPredicate after) {
        Objects.requireNonNull(after);
        return value -> after.test(applyAsLong(value));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BooleanToByteFunction andThenToByte(@Nonnull final LongToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsLong(value));
    }

    /**
     * Returns a composed {@link BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BooleanToCharFunction andThenToChar(@Nonnull final LongToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsLong(value));
    }

    /**
     * Returns a composed {@link BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BooleanToDoubleFunction andThenToDouble(@Nonnull final LongToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsLong(value));
    }

    /**
     * Returns a composed {@link BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BooleanToFloatFunction andThenToFloat(@Nonnull final LongToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsLong(value));
    }

    /**
     * Returns a composed {@link BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BooleanToIntFunction andThenToInt(@Nonnull final LongToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsLong(value));
    }

    /**
     * Returns a composed {@link BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BooleanToLongFunction andThenToLong(@Nonnull final LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsLong(value));
    }

    /**
     * Returns a composed {@link BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BooleanToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(LongFunction)
     */
    @Nonnull
    default BooleanToShortFunction andThenToShort(@Nonnull final LongToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsLong(value));
    }

    /**
     * Returns a composed {@link BooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link LongConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BooleanConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanConsumer consume(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsLong(value));
    }

    /**
     * Applies this function partially to one argument. The result is an operation of arity {@code 0}.
     *
     * @param value The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default LongSupplier partial(boolean value) {
        return () -> applyAsLong(value);
    }

    /**
     * Returns a composed {@link Function} which represents this {@link BooleanToLongFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BooleanToLongFunction} with JRE specific methods, only accepting {@code Function}.
     *
     * @return A composed {@code Function} which represents this {@code BooleanToLongFunction}.
     */
    @Nonnull
    default Function<Boolean, Long> boxed() {
        return this::applyAsLong;
    }
}