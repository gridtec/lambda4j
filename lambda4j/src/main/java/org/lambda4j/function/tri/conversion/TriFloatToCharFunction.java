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

package org.lambda4j.function.tri.conversion;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Triple;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.CharConsumer;
import org.lambda4j.consumer.tri.TriFloatConsumer;
import org.lambda4j.function.CharFunction;
import org.lambda4j.function.bi.conversion.BiFloatToCharFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.CharToByteFunction;
import org.lambda4j.function.conversion.CharToDoubleFunction;
import org.lambda4j.function.conversion.CharToFloatFunction;
import org.lambda4j.function.conversion.CharToIntFunction;
import org.lambda4j.function.conversion.CharToLongFunction;
import org.lambda4j.function.conversion.CharToShortFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.FloatToCharFunction;
import org.lambda4j.function.conversion.IntToFloatFunction;
import org.lambda4j.function.conversion.LongToFloatFunction;
import org.lambda4j.function.conversion.ShortToFloatFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.function.tri.TriFloatFunction;
import org.lambda4j.function.tri.TriFunction;
import org.lambda4j.function.tri.to.ToCharTriFunction;
import org.lambda4j.operator.ternary.CharTernaryOperator;
import org.lambda4j.operator.ternary.FloatTernaryOperator;
import org.lambda4j.operator.unary.CharUnaryOperator;
import org.lambda4j.operator.unary.FloatUnaryOperator;
import org.lambda4j.predicate.CharPredicate;
import org.lambda4j.predicate.tri.TriFloatPredicate;

/**
 * Represents an operation that accepts three {@code float}-valued input arguments and produces a {@code char}-valued
 * result. This is a primitive specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(float, float, float)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriFloatToCharFunction extends Lambda {

    /**
     * Constructs a {@link TriFloatToCharFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriFloatToCharFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static TriFloatToCharFunction of(@Nullable TriFloatToCharFunction expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriFloatToCharFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriFloatToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static char call(@Nonnull TriFloatToCharFunction function, float value1, float value2, float value3) {
        Objects.requireNonNull(function);
        return function.applyAsChar(value1, value2, value3);
    }

    /**
     * Creates a {@link TriFloatToCharFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link FloatToCharFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriFloatToCharFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code FloatToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriFloatToCharFunction onlyFirst(@Nonnull FloatToCharFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsChar(value1);
    }

    /**
     * Creates a {@link TriFloatToCharFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link FloatToCharFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriFloatToCharFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code FloatToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriFloatToCharFunction onlySecond(@Nonnull FloatToCharFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsChar(value2);
    }

    /**
     * Creates a {@link TriFloatToCharFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link FloatToCharFunction}.
     *
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriFloatToCharFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code FloatToCharFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static TriFloatToCharFunction onlyThird(@Nonnull FloatToCharFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsChar(value3);
    }

    /**
     * Creates a {@link TriFloatToCharFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriFloatToCharFunction} which always returns a given value.
     */
    @Nonnull
    static TriFloatToCharFunction constant(char ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    char applyAsChar(float value1, float value2, float value3);

    /**
     * Applies this function partially to some arguments of this one, producing a {@link BiFloatToCharFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @return A {@code BiFloatToCharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default BiFloatToCharFunction applyAsCharPartially(float value1) {
        return (value2, value3) -> applyAsChar(value1, value2, value3);
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link FloatToCharFunction} as
     * result.
     *
     * @param value1 The first argument to this function used to partially apply this function
     * @param value2 The second argument to this function used to partially apply this function
     * @return A {@code FloatToCharFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default FloatToCharFunction applyAsCharPartially(float value1, float value2) {
        return value3 -> applyAsChar(value1, value2, value3);
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
     * Returns a composed {@link ToCharTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToCharTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToCharTriFunction<A, B, C> compose(@Nonnull ToFloatFunction<? super A> before1,
            @Nonnull ToFloatFunction<? super B> before2, @Nonnull ToFloatFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsChar(before1.applyAsFloat(a), before2.applyAsFloat(b), before3.applyAsFloat(c));
    }

    /**
     * Returns a composed {@link TriBooleanToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToCharFunction composeFromBoolean(@Nonnull BooleanToFloatFunction before1,
            @Nonnull BooleanToFloatFunction before2, @Nonnull BooleanToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriByteToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToCharFunction composeFromByte(@Nonnull ByteToFloatFunction before1,
            @Nonnull ByteToFloatFunction before2, @Nonnull ByteToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link CharTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code CharTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharTernaryOperator composeFromChar(@Nonnull CharToFloatFunction before1,
            @Nonnull CharToFloatFunction before2, @Nonnull CharToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToCharFunction composeFromDouble(@Nonnull DoubleToFloatFunction before1,
            @Nonnull DoubleToFloatFunction before2, @Nonnull DoubleToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriFloatToCharFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriFloatToCharFunction} that first applies the {@code before} operators to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToCharFunction composeFromFloat(@Nonnull FloatUnaryOperator before1,
            @Nonnull FloatUnaryOperator before2, @Nonnull FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriIntToCharFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToCharFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToCharFunction composeFromInt(@Nonnull IntToFloatFunction before1,
            @Nonnull IntToFloatFunction before2, @Nonnull IntToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriLongToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToCharFunction composeFromLong(@Nonnull LongToFloatFunction before1,
            @Nonnull LongToFloatFunction before2, @Nonnull LongToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriShortToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortToCharFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToCharFunction composeFromShort(@Nonnull ShortToFloatFunction before1,
            @Nonnull ShortToFloatFunction before2, @Nonnull ShortToFloatFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsChar(before1.applyAsFloat(value1), before2.applyAsFloat(value2),
                before3.applyAsFloat(value3));
    }

    /**
     * Returns a composed {@link TriFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriFloatFunction<S> andThen(@Nonnull CharFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code TriFloatPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriFloatPredicate andThenToBoolean(@Nonnull CharPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.test(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriFloatToByteFunction andThenToByte(@Nonnull CharToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code char}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code TriFloatToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriFloatToCharFunction andThenToChar(@Nonnull CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriFloatToDoubleFunction andThenToDouble(@Nonnull CharToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code FloatTernaryOperator} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatTernaryOperator andThenToFloat(@Nonnull CharToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriFloatToIntFunction andThenToInt(@Nonnull CharToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriFloatToLongFunction andThenToLong(@Nonnull CharToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriFloatToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriFloatToShortFunction andThenToShort(@Nonnull CharToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriFloatConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link CharConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriFloatConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriFloatConsumer consume(@Nonnull CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsChar(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link TriFloatToCharFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriFloatToCharFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriFloatToCharFunction memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Triple<Float, Float, Float>, Character> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (TriFloatToCharFunction & Memoized) (value1, value2, value3) -> {
                char returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3),
                            key -> applyAsChar(key.getLeft(), key.getMiddle(),
                                    key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriFloatToCharFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method provides the possibility to use this {@code
     * TriFloatToCharFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriFloatToCharFunction}.
     */
    @Nonnull
    default TriFunction<Float, Float, Float, Character> boxed() {
        return this::applyAsChar;
    }

}
