package com.dbflow5.sql.language

import com.dbflow5.BaseUnitTest
import com.dbflow5.TestDatabase
import com.dbflow5.annotation.ConflictAction
import com.dbflow5.assertEquals
import com.dbflow5.config.database
import com.dbflow5.config.writableTransaction
import com.dbflow5.models.NumberModel_Table
import com.dbflow5.models.SimpleModel_Table
import com.dbflow5.query.property.Property
import com.dbflow5.query2.orAbort
import com.dbflow5.query2.orFail
import com.dbflow5.query2.orIgnore
import com.dbflow5.query2.orReplace
import com.dbflow5.query2.orRollback
import com.dbflow5.query2.update
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.test.fail

class UpdateTest : BaseUnitTest() {

    private val simpleModelAdapter
        get() = database<TestDatabase>().simpleModelAdapter

    @Test
    fun validateUpdateRollback() {
        "UPDATE OR ROLLBACK `SimpleModel`".assertEquals(
            simpleModelAdapter.update().orRollback()
        )
    }

    @Test
    fun validateUpdateFail() {
        "UPDATE OR FAIL `SimpleModel`".assertEquals(
            simpleModelAdapter.update().orFail()
        )
    }

    @Test
    fun validateUpdateIgnore() {
        "UPDATE OR IGNORE `SimpleModel`".assertEquals(
            simpleModelAdapter.update().orIgnore()
        )
    }

    @Test
    fun validateUpdateReplace() {
        "UPDATE OR REPLACE `SimpleModel`".assertEquals(
            simpleModelAdapter.update().orReplace()
        )
    }

    @Test
    fun validateUpdateAbort() {
        "UPDATE OR ABORT `SimpleModel`".assertEquals(
            simpleModelAdapter.update().orAbort()
        )
    }

    @Test
    fun validateSetQuery() {
        "UPDATE `SimpleModel` SET `name`='name'".assertEquals(
            simpleModelAdapter.update() set (SimpleModel_Table.name eq "name")
        )
    }

    @Test
    fun validateWildcardQuery() {
        "UPDATE OR FAIL `NumberModel` SET `id`=? WHERE `id`=?".assertEquals(
            database<TestDatabase>().numberModelAdapter.update().or(ConflictAction.FAIL)
                .set(NumberModel_Table.id.eq(Property.WILDCARD))
                .where(NumberModel_Table.id.eq(Property.WILDCARD))
        )
    }

    @Test
    fun validateNonSelectThrowError() = runBlockingTest {
        database<TestDatabase>().writableTransaction {
            try {
                //simpleModelAdapter.update().set(SimpleModel_Table.name.`is`("name")).querySingle()
                fail("Non select passed")
            } catch (i: IllegalArgumentException) {
                // expected
            }

            try {
                //simpleModelAdapter.update().set(SimpleModel_Table.name.`is`("name")).querySingle()
                fail("Non select passed")
            } catch (i: IllegalArgumentException) {
                // expected
            }
        }
    }
}