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

package org.lambda4j.function.to;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableConsumer;
import org.lambda4j.consumer.ThrowableLongConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableBooleanFunction;
import org.lambda4j.function.ThrowableByteFunction;
import org.lambda4j.function.ThrowableCharFunction;
import org.lambda4j.function.ThrowableDoubleFunction;
import org.lambda4j.function.ThrowableFloatFunction;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.ThrowableIntFunction;
import org.lambda4j.function.ThrowableLongFunction;
import org.lambda4j.function.ThrowableShortFunction;
import org.lambda4j.function.conversion.ThrowableBooleanToLongFunction;
import org.lambda4j.function.conversion.ThrowableByteToLongFunction;
import org.lambda4j.function.conversion.ThrowableCharToLongFunction;
import org.lambda4j.function.conversion.ThrowableDoubleToLongFunction;
import org.lambda4j.function.conversion.ThrowableFloatToLongFunction;
import org.lambda4j.function.conversion.ThrowableIntToLongFunction;
import org.lambda4j.function.conversion.ThrowableLongToByteFunction;
import org.lambda4j.function.conversion.ThrowableLongToCharFunction;
import org.lambda4j.function.conversion.ThrowableLongToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableLongToFloatFunction;
import org.lambda4j.function.conversion.ThrowableLongToIntFunction;
import org.lambda4j.function.conversion.ThrowableLongToShortFunction;
import org.lambda4j.function.conversion.ThrowableShortToLongFunction;
import org.lambda4j.operator.unary.ThrowableLongUnaryOperator;
import org.lambda4j.predicate.ThrowableLongPredicate;
import org.lambda4j.predicate.ThrowablePredicate;

