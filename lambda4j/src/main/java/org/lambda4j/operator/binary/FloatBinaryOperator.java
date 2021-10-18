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

package org.lambda4j.operator.binary;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BinaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.FloatConsumer;
import org.lambda4j.consumer.bi.BiFloatConsumer;
import org.lambda4j.function.FloatFunction;
import org.lambda4j.function.bi.BiFloatFunction;
import org.lambda4j.function.bi.conversion.BiBooleanToFloatFunction;
import org.lambda4j.function.bi.conversion.BiByteToFloatFunction;
import org.lambda4j.function.bi.conversion.BiCharToFloatFunction;
import org.lambda4j.function.bi.conversion.BiDoubleToFloatFunction;
import org.lambda4j.function.bi.conversion.BiFloatToByteFunction;
import org.lambda4j.function.bi.conversion.BiFloatToCharFunction;
import org.lambda4j.function.bi.conversion.BiFloatToDoubleFunction;
import org.lambda4j.function.bi.conversion.BiFloatToIntFunction;
import org.lambda4j.function.bi.conversion.BiFloatToLongFunction;
import org.lambda4j.function.bi.conversion.BiFloatToShortFunction;
import org.lambda4j.function.bi.conversion.BiIntToFloatFunction;
import org.lambda4j.function.bi.conversion.BiLongToFloatFunction;
import org.lambda4j.function.bi.conversion.BiShortToFloatFunction;
import org.lambda4j.function.bi.to.ToFloatBiFunction;
import org.lambda4j.function.conversion.BooleanToFloatFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.CharToFloatFunction;
import org.lambda4j.function.conversion.DoubleToFloatFunction;
import org.lambda4j.function.conversion.FloatToByteFunction;
import org.lambda4j.function.conversion.FloatToCharFunction;
import org.lambda4j.function.conversion.FloatToDoubleFunction;
import org.lambda4j.function.conversion.FloatToIntFunction;
import org.lambda4j.function.conversion.FloatToLongFunction;
import org.lambda4j.function.conversion.FloatToShortFunction;
import org.lambda4j.function.conversion.IntToFloatFunction;
import org.lambda4j.function.conversion.LongToFloatFunction;
import org.lambda4j.function.conversion.ShortToFloatFunction;
import org.lambda4j.function.to.ToFloatFunction;
import org.lambda4j.operator.unary.FloatUnaryOperator;
import org.lambda4j.predicate.FloatPredicate;
import org.lambda4j.predicate.bi.BiFloatPredicate;

