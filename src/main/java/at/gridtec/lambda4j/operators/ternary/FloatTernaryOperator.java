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
package at.gridtec.lambda4j.operators.ternary;

import at.gridtec.lambda4j.operators.unary.FloatUnaryOperator;

import java.util.Objects;

/**
 * Represents an operation on a two {@code float}-valued operands and producing a {@code float}-valued result. This is
 * the primitive type specialization of {@link TernaryOperator} for {@code float}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsFloat(float, float, float)}.
 *
 * @see TernaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface FloatTernaryOperator {

    /**
     * Creates a {@link FloatTernaryOperator} which always returns a given value.
     *
     * @param r The return value for the constant
     * @return A {@code FloatTernaryOperator} which always returns a given value.
     */
    static <T> FloatTernaryOperator constant(float r) {
        return (left, middle, right) -> r;
    }

    /**
     * Creates a {@link FloatTernaryOperator} which uses the left parameter only from the given {@link
     * FloatUnaryOperator}.
     *
     * @return Creates a {@code FloatTernaryOperator} which uses the left parameter only from the given {@code
     * FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> FloatTernaryOperator forLeft(final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(left);
    }

    /**
     * Creates a {@link FloatTernaryOperator} which uses the middle parameter only from the given {@link
     * FloatUnaryOperator}.
     *
     * @return Creates a {@code FloatTernaryOperator} which uses the middle parameter only from the given {@code
     * FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> FloatTernaryOperator forMiddle(final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(middle);
    }

    /**
     * Creates a {@link FloatTernaryOperator} which uses the right parameter only from the given {@link
     * FloatUnaryOperator}.
     *
     * @return Creates a {@code FloatTernaryOperator} which uses the right parameter only from the given {@code
     * FloatUnaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> FloatTernaryOperator forRight(final FloatUnaryOperator operator) {
        Objects.requireNonNull(operator);
        return (left, middle, right) -> operator.applyAsFloat(right);
    }

    /**
     * Applies this operator to the given operand arguments.
     *
     * @param left The first argument to the operator (left input)
     * @param middle The second argument to the operator (middle input)
     * @param right The third argument to the operator (right input)
     * @return The return value from the operator.
     */
    float applyAsFloat(float left, float middle, float right);

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies the given {@code before} operators to its
     * input, and then applies this operator to the result. If evaluation of either operator throws an exception, it is
     * relayed to the caller of the composed operator.
     *
     * @param before1 The first {@code FloatUnaryOperator} to apply before this operator is applied
     * @param before2 The second {@code FloatUnaryOperator} to apply before this operator is applied
     * @param before3 The third {@code FloatUnaryOperator} to apply before this operator is applied
     * @return A composed {@code FloatTernaryOperator} that first applies the given {@code before} operators and then
     * applies this operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #andThen(FloatUnaryOperator)
     */
    default FloatTernaryOperator compose(final FloatUnaryOperator before1, final FloatUnaryOperator before2,
            final FloatUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (left, middle, right) -> applyAsFloat(before1.applyAsFloat(left), before2.applyAsFloat(middle),
                                                     before3.applyAsFloat(right));
    }

    /**
     * Returns a composed {@link FloatTernaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The {@code FloatUnaryOperator} to apply after this operator is applied
     * @return A composed {@code FloatTernaryOperator} that first applies this operator and then applies the {@code
     * after} operator.
     * @throws NullPointerException If one of the given operators are {@code null}
     * @see #compose(FloatUnaryOperator, FloatUnaryOperator, FloatUnaryOperator)
     */
    default FloatTernaryOperator andThen(FloatUnaryOperator after) {
        Objects.requireNonNull(after);
        return (left, middle, right) -> after.applyAsFloat(applyAsFloat(left, middle, right));
    }
}