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
package at.gridtec.lambda4j.predicates.primitives;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one {@code char}-valued argument. This is the {@code
 * char}-consuming primitive type specialization of {@link Predicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(char)}.
 *
 * @see Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharPredicate {

    /**
     * Calls the given {@link CharPredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code CharPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    static boolean call(@Nonnull final CharPredicate predicate, char value) {
        Objects.requireNonNull(predicate);
        return predicate.test(value);
    }

    /**
     * Creates a {@link CharPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharPredicate} which always returns a given value.
     */
    @Nonnull
    static CharPredicate constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link CharPredicate} that tests if the given argument is equal to the one of this predicate according
     * to {@code value == target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code CharPredicate} that tests if the given argument is equal to the one of this predicate.
     * @see #isNotEqual(char)
     */
    @Nonnull
    static CharPredicate isEqual(char target) {
        return value -> value == target;
    }

    /**
     * Returns a {@link CharPredicate} that tests if the given argument is not equal to the one of this predicate
     * according to {@code value != target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code CharPredicate} that tests if the given argument is not equal to the one of this predicate.
     * @see #isEqual(char)
     */
    @Nonnull
    static CharPredicate isNotEqual(char target) {
        return value -> value != target;
    }

    /**
     * Returns a {@link CharPredicate} that always returns {@code true}.
     *
     * @return A {@link CharPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static CharPredicate alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link CharPredicate} the always returns {@code false}.
     *
     * @return A {@link CharPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static CharPredicate alwaysFalse() {
        return value -> false;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value The argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean test(char value);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a {@link CharPredicate} that represents the logical negation of this one.
     *
     * @return A {@code CharPredicate} that represents the logical negation of this one.
     * @see Predicate#negate()
     */
    @Nonnull
    default CharPredicate negate() {
        return value -> !test(value);
    }

    /**
     * Returns a composed {@link CharPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code CharPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code CharPredicate} that will be logically-ANDed with this one
     * @return A composed {@code CharPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(CharPredicate)
     * @see #xor(CharPredicate)
     * @see Predicate#and(Predicate)
     */
    @Nonnull
    default CharPredicate and(@Nonnull final CharPredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link CharPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code CharPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code CharPredicate} that will be logically-ORed with this one
     * @return A composed {@code CharPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(CharPredicate)
     * @see #xor(CharPredicate)
     * @see Predicate#or(Predicate)
     */
    @Nonnull
    default CharPredicate or(@Nonnull final CharPredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) && other.test(value);
    }

    /**
     * Returns a composed {@link CharPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code CharPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code CharPredicate} that will be logically-XORed with this one
     * @return A composed {@code CharPredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(CharPredicate)
     * @see #or(CharPredicate)
     */
    @Nonnull
    default CharPredicate xor(@Nonnull final CharPredicate other) {
        Objects.requireNonNull(other);
        return value -> test(value) ^ other.test(value);
    }

    /**
     * Applies this predicate partially to one argument. The result is an operation of arity {@code 0};
     *
     * @param value The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default BooleanSupplier partial(char value) {
        return () -> test(value);
    }

    /**
     * Returns a composed {@link Predicate} which represents this {@link CharPredicate}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code CharPredicate} with JRE specific methods, only accepting {@code Predicate}.
     *
     * @return A composed {@code Predicate} which represents this {@code CharPredicate}.
     */
    @Nonnull
    default Predicate<Character> boxed() {
        return this::test;
    }
}