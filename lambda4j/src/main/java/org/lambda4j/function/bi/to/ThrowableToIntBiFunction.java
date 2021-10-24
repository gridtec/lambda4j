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

package org.lambda4j.function.bi.to;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.ToIntBiFunction;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.lang3.tuple.Pair;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ThrowableIntConsumer;
import org.lambda4j.consumer.bi.ThrowableBiConsumer;
import org.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import org.lambda4j.core.util.ThrowableUtils;
import org.lambda4j.function.ThrowableFunction;
import org.lambda4j.function.ThrowableIntFunction;
import org.lambda4j.function.bi.ThrowableBiFunction;
import org.lambda4j.function.conversion.ThrowableIntToByteFunction;
import org.lambda4j.function.conversion.ThrowableIntToCharFunction;
import org.lambda4j.function.conversion.ThrowableIntToDoubleFunction;
import org.lambda4j.function.conversion.ThrowableIntToFloatFunction;
import org.lambda4j.function.conversion.ThrowableIntToLongFunction;
import org.lambda4j.function.conversion.ThrowableIntToShortFunction;
import org.lambda4j.function.to.ThrowableToIntFunction;
import org.lambda4j.operator.unary.ThrowableIntUnaryOperator;
import org.lambda4j.predicate.ThrowableIntPredicate;
import org.lambda4j.predicate.bi.ThrowableBiPredicate;

