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
package at.gridtec.lambda4j.predicates.primitives.obj;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;

/**
 * This functional interface implements a {@link ObjDoublePredicate} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the <em>throws</em> keyword. The exception
 * is still thrown, but the Java compiler stops warning about it.
 * <p>
 * However, when using this throwing lambda, be aware of the following consequences: <ol> <li>If the calling code is to
 * handle a thrown {@code Exception}, it MUST be declared in the methods <em>throws</em> clause which uses this lambda.
 * The compiler will not force you to add it.</li> <li>If the calling code already handles a thrown {@code Exception},
 * it needs to be declared in the methods <em>throws</em> clause which uses this lambda. If not the compiler prints an
 * error that the corresponding {@code try} block never throws the specific exception.</li> <li>In any case, there is no
 * way of explicitly catching the thrown {@code Exception} in the method which uses this lambda. If you try, the
 * compiler prints an error that the corresponding {@code try} block never throws the specific exception.</li> </ol>
 * <p>
 * When the calling code never throws the specific exception that it declares, you should omit it. For example: {@code
 * new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but UTF-8 is guaranteed by the Java specification
 * to be always present. The exception should therefore be omitted.
 * <p>
 * Moreover, if no checked exception should be used at all or its use is inappropriate for any reasons, omit the
 * declaration in the <em>throws</em> clause. The checked exception will behave just like a normal <b>unchecked</b>
 * exception due to sneaky throwing.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(Object, double)}.
 *
 * @param <T> The type of argument to the predicate
 * @see java.util.function.BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableObjDoublePredicate<T> extends ObjDoublePredicate<T> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableObjDoublePredicate}. This is a
     * convenience method in case the given {@link ThrowableObjDoublePredicate} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableObjDoublePredicate} is returned as-is.
     *
     * @param <T> The type of argument to the predicate
     * @param lambda The {@code ThrowableObjDoublePredicate} which should be returned as-is.
     * @return The given {@code ThrowableObjDoublePredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableObjDoublePredicate<T> wrap(final ThrowableObjDoublePredicate<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableObjDoublePredicate} from the given {@link ObjDoublePredicate}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param <T> The type of argument to the predicate
     * @param lambda A {@code ObjDoublePredicate} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableObjDoublePredicate} from the given {@code ObjDoublePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableObjDoublePredicate<T> from(final ObjDoublePredicate<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::test;
    }

    /**
     * Creates a {@link ThrowableObjDoublePredicate} which always returns a given value.
     *
     * @param <T> The type of argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableObjDoublePredicate} which always returns a given value.
     */
    static <T> ThrowableObjDoublePredicate<T> constant(boolean ret) {
        return (t, value) -> ret;
    }

    /**
     * Returns a {@link ThrowableObjDoublePredicate} that tests if the given arguments are equal to the ones of this
     * predicate according to {@code value == target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ThrowableObjDoublePredicate} that tests if the given arguments are equal to the ones of this
     * predicate.
     * @see #isNotEqual(Object, double)
     */
    static <T> ThrowableObjDoublePredicate<T> isEqual(Object targetRef, double targetValue) {
        return (t, value) -> (t == null ? targetRef == null : t.equals(targetRef)) && (value == targetValue);
    }

    /**
     * Returns a {@link ThrowableObjDoublePredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@code value != target} method.
     *
     * @param <T> The type of argument to the predicate
     * @param targetRef The first target value with which to compare for equality
     * @param targetValue The second target value with which to compare for equality
     * @return A {@code ThrowableObjDoublePredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, double)
     */
    static <T> ThrowableObjDoublePredicate<T> isNotEqual(Object targetRef, double targetValue) {
        return (t, value) -> !(t == null ? targetRef == null : t.equals(targetRef)) || (value != targetValue);
    }

    /**
     * Returns a {@link ThrowableObjDoublePredicate} that always returns {@code true}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ThrowableObjDoublePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    static <T> ThrowableObjDoublePredicate<T> alwaysTrue() {
        return (t, value) -> true;
    }

    /**
     * Returns a {@link ThrowableObjDoublePredicate} the always returns {@code false}.
     *
     * @param <T> The type of argument to the predicate
     * @return A {@link ThrowableObjDoublePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static <T> ThrowableObjDoublePredicate<T> alwaysFalse() {
        return (t, value) -> false;
    }

    /**
     * The test method for this {@link ThrowableObjDoublePredicate} which is able to throw any {@link Exception} type.
     *
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @throws Exception Any exception from this functions action
     */
    boolean testThrows(T t, double value) throws Exception;

    /**
     * Overrides the {@link ObjDoublePredicate#test(Object, double)} method by using a redefinition as default method.
     * It calls the {@link #testThrows(Object, double)} method of this interface and catches the thrown {@link
     * Exception}s from it . If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types
     * are sneakily thrown.
     *
     * @param t The first argument to the predicate
     * @param value The second argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default boolean test(T t, double value) {
        try {
            return testThrows(t, value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableObjDoublePredicate} that applies this {@code ThrowableObjDoublePredicate} to
     * its input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableObjDoublePredicate} is ignored.
     *
     * @param other A {@code ThrowableObjDoublePredicate} to be applied if this one fails
     * @return A composed {@code ThrowableObjDoublePredicate} that applies this {@code ThrowableObjDoublePredicate}, and
     * if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableObjDoublePredicate<T> orElse(final ThrowableObjDoublePredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t, value) -> {
            try {
                return testThrows(t, value);
            } catch (Exception ignored) {
                return other.testThrows(t, value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableObjDoublePredicate} that applies this {@code ThrowableObjDoublePredicate} to
     * its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableObjDoublePredicate} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableObjDoublePredicate} that applies this {@code ThrowableObjDoublePredicate}, and
     * if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableObjDoublePredicate<T> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (t, value) -> {
            try {
                return testThrows(t, value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ObjDoublePredicate} that applies this {@link ThrowableObjDoublePredicate} to its input,
     * and if an error occurred, applies the given {@code ObjDoublePredicate} representing a fallback. The exception
     * from this {@code ThrowableObjDoublePredicate} is ignored.
     *
     * @param fallback A {@code ObjDoublePredicate} to be applied if this one fails
     * @return A composed {@code ObjDoublePredicate} that applies this {@code ThrowableObjDoublePredicate}, and if an
     * error occurred, applies the given {@code ObjDoublePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ObjDoublePredicate<T> fallbackTo(final ObjDoublePredicate<? super T> fallback) {
        Objects.requireNonNull(fallback);
        return (t, value) -> {
            try {
                return testThrows(t, value);
            } catch (Exception ignored) {
                return fallback.test(t, value);
            }
        };
    }

    /**
     * Returns a composed {@link ObjDoublePredicate} that applies this {@link ThrowableObjDoublePredicate} to its input,
     * and if an error occurred, returns {@code true}. The exception from this {@code ThrowableObjDoublePredicate} is
     * ignored.
     *
     * @return A composed {@code ObjDoublePredicate} that applies this {@code ThrowableObjDoublePredicate}, and if an
     * error occurred, returns {@code true}.
     */
    default ObjDoublePredicate<T> orReturnTrue() {
        return (t, value) -> {
            try {
                return testThrows(t, value);
            } catch (Exception ignored) {
                return true;
            }
        };
    }

    /**
     * Returns a composed {@link ObjDoublePredicate} that applies this {@link ThrowableObjDoublePredicate} to its input,
     * and if an error occurred, returns {@code false}. The exception from this {@code ThrowableObjDoublePredicate} is
     * ignored.
     *
     * @return A composed {@code ObjDoublePredicate} that applies this {@code ThrowableObjDoublePredicate}, and if an
     * error occurred, returns {@code false}.
     */
    default ObjDoublePredicate<T> orReturnFalse() {
        return (t, value) -> {
            try {
                return testThrows(t, value);
            } catch (Exception ignored) {
                return false;
            }
        };
    }
}
