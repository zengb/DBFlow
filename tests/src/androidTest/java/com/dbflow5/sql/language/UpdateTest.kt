package com.dbflow5.sql.language

import com.dbflow5.BaseUnitTest
import com.dbflow5.TestDatabase
import com.dbflow5.annotation.ConflictAction
import com.dbflow5.assertEquals
import com.dbflow5.config.database
import com.dbflow5.models.NumberModel_Table
import com.dbflow5.models.SimpleModel_Table
import com.dbflow5.query.property.Property
import com.dbflow5.query.set
import com.dbflow5.query.update
import org.junit.Test

class UpdateTest : BaseUnitTest() {

    private val simpleModelAdapter
        get() = database<TestDatabase>().simpleModelAdapter

    @Test
    fun validateUpdateRollback() {
        "UPDATE OR ROLLBACK `SimpleModel`".assertEquals(
            update(simpleModelAdapter).orRollback()
        )
    }

    @Test
    fun validateUpdateFail() {
        "UPDATE OR FAIL `SimpleModel`".assertEquals(
            update(simpleModelAdapter).orFail()
        )
    }

    @Test
    fun validateUpdateIgnore() {
        "UPDATE OR IGNORE `SimpleModel`".assertEquals(
            update(simpleModelAdapter).orIgnore()
        )
    }

    @Test
    fun validateUpdateReplace() {
        "UPDATE OR REPLACE `SimpleModel`".assertEquals(
            update(simpleModelAdapter).orReplace()
        )
    }

    @Test
    fun validateUpdateAbort() {
        "UPDATE OR ABORT `SimpleModel`".assertEquals(
            update(simpleModelAdapter).orAbort()
        )
    }

    @Test
    fun validateSetQuery() {
        "UPDATE `SimpleModel` SET `name`='name'".assertEquals(
            update(simpleModelAdapter) set (SimpleModel_Table.name eq "name")
        )
    }

    @Test
    fun validateWildcardQuery() {
        "UPDATE OR FAIL `NumberModel` SET `id`=? WHERE `id`=?".assertEquals(
            update(database<TestDatabase>().numberModelAdapter).or(ConflictAction.FAIL)
                .set(NumberModel_Table.id.eq(Property.WILDCARD))
                .where(NumberModel_Table.id.eq(Property.WILDCARD))
        )
    }
}