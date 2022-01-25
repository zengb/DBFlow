package com.dbflow5.processor.utils

import com.dbflow5.codegen.shared.shouldBeNonNull
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.javapoet.JTypeName
import com.squareup.kotlinpoet.javapoet.toKTypeName
import javax.lang.model.element.Element
import javax.lang.model.type.TypeMirror
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap
import kotlin.reflect.jvm.internal.impl.name.FqName

fun TypeMirror.toKTypeName() = JTypeName.get(this).toKTypeName()
    .javaToKotlinType()

fun Element.javaToKotlinType(): TypeName =
    asType().asTypeName().javaToKotlinType()

fun TypeName.javaToKotlinType(): TypeName {
    return if (this is ParameterizedTypeName) {
        (rawType.javaToKotlinType() as ClassName).parameterizedBy(
            *typeArguments.map { it.javaToKotlinType() }.toTypedArray()
        )
    } else {
        val className =
            JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(FqName(toString()))
                ?.asSingleFqName()?.asString()
        return if (className == null) {
            this
        } else {
            ClassName.bestGuess(className)
        }
    }
}

/**
 *  Best-guess on nullability. If cannot be determined, then
 *  assumed nullable. This matches the KSP artifact where java "platform" types
 *  are considered nullable.
 */
fun Element.isNullable(): Boolean {
    // check annotation mirrors
    if (asType().kind.isPrimitive || annotationMirrors.any {
            it.annotationType.asTypeName()
                .shouldBeNonNull()
        }) {
        return false
    }
    return true
}