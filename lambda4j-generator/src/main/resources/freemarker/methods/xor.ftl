<#-- @formatter:off -->

<#-- parse only if lambda is of type predicate -->
<#if LambdaUtils.isOfTypePredicate(lambda)>
    <@.namespace.xorMethod/>
</#if>

<#-- a helper macro to centralize and method xor to avoid unnecessary indenting -->
<#macro xorMethod>
/**
 * Returns a composed {@link ${lambda.name}} that represents a short-circuiting logical XOR of this ${lambda.type.simpleName} and
 * another. Any exceptions thrown during evaluation of either ${lambda.type.simpleName} is relayed to the caller; if evaluation of
 * this {@code ${lambda.name}} throws an exception, the {@code other} ${lambda.type.simpleName} will not be evaluated.
 *
 * @param other A {@code ${lambda.name}} that will be logically-XORed with this one
 * @return A composed {@code ${lambda.name}} that represents the short-circuiting logical XOR of this ${lambda.type.simpleName} and
 * the {@code other} ${lambda.type.simpleName}.
<#include "../javadoc/throwsNullPointerException.ftl">
 * @see #and(${lambda.name})
 * @see #or(${lambda.name})
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeString} xor(${annotation.nonnull} final ${lambda.name}${genericParameterTypeStringWithErasure} other) {
    Objects.requireNonNull(other);
    return (${parameterNameString}) -> ${lambda.type.method}(${parameterNameString}) ^ other.${lambda.type.method}(${parameterNameString});
}
</#macro>
<#-- @formatter:on -->