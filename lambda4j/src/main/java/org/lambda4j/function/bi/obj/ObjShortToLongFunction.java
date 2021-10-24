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

package org.lambda4j.function.bi.obj;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToLongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.bi.obj.ObjShortConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.BiFunction2;
import org.lambda4j.function.bi.conversion.BiBooleanToLongFunction;
import org.lambda4j.function.bi.conversion.BiByteToLongFunction;
import org.lambda4j.function.bi.conversion.BiCharToLongFunction;
import org.lambda4j.function.bi.conversion.BiDoubleToLongFunction;
import org.lambda4j.function.bi.conversion.BiFloatToLongFunction;
import org.lambda4j.function.bi.conversion.BiIntToLongFunction;
import org.lambda4j.function.bi.conversion.BiShortToLongFunction;
import org.lambda4j.function.bi.to.ToLongBiFunction2;
import org.lambda4j.function.conversion.BooleanToShortFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToShortFunction;
import org.lambda4j.function.conversion.DoubleToShortFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.IntToShortFunction;
import org.lambda4j.function.conversion.LongToByteFunction;
import org.lambda4j.function.conversion.LongToCharFunction;
import org.lambda4j.function.conversion.LongToFloatFunction;
import org.lambda4j.function.conversion.LongToShortFunction;
import org.lambda4j.function.conversion.ShortToLongFunction;
import org.lambda4j.function.to.ToLongFunction2;
import org.lambda4j.function.to.ToShortFunction;
import org.lambda4j.operator.binary.LongBinaryOperator2;
import org.lambda4j.operator.unary.ShortUnaryOperator;
import org.lambda4j.predicate.bi.obj.ObjShortPredicate;

/**
 * Represents an operation that accepts one object-valued and one {@code short}-valued input argument and produces a
 * {@code long}-valued result. This is a (reference, short) specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(Object, short)}.
 *
 * @param <T> The type of the first argument to the function
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjShortToLongFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjShortToLongFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjShortToLongFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T> ObjShortToLongFunction<T> of(@Nullable ObjShortToLongFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjShortToLongFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjShortToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> long call(@Nonnull ObjShortToLongFunction<? super T> function, T t, short value) {
        Objects.requireNonNull(function);
        return function.applyAsLong(t, value);
    }

    /**
     * Creates a {@link ObjShortToLongFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjShortToLongFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortToLongFunction<T> onlyFirst(@Nonnull ToLongFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsLong(t);
    }

    /**
     * Creates a {@link ObjShortToLongFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link ShortToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjShortToLongFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code ShortToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjShortToLongFunction<T> onlySecond(@Nonnull ShortToLongFunction function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.applyAsLong(value);
    }

    /**
     * Creates a {@link ObjShortToLongFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjShortToLongFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjShortToLongFunction<T> constant(long ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    long applyAsLong(T t, short value);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ShortToLongFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ShortToLongFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ShortToLongFunction applyAsLongPartially(T t) {
        return value -> applyAsLong(t, value);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToLongFunction2} as result.
     *
     * @param value The second argument to this function used to partially apply this function
     * @return A {@code ToLongFunction2} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToLongFunction2<T> applyAsLongPartially(short value) {
        return t -> applyAsLong(t, value);
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToLongBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ToLongBiFunction2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToLongBiFunction2<A, B> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToShortFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsLong(before1.apply(a), before2.applyAsShort(b));
    }

    /**
     * Returns a composed {@link BiBooleanToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiBooleanToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToLongFunction composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiByteToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiByteToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteToLongFunction composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiCharToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiCharToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToLongFunction composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiDoubleToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiDoubleToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleToLongFunction composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiFloatToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiFloatToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatToLongFunction composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiIntToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiIntToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntToLongFunction composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link LongBinaryOperator2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code LongBinaryOperator2} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongBinaryOperator2 composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongToShortFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link BiShortToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiShortToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToLongFunction composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsLong(before1.apply(value1), before2.applyAsShort(value2));
    }

    /**
     * Returns a composed {@link ObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjShortFunction<T, S> andThen(@Nonnull LongFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjShortPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjShortPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjShortPredicate<T> andThenToBoolean(@Nonnull LongPredicate after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.test(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjShortToByteFunction<T> andThenToByte(@Nonnull LongToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsByte(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjShortToCharFunction<T> andThenToChar(@Nonnull LongToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsChar(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjShortToDoubleFunction<T> andThenToDouble(@Nonnull LongToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsDouble(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjShortToFloatFunction<T> andThenToFloat(@Nonnull LongToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsFloat(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjShortToIntFunction<T> andThenToInt(@Nonnull LongToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsInt(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjShortToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjShortToLongFunction<T> andThenToLong(@Nonnull LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsLong(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjShortToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjShortToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjShortToShortFunction<T> andThenToShort(@Nonnull LongToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.applyAsShort(applyAsLong(t, value));
    }

    /**
     * Returns a composed {@link ObjShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link LongConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjShortConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjShortConsumer<T> consume(@Nonnull LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(applyAsLong(t, value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjShortToLongFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjShortToLongFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjShortToLongFunction<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<T, Short>, Long> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ObjShortToLongFunction<T> & Memoized) (t, value) -> {
                long returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(t, value),
                            key -> applyAsLong(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BiFunction2} which represents this {@link ObjShortToLongFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ObjShortToLongFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BiFunction2} which represents this {@code ObjShortToLongFunction}.
     */
    @Nonnull
    default BiFunction2<T, Short, Long> boxed() {
        return this::applyAsLong;
    }

}
