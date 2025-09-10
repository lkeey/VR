package dev.vr.com.data.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.vr.com.db.VRDatabase
import java.io.File

object DatabaseFactory {

    fun createDatabase(
        path: String? = null
    ): VRDatabase {
        val dbFile = File(
            path ?: File(
                System.getProperty("user.home"),
                "vr.db"
            ).absolutePath
        )

        /* SQLite driver (file-based) */
        val driver = JdbcSqliteDriver(
            // "jdbc:sqlite:vrgames.db"
            "jdbc:sqlite:${dbFile.absolutePath}"
        )

        if (!dbFile.exists()) {
            VRDatabase.Schema.create(driver)
        }

        return VRDatabase(driver)
    }
}