/**
 * Represents an operation that accepts two input arguments and produces a {@code int}-valued result which is able to
 * throw any {@link Throwable}. This is a primitive specialization of {@link ThrowableBiFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsIntThrows(Object, Object)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @param <X> The type of the throwable to be thrown by this function
 * @apiNote This is a throwable JDK lambda.
 * @see ThrowableBiFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableToIntBiFunction<T, U, X extends Throwable> extends Lambda, ToIntBiFunction<T, U> {

    /**
     * Constructs a {@link ThrowableToIntBiFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableToIntBiFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static <T, U, X extends Throwable> ThrowableToIntBiFunction<T, U, X> of(
            @Nullable ThrowableToIntBiFunction<T, U, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableToIntBiFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The result from the given {@code ThrowableToIntBiFunction}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     */
    static <T, U, X extends Throwable> int call(
            @Nonnull ThrowableToIntBiFunction<? super T, ? super U, ? extends X> function, T t, U u) throws X {
        Objects.requireNonNull(function);
        return function.applyAsIntThrows(t, u);
    }

    /**
     * Creates a {@link ThrowableToIntBiFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ThrowableToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableToIntBiFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ThrowableToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableToIntBiFunction<T, U, X> onlyFirst(
            @Nonnull ThrowableToIntFunction<? super T, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyAsIntThrows(t);
    }

    /**
     * Creates a {@link ThrowableToIntBiFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableToIntBiFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableToIntBiFunction<T, U, X> onlySecond(
            @Nonnull ThrowableToIntFunction<? super U, ? extends X> function) {
        Objects.requireNonNull(function);
        return (t, u) -> function.applyAsIntThrows(u);
    }

    /**
     * Creates a {@link ThrowableToIntBiFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param <X> The type of the throwable to be thrown by this function
     * @param ret The return value for the constant
     * @return A {@code ThrowableToIntBiFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableToIntBiFunction<T, U, X> constant(int ret) {
        return (t, u) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @throws X Any throwable from this functions action
     */
    int applyAsIntThrows(T t, U u) throws X;

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @return The return value from the function, which is its result.
     * @apiNote This method mainly exists to use this {@link ThrowableToIntBiFunction} in JRE specific methods only
     * accepting {@link ToIntBiFunction}. If this function should be applied, then the {@link #applyAsIntThrows(Object,
     * Object)} method should be used.
     * @apiNote Overrides the {@link ToIntBiFunction#applyAsInt(Object, Object)} method by using a redefinition as
     * default method. This implementation calls the {@link #applyAsIntThrows(Object, Object)} method of this function
     * and catches the eventually thrown {@link Throwable} from it. If it is of type {@link RuntimeException} or {@link
     * Error} it is rethrown as is. Other {@code Throwable} types are wrapped in a {@link
     * ThrownByFunctionalInterfaceException}.
     */
    @Override
    default int applyAsInt(T t, U u) {
        return nest().applyAsInt(t, u);
    }

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this functions action
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default int applyAsIntThrows(@Nonnull Pair<T, U> tuple) throws X {
        Objects.requireNonNull(tuple);
        return applyAsIntThrows(tuple.getLeft(), tuple.getRight());
    }

    /**
     * Applies this function partially to some arguments of this one, producing a {@link ThrowableToIntFunction} as
     * result.
     *
     * @param t The first argument to this function used to partially apply this function
     * @return A {@code ThrowableToIntFunction} that represents this function partially applied the some arguments.
     */
    @Nonnull
    default ThrowableToIntFunction<U, X> applyAsIntThrowsPartially(T t) {
        return u -> applyAsIntThrows(t, u);
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
     * Returns a composed {@link ThrowableToIntBiFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code ThrowableToIntBiFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> ThrowableToIntBiFunction<A, B, X> compose(
            @Nonnull ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull ThrowableFunction<? super B, ? extends U, ? extends X> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> applyAsIntThrows(before1.applyThrows(a), before2.applyThrows(b));
    }

    /**
     * Returns a composed {@link ThrowableBiFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableBiFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiFunction<T, U, S, X> andThen(
            @Nonnull ThrowableIntFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a composed {@link ThrowableBiPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ThrowableBiPredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiPredicate<T, U, X> andThenToBoolean(@Nonnull ThrowableIntPredicate<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.testThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a composed {@link ThrowableToByteBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToByteBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableToByteBiFunction<T, U, X> andThenToByte(
            @Nonnull ThrowableIntToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsByteThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a composed {@link ThrowableToCharBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToCharBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableToCharBiFunction<T, U, X> andThenToChar(
            @Nonnull ThrowableIntToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsCharThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a composed {@link ThrowableToDoubleBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToDoubleBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableToDoubleBiFunction<T, U, X> andThenToDouble(
            @Nonnull ThrowableIntToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsDoubleThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a composed {@link ThrowableToFloatBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToFloatBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableToFloatBiFunction<T, U, X> andThenToFloat(
            @Nonnull ThrowableIntToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsFloatThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a composed {@link ThrowableToIntBiFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ThrowableToIntBiFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableToIntBiFunction<T, U, X> andThenToInt(
            @Nonnull ThrowableIntUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsIntThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a composed {@link ThrowableToLongBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToLongBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableToLongBiFunction<T, U, X> andThenToLong(
            @Nonnull ThrowableIntToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsLongThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a composed {@link ThrowableToShortBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ThrowableToShortBiFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableToShortBiFunction<T, U, X> andThenToShort(
            @Nonnull ThrowableIntToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u) -> after.applyAsShortThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a composed {@link ThrowableBiConsumer} that fist applies this function to its input, and then consumes
     * the result using the given {@link ThrowableIntConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code ThrowableIntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiConsumer<T, U, X> consume(@Nonnull ThrowableIntConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u) -> consumer.acceptThrows(applyAsIntThrows(t, u));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ThrowableToIntFunction<Pair<T, U>, X> tupled() {
        return this::applyAsIntThrows;
    }

    /**
     * Returns a reversed version of this function. This may be useful in recursive context.
     *
     * @return A reversed version of this function.
     */
    @Nonnull
    default ThrowableToIntBiFunction<U, T, X> reversed() {
        return (u, t) -> applyAsIntThrows(t, u);
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableToIntBiFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableToIntBiFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableToIntBiFunction<T, U, X> memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Pair<T, U>, Integer> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ThrowableToIntBiFunction<T, U, X> & Memoized) (t, u) -> {
                int returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(t, u), ThrowableFunction.of(
                            key -> applyAsIntThrows(key.getLeft(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableBiFunction} which represents this {@link ThrowableToIntBiFunction}. Thereby
     * the primitive input argument for this function is autoboxed. This method provides the possibility to use this
     * {@code ThrowableToIntBiFunction} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableBiFunction} which represents this {@code ThrowableToIntBiFunction}.
     */
    @Nonnull
    default ThrowableBiFunction<T, U, Integer, X> boxed() {
        return this::applyAsIntThrows;
    }

    /**
     * Returns a composed {@link ToIntBiFunction2} that applies this function to its input and nests the thrown {@link
     * Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link ThrownByFunctionalInterfaceException},
     * which is constructed from the thrown {@code Throwable}s message and the thrown {@code Throwable} itself.
     *
     * @return A composed {@link ToIntBiFunction2} that applies this function to its input and nests the thrown {@code
     * Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ToIntBiFunction2<T, U> nest() {
        return nest(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ToIntBiFunction2} that applies this function to its input and nests the thrown {@link
     * Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ToIntBiFunction2} that applies this function to its input and nests the thrown {@code
     * Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ToIntBiFunction2<T, U> nest(@Nonnull Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ToIntBiFunction2} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * function.
     *
     * @param recover The operation to apply if this function throws a {@code Throwable}
     * @return A composed {@link ToIntBiFunction2} that first applies this function to its input, and then applies the
     * {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing function is {@code null}
     * @implSpec The implementation checks that the returned enclosing function from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ToIntBiFunction2<T, U> recover(
            @Nonnull Function<? super Throwable, ? extends ToIntBiFunction<? super T, ? super U>> recover) {
        Objects.requireNonNull(recover);
        return (t, u) -> {
            try {
                return applyAsIntThrows(t, u);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                ToIntBiFunction<? super T, ? super U> function = recover.apply(throwable);
                Objects.requireNonNull(function, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                return function.applyAsInt(t, u);
            }
        };
    }

    /**
     * Returns a composed {@link ToIntBiFunction2} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed function behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this function in the returned composed
     * function by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing function.
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
     * @return A composed {@link ToIntBiFunction2} that applies this function to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ToIntBiFunction2<T, U> sneakyThrow() {
        return (t, u) -> {
            try {
                return applyAsIntThrows(t, u);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}