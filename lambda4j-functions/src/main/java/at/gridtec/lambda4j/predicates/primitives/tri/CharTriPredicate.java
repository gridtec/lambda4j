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
package at.gridtec.lambda4j.predicates.primitives.tri;

import at.gridtec.lambda4j.predicates.TriPredicate;
import at.gridtec.lambda4j.predicates.primitives.CharPredicate;
import at.gridtec.lambda4j.predicates.primitives.bi.CharBiPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;

/**
 * Represents a predicate (boolean-valued function) of three {@code char}-valued argument. This is the {@code
 * char}-consuming primitive type specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(char, char, char)}.
 *
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharTriPredicate {

    /**
     * Calls the given {@link CharTriPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return The result from the given {@code CharTriPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    static boolean call(@Nonnull final CharTriPredicate predicate, char value1, char value2, char value3) {
        Objects.requireNonNull(predicate);
        return predicate.test(value1, value2, value3);
    }

    /**
     * Creates a {@link CharTriPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link CharPredicate}.
     *
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code CharTriPredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@code CharPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharTriPredicate onlyFirst(@Nonnull final CharPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value1);
    }

    /**
     * Creates a {@link CharTriPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link CharPredicate}.
     *
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code CharTriPredicate} which uses the {@code second} parameter of this one as argument for
     * the given {@code CharPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharTriPredicate onlySecond(@Nonnull final CharPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value2);
    }

    /**
     * Creates a {@link CharTriPredicate} which uses the {@code third} parameter of this one as argument for the given
     * {@link CharPredicate}.
     *
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code CharTriPredicate} which uses the {@code third} parameter of this one as argument for the
     * given {@code CharPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static CharTriPredicate onlyThird(@Nonnull final CharPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2, value3) -> predicate.test(value3);
    }

    /**
     * Creates a {@link CharTriPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharTriPredicate} which always returns a given value.
     */
    @Nonnull
    static CharTriPredicate constant(boolean ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Returns a {@link CharTriPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code CharTriPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(char, char, char)
     */
    @Nonnull
    static CharTriPredicate isEqual(char target1, char target2, char target3) {
        return (value1, value2, value3) -> (value1 == target1) && (value2 == target2) && (value3 == target3);
    }

    /**
     * Returns a {@link CharTriPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @param target3 The third target value with which to compare for equality
     * @return A {@code CharTriPredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(char, char, char)
     */
    @Nonnull
    static CharTriPredicate isNotEqual(char target1, char target2, char target3) {
        return (value1, value2, value3) -> (value1 != target1) && (value2 != target2) && (value3 != target3);
    }

    /**
     * Returns a {@link CharTriPredicate} that always returns {@code true}.
     *
     * @return A {@link CharTriPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static CharTriPredicate alwaysTrue() {
        return (value1, value2, value3) -> true;
    }

    /**
     * Returns a {@link CharTriPredicate} the always returns {@code false}.
     *
     * @return A {@link CharTriPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static CharTriPredicate alwaysFalse() {
        return (value1, value2, value3) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(char value1, char value2, char value3);

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
     * Returns a {@link CharTriPredicate} that represents the logical negation of this one.
     *
     * @return A {@code CharTriPredicate} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    @Nonnull
    default CharTriPredicate negate() {
        return (value1, value2, value3) -> !test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link CharTriPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code CharTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code CharTriPredicate} that will be logically-ANDed with this one
     * @return A composed {@code CharTriPredicate} that represents the short-circuiting logical AND of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(CharTriPredicate)
     * @see #xor(CharTriPredicate)
     * @see TriPredicate#and(TriPredicate)
     */
    @Nonnull
    default CharTriPredicate and(@Nonnull final CharTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link CharTriPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code CharTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code CharTriPredicate} that will be logically-ORed with this one
     * @return A composed {@code CharTriPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(CharTriPredicate)
     * @see #xor(CharTriPredicate)
     * @see TriPredicate#or(TriPredicate)
     */
    @Nonnull
    default CharTriPredicate or(@Nonnull final CharTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) && other.test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link CharTriPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code CharTriPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code CharTriPredicate} that will be logically-XORed with this one
     * @return A composed {@code CharTriPredicate} that represents the short-circuiting logical XOR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(CharTriPredicate)
     * @see #or(CharTriPredicate)
     * @see TriPredicate#xor(TriPredicate)
     */
    @Nonnull
    default CharTriPredicate xor(@Nonnull final CharTriPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2, value3) -> test(value1, value2, value3) ^ other.test(value1, value2, value3);
    }

    /**
     * Applies this predicate partially to one argument. The result is a predicate of arity {@code 2};
     *
     * @param value1 The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default CharBiPredicate partial(char value1) {
        return (value2, value3) -> test(value1, value2, value3);
    }

    /**
     * Applies this predicate partially to two arguments. The result is a predicate of arity {@code 1}.
     *
     * @param value1 The first argument to partially apply to the predicate
     * @param value2 The second argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default CharPredicate partial(char value1, char value2) {
        return value3 -> test(value1, value2, value3);
    }

    /**
     * Applies this predicate partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the predicate
     * @param value2 The second argument to partially apply to the predicate
     * @param value3 The third argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default BooleanSupplier partial(char value1, char value2, char value3) {
        return () -> test(value1, value2, value3);
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link CharTriPredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code CharTriPredicate}.
     */
    @Nonnull
    default TriPredicate<Character, Character, Character> boxed() {
        return this::test;
    }
}