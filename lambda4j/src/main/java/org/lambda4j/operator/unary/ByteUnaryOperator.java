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

package org.lambda4j.operator.unary;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.lambda4j.Lambda;
import org.lambda4j.consumer.ByteConsumer;
import org.lambda4j.function.ByteFunction;
import org.lambda4j.function.conversion.BooleanToByteFunction;
import org.lambda4j.function.conversion.ByteToCharFunction;
import org.lambda4j.function.conversion.ByteToDoubleFunction;
import org.lambda4j.function.conversion.ByteToFloatFunction;
import org.lambda4j.function.conversion.ByteToIntFunction;
import org.lambda4j.function.conversion.ByteToLongFunction;
import org.lambda4j.function.conversion.ByteToShortFunction;
import org.lambda4j.function.conversion.CharToByteFunction;
import org.lambda4j.function.conversion.DoubleToByteFunction;
import org.lambda4j.function.conversion.FloatToByteFunction;
import org.lambda4j.function.conversion.IntToByteFunction;
import org.lambda4j.function.conversion.LongToByteFunction;
import org.lambda4j.function.conversion.ShortToByteFunction;
import org.lambda4j.function.to.ToByteFunction;
import org.lambda4j.predicate.BytePredicate;

/**
 * Represents an operation that accepts one {@code byte}-valued input argument and produces a {@code byte}-valued
 * result. This is a primitive specialization of {@link UnaryOperator2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(byte)}.
 *
 * @see UnaryOperator2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteUnaryOperator extends Lambda {

    /**
     * Constructs a {@link ByteUnaryOperator} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ByteUnaryOperator} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @CheckForNull
    @Nullable
    static ByteUnaryOperator of(@Nullable ByteUnaryOperator expression) {
        return expression;
    }

    /**
     * Calls the given {@link ByteUnaryOperator} with the given argument and returns its result.
     *
     * @param operator The operator to be called
     * @param value The argument to the operator
     * @return The result from the given {@code ByteUnaryOperator}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static byte call(@Nonnull ByteUnaryOperator operator, byte value) {
        Objects.requireNonNull(operator);
        return operator.applyAsByte(value);
    }

    /**
     * Returns a {@link ByteUnaryOperator} that always returns its input argument.
     *
     * @return A {@code ByteUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static ByteUnaryOperator identity() {
        return value -> value;
    }

    /**
     * Creates a {@link ByteUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static ByteUnaryOperator constant(byte ret) {
        return value -> ret;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param value The argument to the operator
     * @return The return value from the operator, which is its result.
     */
    byte applyAsByte(byte value);

    /**
     * Returns the number of arguments for this operator.
     *
     * @return The number of arguments for this operator.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies the {@code before} function to its input, and then
     * applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the given function, and of composed function
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ToByteFunction} that first applies the {@code before} function to its input, and then
     * applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A> ToByteFunction<A> compose(@Nonnull ToByteFunction<? super A> before) {
        Objects.requireNonNull(before);
        return a -> applyAsByte(before.applyAsByte(a));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code BooleanToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanToByteFunction composeFromBoolean(@Nonnull BooleanToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsByte(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive operator is executed.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code ByteUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteUnaryOperator composeFromByte(@Nonnull ByteUnaryOperator before) {
        Objects.requireNonNull(before);
        return value -> applyAsByte(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link CharToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code CharToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharToByteFunction composeFromChar(@Nonnull CharToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsByte(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link DoubleToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code DoubleToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleToByteFunction composeFromDouble(@Nonnull DoubleToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsByte(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link FloatToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code FloatToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatToByteFunction composeFromFloat(@Nonnull FloatToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsByte(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link IntToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code IntToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntToByteFunction composeFromInt(@Nonnull IntToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsByte(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link LongToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code LongToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongToByteFunction composeFromLong(@Nonnull LongToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsByte(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ShortToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive operator is executed.
     *
     * @param before The function to apply before this operator is applied
     * @return A composed {@code ShortToByteFunction} that first applies the {@code before} function to its input, and
     * then applies this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortToByteFunction composeFromShort(@Nonnull ShortToByteFunction before) {
        Objects.requireNonNull(before);
        return value -> applyAsByte(before.applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ByteFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ByteFunction<S> andThen(@Nonnull ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsByte(value));
    }

    /**
     * Returns a composed {@link BytePredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this operator is applied
     * @return A composed {@code BytePredicate} that first applies this operator to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BytePredicate andThenToBoolean(@Nonnull BytePredicate after) {
        Objects.requireNonNull(after);
        return value -> after.test(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ByteUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteUnaryOperator andThenToByte(@Nonnull ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToCharFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code char}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ByteToCharFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ByteToCharFunction andThenToChar(@Nonnull ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code double}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ByteToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ByteToDoubleFunction andThenToDouble(@Nonnull ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code float}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ByteToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ByteToFloatFunction andThenToFloat(@Nonnull ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code int}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ByteToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ByteToIntFunction andThenToInt(@Nonnull ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToLongFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code long}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ByteToLongFunction} that first applies this operator to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ByteToLongFunction andThenToLong(@Nonnull ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive operator to an operation returning {@code short}.
     *
     * @param after The function to apply after this operator is applied
     * @return A composed {@code ByteToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ByteToShortFunction andThenToShort(@Nonnull ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteConsumer} that first applies this operator to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteConsumer consume(@Nonnull ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsByte(value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ByteUnaryOperator}. Whenever it is called, the mapping
     * between the input parameter and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the operator and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ByteUnaryOperator}.
     * @implSpec This implementation does not allow the input parameter or return value to be {@code null} for the
     * resulting memoized operator, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized operator can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ByteUnaryOperator memoized() {
        if (isMemoized()) {
            return this;
        } else {
            Map<Byte, Byte> cache = new ConcurrentHashMap<>();
            Object lock = new Object();
            return (ByteUnaryOperator & Memoized) value -> {
                byte returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(value, this::applyAsByte);
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link UnaryOperator2} which represents this {@link ByteUnaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method provides the possibility to use this {@code
     * ByteUnaryOperator} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code UnaryOperator2} which represents this {@code ByteUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator2<Byte> boxed() {
        return this::applyAsByte;
    }

}