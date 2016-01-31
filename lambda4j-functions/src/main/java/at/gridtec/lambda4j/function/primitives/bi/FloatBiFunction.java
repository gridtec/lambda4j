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
package at.gridtec.lambda4j.function.primitives.bi;

import at.gridtec.lambda4j.consumer.primitives.bi.FloatBiConsumer;
import at.gridtec.lambda4j.function.primitives.FloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiFloatToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;
import at.gridtec.lambda4j.function.primitives.to.ToCharFunction;
import at.gridtec.lambda4j.function.primitives.to.ToFloatFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.binary.FloatBinaryOperator;
import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;
import at.gridtec.lambda4j.predicates.primitives.bi.FloatBiPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts two float-valued arguments and produces a result. This is the {@code
 * float}-consuming primitive specialization for {@link BiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(float, float)}.
 *
 * @param <R> The type of return value from the function
 * @see BiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatBiFunction<R> {

    /**
     * Calls the given {@link FloatBiFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The result from the given {@code FloatBiFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static <R> R call(@Nonnull final FloatBiFunction<? extends R> function, float value1, float value2) {
        Objects.requireNonNull(function);
        return function.apply(value1, value2);
    }

    /**
     * Creates a {@link FloatBiFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link FloatFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code FloatBiFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code FloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <R> FloatBiFunction<R> onlyFirst(@Nonnull final FloatFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value1);
    }

    /**
     * Creates a {@link FloatBiFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link FloatFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code FloatBiFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@code FloatFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <R> FloatBiFunction<R> onlySecond(@Nonnull final FloatFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2) -> function.apply(value2);
    }

    /**
     * Creates a {@link FloatBiFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code FloatBiFunction} which always returns a given value.
     */
    @Nonnull
    static <R> FloatBiFunction<R> constant(R r) {
        return (value1, value2) -> r;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(float value1, float value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link FloatBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @return A composed {@code FloatBiFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code float}.
     * @see #andThen(Function)
     */
    @Nonnull
    default FloatBiFunction<R> compose(@Nonnull final FloatUnaryOperator before1,
            @Nonnull final FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(Function)
     */
    @Nonnull
    default <T, U> BiFunction<T, U, R> compose(@Nonnull final ToFloatFunction<? super T> before1,
            @Nonnull final ToFloatFunction<? super U> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u) -> apply(before1.applyAsFloat(t), before2.applyAsFloat(u));
    }

    /**
     * Returns a composed {@link FloatBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatBiFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(FloatUnaryOperator, FloatUnaryOperator)
     * @see #compose(ToFloatFunction, ToFloatFunction)
     */
    @Nonnull
    default <S> FloatBiFunction<S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(apply(value1, value2));
    }

    /**
     * Returns a composed {@link FloatBiPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatBiPredicate} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default FloatBiPredicate andThenToBoolean(@Nonnull final Predicate<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.test(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default BiFloatToByteFunction andThenToByte(@Nonnull final ToByteFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default BiFloatToCharFunction andThenToChar(@Nonnull final ToCharFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default BiFloatToDoubleFunction andThenToDouble(@Nonnull final ToDoubleFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(apply(value1, value2));
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatBinaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default FloatBinaryOperator andThenToFloat(@Nonnull final ToFloatFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default BiFloatToIntFunction andThenToInt(@Nonnull final ToIntFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default BiFloatToLongFunction andThenToLong(@Nonnull final ToLongFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiFloatToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(Function)
     */
    @Nonnull
    default BiFloatToShortFunction andThenToShort(@Nonnull final ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(apply(value1, value2));
    }

    /**
     * Returns a composed {@link FloatBiConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code FloatBiConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatBiConsumer consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(apply(value1, value2));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 1};
     *
     * @param value1 The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default FloatFunction<R> partial(float value1) {
        return value2 -> apply(value1, value2);
    }

    /**
     * Applies this function partially to two arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default Supplier<R> partial(float value1, float value2) {
        return () -> apply(value1, value2);
    }

    /**
     * Returns a curried version of this function.
     *
     * @return A curried version of this function.
     */
    @Nonnull
    default FloatFunction<FloatFunction<R>> curried() {
        return value1 -> value2 -> apply(value1, value2);
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     */
    @Nonnull
    default FloatBiFunction<Optional<R>> nonNull() {
        return (value1, value2) -> Optional.ofNullable(apply(value1, value2));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link FloatBiFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code FloatBiFunction} with JRE specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code FloatBiFunction}.
     */
    @Nonnull
    default BiFunction<Float, Float, R> boxed() {
        return this::apply;
    }
}