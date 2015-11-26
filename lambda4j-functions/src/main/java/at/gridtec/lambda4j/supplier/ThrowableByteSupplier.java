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
package at.gridtec.lambda4j.supplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Represents a supplier of {@code byte}-valued results which is able to throw any {@link Throwable}. This is the {@code
 * byte}-producing primitive specialization of {@link ThrowableSupplier}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsByteThrows()}.
 *
 * @see ThrowableByteSupplier
 */

@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableByteSupplier {

    /**
     * Calls the given {@link ThrowableByteSupplier} and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code ThrowableByteSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     * @throws Throwable Any throwable from this suppliers action
     */
    static byte call(@Nonnull final ThrowableByteSupplier supplier) throws Throwable {
        Objects.requireNonNull(supplier);
        return supplier.getAsByteThrows();
    }

    /**
     * Creates a {@link ThrowableByteSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableByteSupplier} which always returns a given value.
     */
    @Nonnull
    static ThrowableByteSupplier constant(byte ret) {
        return () -> ret;
    }

    /**
     * Gets the supplied result from this supplier. Thereby any {@link Throwable} type is able to be thrown.
     *
     * @return The supplied result.
     * @throws Throwable Any throwable from this suppliers action
     */
    byte getAsByteThrows() throws Throwable;

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 0}.
     */
    @Nonnegative
    default int arity() {
        return 0;
    }

    //    /**
    //     * Returns a composed {@link ThrowableSupplier} that first gets the result from this operation, and then applies the
    //     * {@code after} operation to the result.
    //     *
    //     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
    //     * @param after The operation to apply after this operation is applied
    //     * @return A composed {@code ThrowableSupplier} that first gets the result from this operation, and then applies the
    //     * {@code after} operation to the result.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @implNote The returned operation is able to handle every type.
    //     */
    //    @Nonnull
    //    default <R> ThrowableSupplier<R> andThen(@Nonnull final ThrowableByteFunction<? extends R> after) {
    //        Objects.requireNonNull(after);
    //        return () -> after.applyThrows(getAsByteThrows());
    //    }
    //
    //    /**
    //     * Returns a composed {@link ThrowableBooleanSupplier} that first gets the result from this operation, and then
    //     * applies the {@code after} operation to the result. This method is just convenience, to provide the ability to
    //     * transform this operation to an operation returning {@code boolean}.
    //     *
    //     * @param after The operation to apply after this operation is applied
    //     * @return A composed {@code ThrowableBooleanSupplier} that first gets the result from this operation, and then
    //     * applies the {@code after} operation to the result.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @see #andThen(ThrowableByteFunction)
    //     */
    //    @Nonnull
    //    default ThrowableBooleanSupplier andThenToBoolean(@Nonnull final BytePredicate after) {
    //        Objects.requireNonNull(after);
    //        return () -> after.testThrows(getAsByteThrows());
    //    }
    //
    //    /**
    //     * Returns a composed {@link ThrowableByteSupplier} that first gets the result from this operation, and then applies
    //     * the {@code after} operation to the result. This method is just convenience, to provide the ability to transform
    //     * this operation to an operation returning {@code byte}.
    //     *
    //     * @param after The operation to apply after this operation is applied
    //     * @return A composed {@code ThrowableByteSupplier} that first gets the result from this operation, and then applies
    //     * the {@code after} operation to the result.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @see #andThen(ThrowableByteFunction)
    //     */
    //    @Nonnull
    //    default ThrowableByteSupplier andThenToByte(@Nonnull final ThrowableByteUnaryOperator after) {
    //        Objects.requireNonNull(after);
    //        return () -> after.applyAsByteThrows(getAsByteThrows());
    //    }
    //
    //    /**
    //     * Returns a composed {@link ThrowableCharSupplier} that first gets the result from this operation, and then applies
    //     * the {@code after} operation to the result. This method is just convenience, to provide the ability to transform
    //     * this operation to an operation returning {@code char}.
    //     *
    //     * @param after The operation to apply after this operation is applied
    //     * @return A composed {@code ThrowableCharSupplier} that first gets the result from this operation, and then applies
    //     * the {@code after} operation to the result.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @see #andThen(ThrowableByteFunction)
    //     */
    //    @Nonnull
    //    default ThrowableCharSupplier andThenToChar(@Nonnull final ThrowableByteToCharFunction after) {
    //        Objects.requireNonNull(after);
    //        return () -> after.applyAsCharThrows(getAsByteThrows());
    //    }
    //
    //    /**
    //     * Returns a composed {@link ThrowableDoubleSupplier} that first gets the result from this operation, and then
    //     * applies the {@code after} operation to the result. This method is just convenience, to provide the ability to
    //     * transform this operation to an operation returning {@code double}.
    //     *
    //     * @param after The operation to apply after this operation is applied
    //     * @return A composed {@code ThrowableDoubleSupplier} that first gets the result from this operation, and then
    //     * applies the {@code after} operation to the result.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @see #andThen(ThrowableByteFunction)
    //     */
    //    @Nonnull
    //    default ThrowableDoubleSupplier andThenToDouble(@Nonnull final ThrowableByteToDoubleFunction after) {
    //        Objects.requireNonNull(after);
    //        return () -> after.applyAsDoubleThrows(getAsByteThrows());
    //    }
    //
    //    /**
    //     * Returns a composed {@link ThrowableFloatSupplier} that first gets the result from this operation, and then
    //     * applies the {@code after} operation to the result. This method is just convenience, to provide the ability to
    //     * transform this operation to an operation returning {@code float}.
    //     *
    //     * @param after The operation to apply after this operation is applied
    //     * @return A composed {@code ThrowableFloatSupplier} that first gets the result from this operation, and then
    //     * applies the {@code after} operation to the result.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @see #andThen(ThrowableByteFunction)
    //     */
    //    @Nonnull
    //    default ThrowableFloatSupplier andThenToFloat(@Nonnull final ThrowableByteToFloatFunction after) {
    //        Objects.requireNonNull(after);
    //        return () -> after.applyAsFloatThrows(getAsByteThrows());
    //    }
    //
    //    /**
    //     * Returns a composed {@link ThrowableIntSupplier} that first gets the result from this operation, and then applies
    //     * the {@code after} operation to the result. This method is just convenience, to provide the ability to transform
    //     * this operation to an operation returning {@code int}.
    //     *
    //     * @param after The operation to apply after this operation is applied
    //     * @return A composed {@code ThrowableIntSupplier} that first gets the result from this operation, and then applies
    //     * the {@code after} operation to the result.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @see #andThen(ThrowableByteFunction)
    //     */
    //    @Nonnull
    //    default ThrowableIntSupplier andThenToInt(@Nonnull final ThrowableByteToIntFunction after) {
    //        Objects.requireNonNull(after);
    //        return () -> after.applyAsIntThrows(getAsByteThrows());
    //    }
    //
    //    /**
    //     * Returns a composed {@link ThrowableLongSupplier} that first gets the result from this operation, and then applies
    //     * the {@code after} operation to the result. This method is just convenience, to provide the ability to transform
    //     * this operation to an operation returning {@code long}.
    //     *
    //     * @param after The operation to apply after this operation is applied
    //     * @return A composed {@code ThrowableLongSupplier} that first gets the result from this operation, and then applies
    //     * the {@code after} operation to the result.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @see #andThen(ThrowableByteFunction)
    //     */
    //    @Nonnull
    //    default ThrowableLongSupplier andThenToLong(@Nonnull final ThrowableByteToLongFunction after) {
    //        Objects.requireNonNull(after);
    //        return () -> after.applyAsLongThrows(getAsByteThrows());
    //
    //    }
    //
    //    /**
    //     * Returns a composed {@link ThrowableShortSupplier} that first gets the result from this operation, and then
    //     * applies the {@code after} operation to the result. This method is just convenience, to provide the ability to
    //     * transform this operation to an operation returning {@code short}.
    //     *
    //     * @param after The operation to apply after this operation is applied
    //     * @return A composed {@code ThrowableShortSupplier} that first gets the result from this operation, and then
    //     * applies the {@code after} operation to the result.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @see #andThen(ThrowableByteFunction)
    //     */
    //    @Nonnull
    //    default ThrowableShortSupplier andThenToShort(@Nonnull final ThrowableByteToShortFunction after) {
    //        Objects.requireNonNull(after);
    //        return () -> after.applyAsShortThrows(getAsByteThrows());
    //    }
    //
    //    /**
    //     * Returns a composed {@link ThrowableConsumer} that first gets the result from this operation, and then consumes
    //     * the result using the given {@link ThrowableByteConsumer}.
    //     *
    //     * @param consumer The operation which consumes the result from this operation
    //     * @return A composed {@code ThrowableConsumer} that first gets the result from this operation, and then consumes
    //     * the result using the given {@code ThrowableByteConsumer}.
    //     * @throws NullPointerException If given argument is {@code null}
    //     * @implNote Due to the fact that a {@link ThrowableSupplier} receives no input, we do not need to pass an argument
    //     * of a particular type to the resulting {@code ThrowableConsumer}. As a result, this method returns a {@code
    //     * ThrowableConsumer} of {@link Void}, whose argument is ignored. Therefore, the input parameter will always be
    //     * {@code null} when the resulting consumer is called with {@code ThrowableConsumer#accept(Object)}.
    //     */
    //    @Nonnull
    //    default ThrowableConsumer<Void> consume(@Nonnull final ThrowableByteConsumer consumer) {
    //        Objects.requireNonNull(consumer);
    //        return ignored -> consumer.acceptThrows(getAsByteThrows());
    //    }

    /**
     * Returns a composed {@link ThrowableSupplier} which represents this {@link ThrowableByteSupplier}. Thereby the
     * primitive input argument for this operation is autoboxed. This method is just convenience to provide the ability
     * to use this {@code ThrowableByteSupplier} with JRE specific methods, only accepting {@code Supplier}.
     *
     * @return A composed {@code ThrowableSupplier} which represents this {@code ThrowableByteSupplier}.
     */
    @Nonnull
    default ThrowableSupplier<Byte> boxed() {
        return this::getAsByteThrows;
    }
}