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
package at.gridtec.lambda4j.operators.binary;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;

/**
 * This functional interface implements a {@link IntBinaryOperator} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsIntThrows(int, int)}.
 *
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableIntBinaryOperator extends IntBinaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableIntBinaryOperator}. This is a convenience
     * method in case the given {@link ThrowableIntBinaryOperator} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableIntBinaryOperator} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableIntBinaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableIntBinaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableIntBinaryOperator wrap(final ThrowableIntBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableIntBinaryOperator} from the given {@link IntBinaryOperator}. This method is just
     * convenience to provide a mapping for the non-throwable/throwable instances of the corresponding functional
     * interface.
     *
     * @param lambda A {@code IntBinaryOperator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableIntBinaryOperator} from the given {@code IntBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableIntBinaryOperator from(final IntBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda::applyAsInt;
    }

    /**
     * Creates a {@link ThrowableIntBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableIntBinaryOperator} which always returns a given value.
     */
    static ThrowableIntBinaryOperator constant(int ret) {
        return (left, right) -> ret;
    }

    /**
     * Returns a {@link ThrowableIntBinaryOperator} which returns the lesser of two elements according to {@link
     * Integer#min(int, int)} operation.
     *
     * @return A {@code ThrowableIntBinaryOperator} which returns the lesser of its operands.
     * @see BinaryOperator#minBy(Comparator)
     * @see Integer#min(int, int)
     * @see Math#min(int, int)
     */
    static ThrowableIntBinaryOperator min() {
        return Integer::min;
    }

    /**
     * Returns a {@link ThrowableIntBinaryOperator} which returns the greater of two elements according to {@link
     * Integer#max(int, int)} operation.
     *
     * @return A {@code ThrowableIntBinaryOperator} which returns the greater of its operands.
     * @see BinaryOperator#maxBy(Comparator)
     * @see Integer#max(int, int)
     * @see Math#max(int, int)
     */
    static ThrowableIntBinaryOperator max() {
        return Integer::max;
    }

    /**
     * Creates a {@link IntBinaryOperator} which uses the left parameter as argument for the given {@link
     * IntUnaryOperator}.
     *
     * @return Creates a {@code IntBinaryOperator} which uses the left parameter as argument for the given {@code
     * IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static IntBinaryOperator onlyLeft(final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsInt(left);
    }

    /**
     * Creates a {@link IntBinaryOperator} which uses the right parameter as argument for the given {@link
     * IntUnaryOperator}.
     *
     * @return Creates a {@code IntBinaryOperator} which uses the right parameter as argument for the given {@code
     * IntUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static IntBinaryOperator onlyRight(final IntUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, right) -> operator.applyAsInt(right);
    }

    /**
     * The apply method for this {@link IntBinaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    int applyAsIntThrows(int left, int right) throws Exception;

    /**
     * Overrides the {@link IntBinaryOperator#applyAsInt(int, int)} method by using a redefinition as default method. It
     * calls the {@link #applyAsIntThrows(int, int)} method of this interface and catches the thrown {@link Exception}s
     * from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other throwable types are sneakily
     * thrown.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default int applyAsInt(int left, int right) {
        try {
            return applyAsIntThrows(left, right);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator} to its
     * input, and if an  error occurred, applies the given one. The exception from this {@code
     * ThrowableIntBinaryOperator} is ignored.
     *
     * @param other A {@code ThrowableIntBinaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and
     * if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableIntBinaryOperator orElse(final ThrowableIntBinaryOperator other) {
        Objects.requireNonNull(other);
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return other.applyAsIntThrows(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator} to its
     * input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableIntBinaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableIntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and
     * if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableIntBinaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its input,
     * and if an error occurred, applies the given {@code IntBinaryOperator} representing a fallback. The exception from
     * this {@code ThrowableIntBinaryOperator} is ignored.
     *
     * @param fallback A {@code IntBinaryOperator} to be applied if this one fails
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, applies the given {@code IntBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default IntBinaryOperator fallbackTo(final IntBinaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return fallback.applyAsInt(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its input,
     * and if an error occurred, returns the given value. The exception from this {@code ThrowableIntBinaryOperator} is
     * ignored.
     *
     * @param value The value to be returned if this {@code ThrowableIntBinaryOperator} fails
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, returns the given value.
     */
    default IntBinaryOperator orReturn(int value) {
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its input,
     * and if an error occurred, returns the supplied value from the given {@link IntSupplier}. The exception from this
     * {@code ThrowableIntBinaryOperator} is ignored.
     *
     * @param supplier A {@code IntSupplier} to return a supplied value if this {@code ThrowableIntBinaryOperator}
     * fails
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, the supplied value from the given {@code IntSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default IntBinaryOperator orReturn(final IntSupplier supplier) {
        Objects.requireNonNull(supplier);
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return supplier.getAsInt();
            }
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its input,
     * and if an error occurred, returns the left value from this operator. The exception from this {@code
     * ThrowableIntBinaryOperator} is ignored.
     *
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, returns the left value from this operator.
     */
    default IntBinaryOperator orReturnLeft() {
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return left;
            }
        };
    }

    /**
     * Returns a composed {@link IntBinaryOperator} that applies this {@link ThrowableIntBinaryOperator} to its input,
     * and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableIntBinaryOperator} is ignored.
     *
     * @return A composed {@code IntBinaryOperator} that applies this {@code ThrowableIntBinaryOperator}, and if an
     * error occurred, returns the right value from this operator.
     */
    default IntBinaryOperator orReturnRight() {
        return (left, right) -> {
            try {
                return applyAsIntThrows(left, right);
            } catch (Exception ignored) {
                return right;
            }
        };
    }
}
