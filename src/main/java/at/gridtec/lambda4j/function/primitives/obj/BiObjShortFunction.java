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
package at.gridtec.lambda4j.function.primitives.obj;

import at.gridtec.lambda4j.consumer.primitives.obj.BiObjShortConsumer;
import at.gridtec.lambda4j.function.TriFunction;
import at.gridtec.lambda4j.function.primitives.ShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToShortFunction;
import at.gridtec.lambda4j.operators.unary.ShortUnaryOperator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Represents a function that accepts two object-valued and a {@code short}-valued argument, and produces a result. This
 * is the {@code (reference, reference, short)} specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, Object, short)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjShortFunction<T, U, R> {

    /**
     * Creates a {@link BiObjShortFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code BiObjShortFunction} which always returns a given value.
     */
    static <T, U, R> BiObjShortFunction<T, U, R> constant(R r) {
        return (t, u, value) -> r;
    }

    /**
     * Creates a {@link BiObjShortFunction} which uses the first parameter of this one as argument for the given {@link
     * Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjShortFunction} which uses the first parameter of this one as argument for the given
     * {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, R> BiObjShortFunction<T, U, R> onlyFirst(final Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(t);
    }

    /**
     * Creates a {@link BiObjShortFunction} which uses the second parameter of this one as argument for the given {@link
     * Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjShortFunction} which uses the second parameter of this one as argument for the
     * given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, R> BiObjShortFunction<T, U, R> onlySecond(final Function<? super U, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(u);
    }

    /**
     * Creates a {@link BiObjShortFunction} which uses the third parameter of this one as argument for the given {@link
     * ShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjShortFunction} which uses the third parameter of this one as argument for the given
     * {@code ShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T, U, R> BiObjShortFunction<T, U, R> onlyThird(final ShortFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(value);
    }

    /**
     * Performs this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(T t, U u, short value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link BiObjShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first {@code Function} to apply before this operation is applied
     * @param before2 The second {@code Function} to apply before this operation is applied
     * @param before3 The third {@code ShortUnaryOperator} to apply before this operation is applied
     * @return A composed {@code BiObjShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(ToShortFunction)
     * @see #andThen(Function)
     */
    default <A, B> BiObjShortFunction<A, B, R> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ShortUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> apply(before1.apply(a), before2.apply(b), before3.applyAsShort(value));
    }

    /**
     * Returns a composed {@link TriFunction} that applies the given {@code before} functions to its input, and then
     * applies this operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first before {@code Function} to apply before this operation is applied
     * @param before2 The second before {@code Function} to apply before this operation is applied
     * @param before3 The third before {@code ToShortFunction} to apply before this operation is applied
     * @return A composed {@code TriFunction} that applies the given {@code before} functions to its input, and then
     * applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(ToShortFunction)
     * @see #andThen(Function)
     */
    default <A, B, C> TriFunction<A, B, C, R> compose(final Function<? super A, ? extends T> before1,
            final Function<? super B, ? extends U> before2, final ToShortFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.apply(a), before2.apply(b), before3.applyAsShort(c));
    }

    /**
     * Returns a composed {@link ToShortBiObjShortFunction} that first applies this operation to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param after The {@code ToShortFunction} to apply after this operation is applied
     * @return A composed {@code ToShortBiObjShortFunction} that first applies this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, ShortUnaryOperator)
     * @see #compose(Function, Function, ToShortFunction)
     */
    default ToShortBiObjShortFunction<T, U> andThen(final ToShortFunction<? super R> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShort(apply(t, u, value));
    }

    /**
     * Returns a composed {@link ObjShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code Function} to apply after this operation is applied
     * @return A composed {@code ObjShortFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, ShortUnaryOperator)
     * @see #compose(Function, Function, ToShortFunction)
     */
    default <S> BiObjShortFunction<T, U, S> andThen(final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(apply(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortConsumer} that fist applies this operation to its input, and then consumes
     * the result using the given {@code Consumer}. If evaluation of either operator throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The {@code Consumer} which consumes the result from this operation
     * @return A composed {@code BiObjShortConsumer} that first applies this operation to its input, and then consumes
     * the result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default BiObjShortConsumer<T, U> consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(apply(t, u, value));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjShortFunction}. Thereby the primitive
     * input argument for this operation is autoboxed.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjShortFunction}.
     */
    default TriFunction<T, U, Short, R> boxed() {
        return this::apply;
    }
}