/**
 * Represents an operation that accepts two {@code float}-valued input arguments and produces a {@code float}-valued
 * result. This is a primitive specialization of {@link BinaryOperator2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(float, float)}.
 *
 * @see BinaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatBinaryOperator extends Lambda {

    /**
     * Constructs a {@link FloatBinaryOperator} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code FloatBinaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static FloatBinaryOperator of(@Nullable FloatBinaryOperator expression) {
        return expression;
    }

    /**
     * Calls the given {@link FloatBinaryOperator} with the given arguments and returns its result.
     *
     * @param operator The operator to be called
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The result from the given {@code FloatBinaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static float call(@Nonnull FloatBinaryOperator operator, float value1, float value2) {
        Objects.requireNonNull(operator);
        return operator.applyAsFloat(value1, value2);
    }

    /**
     * Creates a {@link FloatBinaryOperator} which uses the {@code first} parameter of this one as argument for the
     * given {@link FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code first} parameter of this one
     * @return Creates a {@code FloatBinaryOperator} which uses the {@code first} parameter of this one as argument for
     * the given {@code FloatUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static FloatBinaryOperator onlyFirst(@Nonnull FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsFloat(value1);
    }

    /**
     * Creates a {@link FloatBinaryOperator} which uses the {@code second} parameter of this one as argument for the
     * given {@link FloatUnaryOperator}.
     *
     * @param operator The operator which accepts the {@code second} parameter of this one
     * @return Creates a {@code FloatBinaryOperator} which uses the {@code second} parameter of this one as argument for
     * the given {@code FloatUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static FloatBinaryOperator onlySecond(@Nonnull FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (value1, value2) -> operator.applyAsFloat(value2);
    }

    /**
     * Creates a {@link FloatBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code FloatBinaryOperator} which always returns a given value.
     */
    @Nonnull
    static FloatBinaryOperator constant(float ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link FloatBinaryOperator} which returns the lesser of two elements according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code FloatBinaryOperator} which returns the lesser of its operands, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#minBy(Comparator)
     */
    @Nonnull
    static FloatBinaryOperator minBy(@Nonnull Comparator<Float> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) <= 0 ? value1 : value2;
    }

    /**
     * Returns a {@link FloatBinaryOperator} which returns the greater of two elements according to the specified {@code
     * Comparator}.
     *
     * @param comparator A {@code Comparator} for comparing the two values
     * @return A {@code FloatBinaryOperator} which returns the greater of its operands, according to the supplied {@code
     * Comparator}.
     * @throws NullPointerException If given argument is {@code null}
     * @see BinaryOperator#maxBy(Comparator)
     */
    @Nonnull
    static FloatBinaryOperator maxBy(@Nonnull Comparator<Float> comparator) {
        Objects.requireNonNull(comparator);
        return (value1, value2) -> comparator.compare(value1, value2) >= 0 ? value1 : value2;
    }

    /**
     * Applies this operator to the given arguments.
     *
     * @param value1 The first argument to the operator
     * @param value2 The second argument to the operator
     * @return The return value from the operator, which is its result.
     */
    float applyAsFloat(float value1, float value2);

    /**
     * Applies this operator partially to some arguments of this one, producing a {@link FloatUnaryOperator} as result.
     *
     * @param value1 The first argument to this operator used to partially apply this function
     * @return A {@code FloatUnaryOperator} that represents this operator partially applied the some arguments.
     */
    @Nonnull
    default FloatUnaryOperator applyAsFloatPartially(float value1) {
        return value2 -> applyAsFloat(value1, value2);
    }

    /**
     * Returns the number of arguments for this operator.
     *
     * @return The number of arguments for this operator.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link ToFloatBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code ToFloatBiFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ToFloatBiFunction<A, B> compose(@Nonnull ToFloatFunction<? super A> before1,
            @Nonnull ToFloatFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsFloat(before1.applyAsFloat(a), before2.applyAsFloat(b));
    }

    /**
     * Returns a composed {@link BiBooleanToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiBooleanToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanToFloatFunction composeFromBoolean(@Nonnull BooleanToFloatFunction before1,
            @Nonnull BooleanToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiByteToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiByteToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteToFloatFunction composeFromByte(@Nonnull ByteToFloatFunction before1,
            @Nonnull ByteToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiCharToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiCharToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharToFloatFunction composeFromChar(@Nonnull CharToFloatFunction before1,
            @Nonnull CharToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiDoubleToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiDoubleToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleToFloatFunction composeFromDouble(@Nonnull DoubleToFloatFunction before1,
            @Nonnull DoubleToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before1 The first operator to apply before this operator is applied
     * @param before2 The second operator to apply before this operator is applied
     * @return A composed {@code FloatBinaryOperator} that first applies the {@code before} operators to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatBinaryOperator composeFromFloat(@Nonnull FloatUnaryOperator before1,
            @Nonnull FloatUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiIntToFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiIntToFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntToFloatFunction composeFromInt(@Nonnull IntToFloatFunction before1,
            @Nonnull IntToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiLongToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiLongToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongToFloatFunction composeFromLong(@Nonnull LongToFloatFunction before1,
            @Nonnull LongToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiShortToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before1 The first function to apply before this operator is applied
     * @param before2 The second function to apply before this operator is applied
     * @return A composed {@code BiShortToFloatFunction} that first applies the {@code before} functions to its input,
     * and then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortToFloatFunction composeFromShort(@Nonnull ShortToFloatFunction before1,
            @Nonnull ShortToFloatFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> applyAsFloat(before1.applyAsFloat(value1), before2.applyAsFloat(value2));
    }

    /**
     * Returns a composed {@link BiFloatFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiFloatFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiFloatFunction<S> andThen(@Nonnull FloatFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.apply(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code BiFloatPredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiFloatPredicate andThenToBoolean(@Nonnull FloatPredicate after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.test(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code byte}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiFloatToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiFloatToByteFunction andThenToByte(@Nonnull FloatToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsByte(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiFloatToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiFloatToCharFunction andThenToChar(@Nonnull FloatToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsChar(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiFloatToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiFloatToDoubleFunction andThenToDouble(@Nonnull FloatToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsDouble(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link FloatBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code float}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code FloatBinaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatBinaryOperator andThenToFloat(@Nonnull FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsFloat(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiFloatToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiFloatToIntFunction andThenToInt(@Nonnull FloatToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsInt(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiFloatToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiFloatToLongFunction andThenToLong(@Nonnull FloatToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsLong(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code BiFloatToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiFloatToShortFunction andThenToShort(@Nonnull FloatToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> after.applyAsShort(applyAsFloat(value1, value2));
    }

    /**
     * Returns a composed {@link BiFloatConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link FloatConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiFloatConsumer} that first applies this operator to its input, and then consumes the
     * result using the given {@code FloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiFloatConsumer consume(@Nonnull FloatConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(applyAsFloat(value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link FloatBinaryOperator}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code FloatBinaryOperator}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default FloatBinaryOperator memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<Float, Float>, Float> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (FloatBinaryOperator & Memoized) (value1, value2) -> {
                float returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(value1, value2),
                            key -> applyAsFloat(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link BinaryOperator2} which represents this {@link FloatBinaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method provides the possibility to use this {@code
     * FloatBinaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code BinaryOperator2} which represents this {@code FloatBinaryOperator}.
     */
    @Nonnull
    default BinaryOperator2<Float> boxed() {
        return this::applyAsFloat;
    }

}
