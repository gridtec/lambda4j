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

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

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
     * Calls the given {@link BiObjShortFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjShortFunction}.
     * @throws NullPointerException If the given function is {@code null}
     */
    static <T, U, R> R call(@Nonnull final BiObjShortFunction<? super T, ? super U, ? extends R> function, final T t,
            final U u, short value) {
        Objects.requireNonNull(function);
        return function.apply(t, u, value);
    }

    /**
     * Creates a {@link BiObjShortFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjShortFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjShortFunction<T, U, R> onlyFirst(@Nonnull final Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(t);
    }

    /**
     * Creates a {@link BiObjShortFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjShortFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code Function}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjShortFunction<T, U, R> onlySecond(@Nonnull final Function<? super U, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(u);
    }

    /**
     * Creates a {@link BiObjShortFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link ShortFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjShortFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@code ShortFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U, R> BiObjShortFunction<T, U, R> onlyThird(@Nonnull final ShortFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.apply(value);
    }

    /**
     * Creates a {@link BiObjShortFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code BiObjShortFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, R> BiObjShortFunction<T, U, R> constant(R r) {
        return (t, u, value) -> r;
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
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @param value The primitive value to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default R apply(@Nonnull Pair<T, U> tuple, short value) {
        Objects.requireNonNull(tuple);
        return apply(tuple.getLeft(), tuple.getRight(), value);
    }

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
     * Returns a composed {@link BiObjShortFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param before1 The first operation to apply before this function is applied
     * @param before2 The second operation to apply before this function is applied
     * @param before3 The third operation to apply before this function is applied
     * @return A composed {@code BiObjShortFunction} that first applies the {@code before} operations to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The last input argument of this method is the primitive specialization of {@link UnaryOperator}.
     * Therefore the operation handles a primitive type. In this case this is {@code short}.
     * @see #andThen(Function)
     */
    @Nonnull
    default <A, B> BiObjShortFunction<A, B, R> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2, @Nonnull final ShortUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, value) -> apply(before1.apply(a), before2.apply(b), before3.applyAsShort(value));
    }

    /**
     * Returns a composed {@link TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <A> The type of the argument to the first before operation
     * @param <B> The type of the argument to the second before operation
     * @param <C> The type of the argument to the third before operation
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @implNote The input arguments of this method are able to handle every type.
     * @see #andThen(Function)
     */
    @Nonnull
    default <A, B, C> TriFunction<A, B, C, R> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2,
            @Nonnull final ToShortFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.apply(a), before2.apply(b), before3.applyAsShort(c));
    }

    /**
     * Returns a composed {@link BiObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either function throws an exception, it is relayed to the
     * caller of the composed function.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjShortFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(Function, Function, ShortUnaryOperator)
     * @see #compose(Function, Function, ToShortFunction)
     */
    @Nonnull
    default <S> BiObjShortFunction<T, U, S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(apply(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjShortConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjShortConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjShortConsumer<T, U> consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(apply(t, u, value));
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param t The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ObjShortFunction<U, R> partial(T t) {
        return (u, value) -> apply(t, u, value);
    }

    /**
     * Applies this function partially to one argument. The result is a function of arity {@code 2};
     *
     * @param value The argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default BiFunction<T, U, R> partial(short value) {
        return (t, u) -> apply(t, u, value);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the function
     * @param u The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default ShortFunction<R> partial(T t, U u) {
        return value -> apply(t, u, value);
    }

    /**
     * Applies this function partially to two arguments. The result is a function of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the function
     * @param value The second argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default Function<U, R> partial(T t, short value) {
        return u -> apply(t, u, value);
    }

    /**
     * Applies this function partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param t The first argument to partially apply to the function
     * @param u The second argument to partially apply to the function
     * @param value The third argument to partially apply to the function
     * @return A partial application of this function.
     */
    @Nonnull
    default Supplier<R> partial(T t, U u, short value) {
        return () -> apply(t, u, value);
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ObjShortFunction<Pair<T, U>, R> tupled() {
        return this::apply;
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     */
    @Nonnull
    default BiObjShortFunction<T, U, Optional<R>> nonNull() {
        return (t, u, value) -> Optional.ofNullable(apply(t, u, value));
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjShortFunction}. Thereby the primitive
     * input argument for this function is autoboxed.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjShortFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Short, R> boxed() {
        return this::apply;
    }
}
