@file:Suppress("unused")

package com.dbflow5.query.property

import com.dbflow5.database.FlowCursor

@JvmName("getNullable")
inline fun Property<String?>.infer(
    cursor: FlowCursor,
    defValue: String? = null
): String? =
    defValue?.let {
        cursor.getStringOrDefault(
            nameAlias.nameRaw(),
            it
        )
    } ?: cursor.getStringOrDefault(nameAlias.nameRaw())

@JvmName("getNullable")
inline fun Property<String?>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: String? = null
): String? =
    defValue?.let {
        cursor.getStringOrDefault(
            index,
            it
        )
    } ?: cursor.getStringOrDefault(index)

inline fun Property<String>.infer(
    cursor: FlowCursor,
    defValue: String = ""
): String =
    cursor.getStringOrDefault(nameAlias.nameRaw(), defValue)

inline fun Property<String>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: String = ""
): String =
    cursor.getStringOrDefault(index, defValue)


@JvmName("getNullable")
inline fun Property<Boolean?>.infer(
    cursor: FlowCursor,
    defValue: Boolean? = null
): Boolean? =
    cursor.getBooleanOrDefault(
        nameAlias.nameRaw(),
        defValue
    )

@JvmName("getNullable")
inline fun Property<Boolean?>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Boolean? = null
): Boolean? =
    cursor.getBooleanOrDefault(
        index,
        defValue
    )

inline fun Property<Boolean>.infer(
    cursor: FlowCursor,
    defValue: Boolean = false
): Boolean =
    cursor.getBooleanOrDefault(nameAlias.nameRaw(), defValue)

inline fun Property<Boolean>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Boolean = false
): Boolean =
    cursor.getBooleanOrDefault(index, defValue)

@JvmName("getNullable")
inline fun Property<Int?>.infer(
    cursor: FlowCursor,
    defValue: Int? = null
): Int? =
    cursor.getIntOrDefault(nameAlias.nameRaw(), defValue)

@JvmName("getNullable")
inline fun Property<Int?>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Int? = null
): Int? =
    cursor.getIntOrDefault(index, defValue)

inline fun Property<Int>.infer(
    cursor: FlowCursor,
    defValue: Int = 0
): Int =
    cursor.getIntOrDefault(nameAlias.nameRaw(), defValue)

inline fun Property<Int>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Int = 0
): Int =
    cursor.getIntOrDefault(index, defValue)

@JvmName("getNullable")
inline fun Property<Double?>.infer(
    cursor: FlowCursor,
    defValue: Double? = null
): Double? =
    cursor.getDoubleOrDefault(nameAlias.nameRaw(), defValue)

@JvmName("getNullable")
inline fun Property<Double?>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Double? = null
): Double? =
    cursor.getDoubleOrDefault(index, defValue)

inline fun Property<Double>.infer(
    cursor: FlowCursor,
    defValue: Double = 0.0
): Double =
    cursor.getDoubleOrDefault(nameAlias.nameRaw(), defValue)

inline fun Property<Double>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Double = 0.0
): Double =
    cursor.getDoubleOrDefault(index, defValue)

@JvmName("getNullable")
inline fun Property<Float?>.infer(
    cursor: FlowCursor,
    defValue: Float? = null
): Float? =
    cursor.getFloatOrDefault(nameAlias.nameRaw(), defValue)

@JvmName("getNullable")
inline fun Property<Float?>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Float? = null
): Float? =
    cursor.getFloatOrDefault(index, defValue)

inline fun Property<Float>.infer(
    cursor: FlowCursor,
    defValue: Float = 0f
): Float =
    cursor.getFloatOrDefault(nameAlias.nameRaw(), defValue)

inline fun Property<Float>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Float = 0f
): Float =
    cursor.getFloatOrDefault(index, defValue)

@JvmName("getNullable")
inline fun Property<Long?>.infer(
    cursor: FlowCursor,
    defValue: Long? = null
): Long? =
    cursor.getLongOrDefault(nameAlias.nameRaw(), defValue)

@JvmName("getNullable")
inline fun Property<Long?>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Long? = null
): Long? =
    cursor.getLongOrDefault(index, defValue)

