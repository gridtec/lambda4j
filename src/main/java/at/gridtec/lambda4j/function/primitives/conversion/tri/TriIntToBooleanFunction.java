/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.lambda4j.function.primitives.conversion.tri;

import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.consumer.primitives.tri.IntTriConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToShortFunction;
import at.gridtec.lambda4j.function.primitives.conversion.IntToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.bi.BiIntToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.tri.IntTriFunction;
import at.gridtec.lambda4j.operators.ternary.IntTernaryOperator;
import at.gridtec.lambda4j.operators.unary.BooleanUnaryOperator;
import at.gridtec.lambda4j.predicates.TriPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

/**
 * Represents a function that accepts three int-valued arguments and produces a boolean-valued result. This is the
 * {@code int}-to-{@code boolean} primitive specialization for {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(int, int, int)}.
 *
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriIntToBooleanFunction {

    /**
     * Calls the given {@link TriIntToBooleanFunction} with the given arguments and returns its result.
     *
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriIntToBooleanFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static boolean call(@Nonnull final TriIntToBooleanFunction function, int value1, int value2, int value3) {
        Objects.requireNonNull(function);
        return function.applyAsBoolean(value1, value2, value3);
    }

    /**
     * Creates a {@link TriIntToBooleanFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link IntToBooleanFunction}.
     *
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriIntToBooleanFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code IntToBooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriIntToBooleanFunction onlyFirst(@Nonnull final IntToBooleanFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsBoolean(value1);
    }

    /**
     * Creates a {@link TriIntToBooleanFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link IntToBooleanFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriIntToBooleanFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code IntToBooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriIntToBooleanFunction onlySecond(@Nonnull final IntToBooleanFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsBoolean(value2);
    }

    /**
     * Creates a {@link TriIntToBooleanFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link IntToBooleanFunction}.
     *
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriIntToBooleanFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code IntToBooleanFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static TriIntToBooleanFunction onlyThird(@Nonnull final IntToBooleanFunction function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.applyAsBoolean(value3);
    }

    /**
     * Creates a {@link TriIntToBooleanFunction} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code TriIntToBooleanFunction} which always returns a given value.
     */
    @Nonnull
    static TriIntToBooleanFunction constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given argument.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    boolean applyAsBoolean(int value1, int value2, int value3);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link TriIntToBooleanFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code TriIntToBooleanFunction} that first applies the {@code before} operations to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are primitive specializations of {@link UnaryOperator}. Therefore
     * the given operations handle primitive types. In this case this is {@code int}.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriIntToBooleanFunction compose(@Nonnull final IntUnaryOperator before1,
            @Nonnull final IntUnaryOperator before2, @Nonnull final IntUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsBoolean(before1.applyAsInt(value1), before2.applyAsInt(value2),
                                                          before3.applyAsInt(value3));
    }

    /**
     * Returns a composed {@link TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <T> The type of the argument to the first before function
     * @param <U> The type of the argument to the second before function
     * @param <V> The type of the argument to the third before function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriPredicate} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If one of the given arguments is {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default <T, U, V> TriPredicate<T, U, V> compose(@Nonnull final ToIntFunction<? super T> before1,
            @Nonnull final ToIntFunction<? super U> before2, @Nonnull final ToIntFunction<? super V> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (t, u, v) -> applyAsBoolean(before1.applyAsInt(t), before2.applyAsInt(u), before3.applyAsInt(v));
    }

    /**
     * Returns a composed {@link IntTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <R> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code IntTriFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(IntUnaryOperator, IntUnaryOperator, IntUnaryOperator)
     * @see #compose(ToIntFunction, ToIntFunction, ToIntFunction)
     */
    @Nonnull
    default <R> IntTriFunction<R> andThen(@Nonnull final BooleanFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriIntToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either function throws an exception, it is
     * relayed to the caller of the composed function. This method is just convenience, to provide the ability to
     * transform this operation to an operation returning {@code boolean}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriIntToBooleanFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriIntToBooleanFunction andThenToBoolean(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsBoolean(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriIntToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriIntToByteFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriIntToByteFunction andThenToByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsByte(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriIntToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriIntToCharFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriIntToCharFunction andThenToChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsChar(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriIntToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriIntToDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriIntToDoubleFunction andThenToDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsDouble(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriIntToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriIntToFloatFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriIntToFloatFunction andThenToFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsFloat(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link IntTernaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code IntTernaryOperator} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default IntTernaryOperator andThenToInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsInt(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriIntToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriIntToLongFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriIntToLongFunction andThenToLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsLong(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriIntToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function. This method is just convenience, to provide the ability to transform this
     * operation to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriIntToShortFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default TriIntToShortFunction andThenToShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.applyAsShort(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Returns a composed {@link IntTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code IntTriConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default IntTriConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(applyAsBoolean(value1, value2, value3));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default BiIntToBooleanFunction partial(int value1) {
        return (value2, value3) -> applyAsBoolean(value1, value2, value3);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default IntToBooleanFunction partial(int value1, int value2) {
        return value3 -> applyAsBoolean(value1, value2, value3);
    }

    /**
     * Applies this function partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the function
     * @param value2 The second argument to partially apply to the function
     * @param value3 The third argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default BooleanSupplier partial(int value1, int value2, int value3) {
        return () -> applyAsBoolean(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriIntToBooleanFunction}. Thereby the
     * primitive input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriIntToBooleanFunction}.
     */
    @Nonnull
    default TriFunction<Integer, Integer, Integer, Boolean> boxed() {
        return this::applyAsBoolean;
    }
}