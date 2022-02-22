package com.dbflow5.sqlcipher

import com.dbflow5.adapter2.ModelAdapter
import com.dbflow5.annotation.Database
import com.dbflow5.config.DBFlowDatabase

@Database(
    version = 1,
    tables = [
        CipherModel::class,
    ]
)
abstract class CipherDatabase : DBFlowDatabase() {

    abstract val cipherAdapter: ModelAdapter<CipherModel>
}