inline fun Property<Long>.infer(
    cursor: FlowCursor,
    defValue: Long = 0L
): Long =
    cursor.getLongOrDefault(nameAlias.nameRaw(), defValue)

inline fun Property<Long>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Long = 0L
): Long =
    cursor.getLongOrDefault(index, defValue)

@JvmName("getNullable")
inline fun Property<Short?>.infer(
    cursor: FlowCursor,
    defValue: Short? = null
): Short? =
    cursor.getShortOrDefault(nameAlias.nameRaw(), defValue)

@JvmName("getNullable")
inline fun Property<Short?>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Short? = null
): Short? =
    cursor.getShortOrDefault(index, defValue)

inline fun Property<Short>.infer(
    cursor: FlowCursor,
    defValue: Short = 0
): Short =
    cursor.getShortOrDefault(nameAlias.nameRaw(), defValue)

inline fun Property<Short>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: Short = 0
): Short =
    cursor.getShortOrDefault(index, defValue)

@JvmName("getNullable")
inline fun Property<ByteArray?>.infer(
    cursor: FlowCursor,
    defValue: ByteArray? = null
): ByteArray? =
    cursor.getBlobOrDefault(nameAlias.nameRaw(), defValue)

@JvmName("getNullable")
inline fun Property<ByteArray?>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: ByteArray? = null
): ByteArray? =
    cursor.getBlobOrDefault(index, defValue)

inline fun Property<ByteArray>.infer(
    cursor: FlowCursor,
    defValue: ByteArray = byteArrayOf()
): ByteArray =
    cursor.getBlobOrDefault(nameAlias.nameRaw(), defValue)

inline fun Property<ByteArray>.infer(
    cursor: FlowCursor,
    index: Int,
    defValue: ByteArray = byteArrayOf()
): ByteArray =
    cursor.getBlobOrDefault(index, defValue)

@Suppress("unused")
inline fun <Data : Any, Model : Any> TypeConvertedProperty<Data, Model>.infer(
    getData: TypeConvertedProperty<Data, Model>.() -> Data
): Model =
    typeConverter<Data, Model>().getModelValue(getData())

@JvmName("inferNullable")
@Suppress("unused")
inline fun <Data : Any, Model : Any> TypeConvertedProperty<Data, Model?>.infer(
    getData: TypeConvertedProperty<Data, Model?>.() -> Data?
): Model? =
    getData()?.let { typeConverter<Data, Model>().getModelValue(it) }

@JvmName("inferNullableData")
@Suppress("unused")
inline fun <Data : Any, Model : Any> TypeConvertedProperty<Data?, Model>.infer(
    getData: TypeConvertedProperty<Data?, Model>.() -> Data
): Model = typeConverter<Data, Model>().getModelValue(getData())

@JvmName("inferNullableDataModel")
@Suppress("unused")
inline fun <Data : Any, Model : Any> TypeConvertedProperty<Data?, Model?>.infer(
    getData: TypeConvertedProperty<Data?, Model?>.() -> Data?
): Model? =
    getData()?.let { typeConverter<Data, Model>().getModelValue(it) }

@JvmName("inferNullable")
inline fun <E : Enum<*>?> Property<E>.infer(
    cursor: FlowCursor,
    enumValueOf: (value: String) -> E
): E? =
    cursor.getStringOrDefault(nameAlias.nameRaw())?.let(enumValueOf)

@JvmName("inferNullable")
inline fun <E : Enum<*>?> Property<E>.infer(
    cursor: FlowCursor,
    index: Int,
    enumValueOf: (value: String) -> E
): E? =
    cursor.getStringOrDefault(index)?.let(enumValueOf)

inline fun <E : Enum<*>> Property<E>.infer(
    cursor: FlowCursor,
    enumValueOf: (value: String) -> E
): E =
    enumValueOf(cursor.getStringOrDefault(nameAlias.nameRaw(), ""))

inline fun <E : Enum<*>> Property<E>.infer(
    cursor: FlowCursor,
    index: Int,
    enumValueOf: (value: String) -> E
): E =
    enumValueOf(cursor.getStringOrDefault(index, ""))