/**
 * Represents an operation that accepts one input argument and produces a {@code long}-valued result which is able to
 * throw any {@link Throwable}. This is a primitive specialization of {@link ThrowableFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLongThrows(Object)}.
 *
 * @param <T> The type of the argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @apiNote This is a throwable JDK lambda.
 * @see ThrowableFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableToLongFunction<T, X extends Throwable> extends Lambda, ToLongFunction<T> {

    /**
     * Constructs a {@link ThrowableToLongFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableToLongFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, X extends Throwable> ThrowableToLongFunction<T, X> of(
            @Nullable ThrowableToLongFunction<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableToLongFunction} with the given argument and returns its result.
     *
     * @param <T> The type of the argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The argument to the function
     * @return The result from the given {@code ThrowableToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, X extends Throwable> long call(@Nonnull ThrowableToLongFunction<? super T, ? extends X> function,
            T t) throws X {
        Objects.requireNonNull(function);
        return function.applyAsLongThrows(t);
    }

    /**
     * Creates a {@link ThrowableToLongFunction} which always returns a given value.
     *
     * @param <T> The type of the argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableToLongFunction} which always returns a given value.
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableToLongFunction<T, X> constant(long ret) {
        return t -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    long applyAsLongThrows(T t) throws X;

    /**
     * Applies this function to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     * @apiNote This method mainly exists to use this {@link ThrowableToLongFunction} in JRE specific methods only
     * accepting {@link ToLongFunction}. If this function should be applied, then the {@link #applyAsLongThrows(Object)}
     * method should be used.
     * @apiNote Overrides the {@link ToLongFunction#applyAsLong(Object)} method by using a redefinition as default
     * method. This implementation calls the {@link #applyAsLongThrows(Object)} method of this function and catches the
     * eventually thrown {@link Throwable} from it. If it is of type {@link RuntimeException} or {@link Error} it is
     * rethrown as is. Other {@code Throwable} types are wrapped in a {@link ThrownByFunctionalInterfaceException}.
     */
    @Override
    default long applyAsLong(T t) {
        return nest().applyAsLong(t);
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ThrowableToLongFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableToLongFunction} that first applies the {@code before} function to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ThrowableToLongFunction<A, X> compose(
            @Nonnull ThrowableFunction<? super A, ? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return a -> applyAsLongThrows(before.applyThrows(a));
    }

    /**
     * Returns a composed {@link ThrowableBooleanToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableBooleanToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanToLongFunction<X> composeFromBoolean(
            @Nonnull ThrowableBooleanFunction<? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsLongThrows(before.applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableByteToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableByteToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableByteToLongFunction<X> composeFromByte(
            @Nonnull ThrowableByteFunction<? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsLongThrows(before.applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableCharToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableCharToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableCharToLongFunction<X> composeFromChar(
            @Nonnull ThrowableCharFunction<? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsLongThrows(before.applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableDoubleToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableDoubleToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableDoubleToLongFunction<X> composeFromDouble(
            @Nonnull ThrowableDoubleFunction<? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsLongThrows(before.applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFloatToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableFloatToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableFloatToLongFunction<X> composeFromFloat(
            @Nonnull ThrowableFloatFunction<? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsLongThrows(before.applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableIntToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code int} input, before this function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableIntToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableIntToLongFunction<X> composeFromInt(
            @Nonnull ThrowableIntFunction<? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsLongThrows(before.applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableLongUnaryOperator} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableLongUnaryOperator} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableLongUnaryOperator<X> composeFromLong(
            @Nonnull ThrowableLongFunction<? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsLongThrows(before.applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableShortToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this function is executed.
     *
     * @param before The function to apply before this function is applied
     * @return A composed {@code ThrowableShortToLongFunction} that first applies the {@code before} function to its
     * input, and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableShortToLongFunction<X> composeFromShort(
            @Nonnull ThrowableShortFunction<? extends T, ? extends X> before) {
        Objects.requireNonNull(before);
        return value -> applyAsLongThrows(before.applyThrows(value));
    }

    /**
     * Returns a composed {@link ThrowableFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableFunction<T, S, X> andThen(
            @Nonnull ThrowableLongFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return t -> after.applyThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a composed {@link ThrowablePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowablePredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowablePredicate<T, X> andThenToBoolean(@Nonnull ThrowableLongPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return t -> after.testThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableToByteFunction<T, X> andThenToByte(@Nonnull ThrowableLongToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsByteThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableToCharFunction<T, X> andThenToChar(@Nonnull ThrowableLongToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsCharThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableToDoubleFunction<T, X> andThenToDouble(
            @Nonnull ThrowableLongToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsDoubleThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableToFloatFunction<T, X> andThenToFloat(
            @Nonnull ThrowableLongToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsFloatThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableToIntFunction<T, X> andThenToInt(@Nonnull ThrowableLongToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsIntThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableToLongFunction<T, X> andThenToLong(@Nonnull ThrowableLongUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsLongThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableToShortFunction<T, X> andThenToShort(
            @Nonnull ThrowableLongToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsShortThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a composed {@link ThrowableConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ThrowableLongConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code ThrowableLongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableConsumer<T, X> consume(@Nonnull ThrowableLongConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.acceptThrows(applyAsLongThrows(t));
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ThrowableToLongFunction<T, X> reversed() {
        return this;
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableToLongFunction}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableToLongFunction}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableToLongFunction<T, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<T, Long> cache = new ConcurrentHashMap<>();
            return (ThrowableToLongFunction<T, X> & Memoized) t -> {
                return cache.computeIfAbsent(t, ThrowableFunction.of(this::applyAsLongThrows));
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableFunction} which represents this {@link ThrowableToLongFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ThrowableToLongFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableFunction} which represents this {@code ThrowableToLongFunction}.
     */
    @Nonnull
    default ThrowableFunction<T, Long, X> boxed() {
        return this::applyAsLongThrows;
    }

    /**
     * Returns a composed {@link ToLongFunction2} that applies this function to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link ToLongFunction2} that applies this function to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ToLongFunction2<T> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ToLongFunction2} that applies this function to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ToLongFunction2} that applies this function to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ToLongFunction2<T> nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ToLongFunction2} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same argument of this
     * function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link ToLongFunction2} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ToLongFunction2<T> recover(
            @Nonnull Function<? super Throwable, ? extends ToLongFunction<? super T>> recover) {
        Objects.requireNonNull(recover);
        return t -> {
            try {
                return applyAsLongThrows(t);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ToLongFunction<? super T> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsLong(t);
            }
        };
    }

    /**
     * Returns a composed {@link ToLongFunction2} that applies this function to its input and sneakily throws the thrown
     * {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that each
     * throwable thrown from the returned composed function behaves exactly the same as an <em>unchecked</em> throwable
     * does. As a result, there is no need to handle the throwable of this function in the returned composed function by
     * either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause, as it would
     * be done in a non sneaky throwing function.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing function variant of this throwable function, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed function. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed function. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed function is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed function, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed function.
     * <p>
     * With all that mentioned, the following example will demonstrate this methods correct use:
     * <pre>{@code
     * // when called with illegal value ClassNotFoundException is thrown
     * public Class<?> sneakyThrowingFunctionalInterface(final String className) throws ClassNotFoundException {
     *     return ThrowableFunction.of(Class::forName) // create the correct throwable functional interface
     *                .sneakyThrow() // create a non-throwable variant which is able to sneaky throw (this method)
     *                .apply(className); // apply non-throwable variant -> may sneaky throw a throwable
     * }
     *
     * // call the the method which surround the sneaky throwing functional interface
     * public void callingMethod() {
     *     try {
     *         final Class<?> clazz = sneakyThrowingFunctionalInterface("some illegal class name");
     *         // ... do something with clazz ...
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link ToLongFunction2} that applies this function to its input and sneakily throws the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ToLongFunction2<T> sneakyThrow() {
        return t -> {
            try {
                return applyAsLongThrows(t);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
