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

package org.lambda4j.function.tri.obj;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ByteConsumer;
import org.lambda4j.consumer.tri.obj.ObjBiCharConsumer;
import org.lambda4j.function.BooleanFunction;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.ShortFunction;
import org.lambda4j.function.bi.conversion.BiCharToByteFunction;
import org.lambda4j.function.bi.obj.ObjCharToByteFunction;
import org.lambda4j.function.conversion.BooleanToCharFunction;
import org.lambda4j.function.conversion.ByteToCharFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.ByteToIntFunction;
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToByteFunction;
import org.lambda4j.function.conversion.DoubleToCharFunction;
import org.lambda4j.function.conversion.FloatToCharFunction;
import org.lambda4j.function.conversion.IntToCharFunction;
import org.lambda4j.function.conversion.LongToCharFunction;
import org.lambda4j.function.conversion.ShortToCharFunction;
import org.lambda4j.function.to.ToByteFunction;
import org.lambda4j.function.to.ToCharFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.conversion.TriBooleanToByteFunction;
import org.lambda4j.function.tri.conversion.TriCharToByteFunction;
import org.lambda4j.function.tri.conversion.TriDoubleToByteFunction;
import org.lambda4j.function.tri.conversion.TriFloatToByteFunction;
import org.lambda4j.function.tri.conversion.TriIntToByteFunction;
import org.lambda4j.function.tri.conversion.TriLongToByteFunction;
import org.lambda4j.function.tri.conversion.TriShortToByteFunction;
import org.lambda4j.function.tri.to.ToByteTriFunction;
import org.lambda4j.operator.ternary.ByteTernaryOperator;
import org.lambda4j.operator.unary.ByteUnaryOperator;
import org.lambda4j.operator.unary.CharUnaryOperator;
import org.lambda4j.predicate.BytePredicate;
import org.lambda4j.predicate.tri.obj.ObjBiCharPredicate;

/**
 * Represents an operation that accepts one object-valued and two {@code char}-valued input arguments and produces a
 * {@code byte}-valued result. This is a (reference, char, char) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(Object, char, char)}.
 *
 * @param <T> The type of the first argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBiCharToByteFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjBiCharToByteFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjBiCharToByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T> ObjBiCharToByteFunction<T> of(@Nullable ObjBiCharToByteFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjBiCharToByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The result from the given {@code ObjBiCharToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> byte call(@Nonnull ObjBiCharToByteFunction<? super T> function, T t, char value1, char value2) {
        Objects.requireNonNull(function);
        return function.applyAsByte(t, value1, value2);
    }

    /**
     * Creates a {@link ObjBiCharToByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjBiCharToByteFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiCharToByteFunction<T> onlyFirst(@Nonnull ToByteFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsByte(t);
    }

    /**
     * Creates a {@link ObjBiCharToByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link CharToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjBiCharToByteFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code CharToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiCharToByteFunction<T> onlySecond(@Nonnull CharToByteFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsByte(value1);
    }

    /**
     * Creates a {@link ObjBiCharToByteFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link CharToByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ObjBiCharToByteFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code CharToByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiCharToByteFunction<T> onlyThird(@Nonnull CharToByteFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsByte(value2);
    }

    /**
     * Creates a {@link ObjBiCharToByteFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjBiCharToByteFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjBiCharToByteFunction<T> constant(byte ret) {
        return (t, value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    byte applyAsByte(T t, char value1, char value2);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BiCharToByteFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code BiCharToByteFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BiCharToByteFunction applyAsBytePartially(T t) {
        return (value1, value2) -> applyAsByte(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link CharToByteFunction} as result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code CharToByteFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default CharToByteFunction applyAsBytePartially(T t, char value1) {
        return value2 -> applyAsByte(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ObjCharToByteFunction} as
     * result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @return A {@code ObjCharToByteFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ObjCharToByteFunction<T> applyAsBytePartially(char value1) {
        return (t, value2) -> applyAsByte(t, value1, value2);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ToByteFunction} as result.
     *
     * @param value1 The second argument to this function used to partially apply this function
     * @param value2 The third argument to this function used to partially apply this function
     * @return A {@code ToByteFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ToByteFunction<T> applyAsBytePartially(char value1, char value2) {
        return t -> applyAsByte(t, value1, value2);
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToByteTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToByteTriFunction<A, B, C> compose(@Nonnull Function<? super A, ? extends T> before1,
            @Nonnull ToCharFunction<? super B> before2, @Nonnull ToCharFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsByte(before1.apply(a), before2.applyAsChar(b), before3.applyAsChar(c));
    }

    /**
     * Returns a composed {@link TriBooleanToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToByteFunction composeFromBoolean(@Nonnull BooleanFunction<? extends T> before1,
            @Nonnull BooleanToCharFunction before2, @Nonnull BooleanToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link ByteTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ByteTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteTernaryOperator composeFromByte(@Nonnull ByteFunction<? extends T> before1,
            @Nonnull ByteToCharFunction before2, @Nonnull ByteToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriCharToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriCharToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToByteFunction composeFromChar(@Nonnull CharFunction<? extends T> before1,
            @Nonnull CharUnaryOperator before2, @Nonnull CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToByteFunction composeFromDouble(@Nonnull DoubleFunction<? extends T> before1,
            @Nonnull DoubleToCharFunction before2, @Nonnull DoubleToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriFloatToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToByteFunction composeFromFloat(@Nonnull FloatFunction<? extends T> before1,
            @Nonnull FloatToCharFunction before2, @Nonnull FloatToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriIntToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToByteFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToByteFunction composeFromInt(@Nonnull IntFunction<? extends T> before1,
            @Nonnull IntToCharFunction before2, @Nonnull IntToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriLongToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToByteFunction composeFromLong(@Nonnull LongFunction<? extends T> before1,
            @Nonnull LongToCharFunction before2, @Nonnull LongToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriShortToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortToByteFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToByteFunction composeFromShort(@Nonnull ShortFunction<? extends T> before1,
            @Nonnull ShortToCharFunction before2, @Nonnull ShortToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsByte(before1.apply(value1), before2.applyAsChar(value2),
                before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link ObjBiCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjBiCharFunction<T, S> andThen(@Nonnull ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.apply(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjBiCharPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjBiCharPredicate<T> andThenToBoolean(@Nonnull BytePredicate after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.test(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjBiCharToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjBiCharToByteFunction<T> andThenToByte(@Nonnull ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByte(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjBiCharToCharFunction<T> andThenToChar(@Nonnull ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsChar(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjBiCharToDoubleFunction<T> andThenToDouble(@Nonnull ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDouble(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjBiCharToFloatFunction<T> andThenToFloat(@Nonnull ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloat(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjBiCharToIntFunction<T> andThenToInt(@Nonnull ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsInt(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjBiCharToLongFunction<T> andThenToLong(@Nonnull ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLong(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjBiCharToShortFunction<T> andThenToShort(@Nonnull ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShort(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjBiCharConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjBiCharConsumer<T> consume(@Nonnull ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.accept(applyAsByte(t, value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjBiCharToByteFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjBiCharToByteFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjBiCharToByteFunction<T> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<T, Character, Character>, Byte> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ObjBiCharToByteFunction<T> & Memoized) (t, value1, value2) -> {
                byte returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, value1, value2),
                            key -> applyAsByte(key.getLeft(), key.getMiddle(),
                                    key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjBiCharToByteFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * ObjBiCharToByteFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjBiCharToByteFunction}.
     */
    @Nonnull
    default TriFunction<T, Character, Character, Byte> boxed() {
        return this::applyAsByte;
    }

}
