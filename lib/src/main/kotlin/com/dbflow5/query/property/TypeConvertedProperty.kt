package com.dbflow5.query.property

import com.dbflow5.adapter.ModelAdapter
import com.dbflow5.adapter.SQLObjectAdapter
import com.dbflow5.converter.TypeConverter
import com.dbflow5.query.NameAlias
import com.dbflow5.query.Operator
import com.dbflow5.query.nameAlias
import kotlin.reflect.KClass

/**
 * Description: Provides convenience methods for [TypeConverter] when constructing queries.
 *
 * @author Andrew Grosner (fuzz)
 */

class TypeConvertedProperty<Data, Model> : Property<Model> {

    private val convertToDB: Boolean
    private val getter: TypeConverterGetter

    /**
     * returns a [Property] of its [Data] type without type conversion.
     */
    val dataProperty: Property<Data>

    override val adapter: SQLObjectAdapter<*>
        get() = super.adapter!!

    /**
     * Generated by the compiler, looks up the type converter based on [ModelAdapter] when needed.
     * This is so we can properly retrieve the type converter at any time.
     */
    fun interface TypeConverterGetter {

        fun getTypeConverter(modelClass: KClass<*>): TypeConverter<*, *>
    }

    override val operator: Operator<Model>
        get() = Operator.op(nameAlias, adapter, getter, convertToDB)

    constructor(
        adapter: SQLObjectAdapter<*>, nameAlias: NameAlias,
        convertToDB: Boolean,
        getter: TypeConverterGetter
    ) : super(adapter, nameAlias) {
        this.convertToDB = convertToDB
        this.getter = getter
        this.dataProperty = Property(adapter, nameAlias)
    }

    constructor(
        adapter: SQLObjectAdapter<*>,
        columnName: String,
        convertToDB: Boolean = true,
        getter: TypeConverterGetter
    ) : super(adapter, columnName.nameAlias) {
        this.convertToDB = convertToDB
        this.getter = getter
        this.dataProperty = Property(adapter, nameAlias)
    }

    override fun withTable(): TypeConvertedProperty<Data, Model> {
        val nameAlias = this.nameAlias
            .newBuilder()
            .withTable(adapter.name)
            .build()
        return TypeConvertedProperty(adapter, nameAlias, this.convertToDB, this.getter)
    }

    /**
     * This method would cache a [TypeConvertedProperty] of the inverted type, though would
     * keep its referenced type converter. This doesn't make much sense as the type converter
     * did not get used or as a type.
     */
    @Deprecated(
        replaceWith = ReplaceWith("dataProperty"),
        message = "invertProperty is now deprecated." +
            " use the accessor dataProperty instead or invert() for true inversion."
    )
    fun invertProperty(): Property<Data> = TypeConvertedProperty<Model, Data>(
        adapter, nameAlias,
        !convertToDB
    ) { modelClass -> getter.getTypeConverter(modelClass) }

    /**
     * Convenience operator used in library generated code.
     */
    @Suppress("UNCHECKED_CAST")
    fun <Data : Any, Model : Any> typeConverter(): TypeConverter<Data, Model> =
        getter.getTypeConverter(adapter.table) as TypeConverter<Data, Model>

    override fun withTable(tableNameAlias: NameAlias): TypeConvertedProperty<Data, Model> {
        val nameAlias = this.nameAlias
            .newBuilder()
            .withTable(tableNameAlias.tableName)
            .build()
        return TypeConvertedProperty(this.adapter, nameAlias, this.convertToDB, this.getter)
    }
}
