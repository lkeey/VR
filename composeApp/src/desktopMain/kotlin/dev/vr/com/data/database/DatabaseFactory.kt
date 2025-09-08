package dev.vr.com.data.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.vr.com.db.VRDatabase

object DatabaseFactory {

    fun createDatabase(): VRDatabase {
        // 1. Create a SQLite driver (file-based)
        val driver = JdbcSqliteDriver("jdbc:sqlite:vrgames.db")

        // 2. Create the database schema if it doesn't exist
        VRDatabase.Schema.create(driver)

        // 3. Return the database instance
        return VRDatabase(driver)
    }
}
