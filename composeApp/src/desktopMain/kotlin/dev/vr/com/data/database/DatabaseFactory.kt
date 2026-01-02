package dev.vr.com.data.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.vr.com.db.VRDatabase
import java.io.File
import java.nio.ByteBuffer
import java.sql.Connection
import java.sql.DriverManager

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

        val dbExists = dbFile.exists()

        /* SQLite driver (file-based) */
        val driver = JdbcSqliteDriver(
            // "jdbc:sqlite:vrgames.db"
            "jdbc:sqlite:${dbFile.absolutePath}"
        )

        if (!dbExists) {
            // Create new database with current schema
            VRDatabase.Schema.create(driver)
        } else {
            // Check if migration is needed
            migrateIfNeeded(dbFile.absolutePath, driver)
        }

        return VRDatabase(driver)
    }

    private fun migrateIfNeeded(dbPath: String, driver: JdbcSqliteDriver) {
        val connection: Connection = DriverManager.getConnection("jdbc:sqlite:$dbPath")
        
        try {
            // Check if game table exists
            val tableCheckResult = connection.createStatement().executeQuery(
                "SELECT name FROM sqlite_master WHERE type='table' AND name='game'"
            )
            val tableExists = tableCheckResult.next()
            tableCheckResult.close()
            
            if (!tableExists) {
                // Table doesn't exist at all - create fresh schema
                println("Game table doesn't exist. Creating fresh schema...")
                VRDatabase.Schema.create(driver)
                return
            }
            
            // Check if 'image' column exists (old schema)
            val resultSet = connection.createStatement().executeQuery(
                "PRAGMA table_info(game)"
            )
            
            var hasOldImageColumn = false
            var hasNewImagesColumn = false
            var columnCount = 0
            
            while (resultSet.next()) {
                columnCount++
                val columnName = resultSet.getString("name")
                if (columnName == "image") {
                    hasOldImageColumn = true
                }
                if (columnName == "images") {
                    hasNewImagesColumn = true
                }
            }
            resultSet.close()
            
            // If table has no columns or wrong schema, recreate it
            if (columnCount == 0) {
                println("Game table is empty/corrupted. Recreating schema...")
                connection.createStatement().execute("DROP TABLE IF EXISTS game")
                VRDatabase.Schema.create(driver)
                return
            }
            
            // If we have old schema, migrate it
            if (hasOldImageColumn && !hasNewImagesColumn) {
                println("Migrating database from single image to multiple images format...")
                migrateFromSingleToMultipleImages(connection)
                println("Migration completed successfully!")
            } else if (!hasNewImagesColumn) {
                // Schema is corrupted, recreate
                println("Game table schema is corrupted. Recreating...")
                connection.createStatement().execute("DROP TABLE IF EXISTS game")
                VRDatabase.Schema.create(driver)
            }
        } catch (e: Exception) {
            println("Error during migration check: ${e.message}")
            e.printStackTrace()
            // If anything fails, try to recreate the schema
            try {
                connection.createStatement().execute("DROP TABLE IF EXISTS game")
                VRDatabase.Schema.create(driver)
                println("Schema recreated after error.")
            } catch (recreateError: Exception) {
                println("Failed to recreate schema: ${recreateError.message}")
                throw recreateError
            }
        } finally {
            connection.close()
        }
    }

    private fun migrateFromSingleToMultipleImages(connection: Connection) {
        connection.autoCommit = false
        
        try {
            // Read all existing games
            val games = mutableListOf<GameData>()
            val selectStatement = connection.createStatement()
            val resultSet = selectStatement.executeQuery("SELECT * FROM game")
            
            while (resultSet.next()) {
                games.add(
                    GameData(
                        id = resultSet.getLong("id"),
                        name = resultSet.getString("name"),
                        description = resultSet.getString("description"),
                        imageBlob = resultSet.getBytes("image"),
                        videoPath = resultSet.getString("video_path"),
                        category = resultSet.getString("category")
                    )
                )
            }
            resultSet.close()
            selectStatement.close()
            
            // Create new table with updated schema
            val statement = connection.createStatement()
            statement.execute("""
                CREATE TABLE game_new (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    description TEXT,
                    images BLOB NOT NULL,
                    video_path TEXT,
                    category TEXT NOT NULL
                )
            """)
            statement.close()
            
            // Insert migrated data
            val insertStatement = connection.prepareStatement("""
                INSERT INTO game_new (id, name, description, images, video_path, category)
                VALUES (?, ?, ?, ?, ?, ?)
            """)
            
            games.forEach { game ->
                // Convert single image to multi-image format
                val serializedImages = wrapSingleImageInMultiFormat(game.imageBlob)
                
                insertStatement.setLong(1, game.id)
                insertStatement.setString(2, game.name)
                insertStatement.setString(3, game.description)
                insertStatement.setBytes(4, serializedImages)
                insertStatement.setString(5, game.videoPath)
                insertStatement.setString(6, game.category)
                insertStatement.executeUpdate()
            }
            insertStatement.close()
            
            // Drop old table and rename new one
            val renameStatement = connection.createStatement()
            renameStatement.execute("DROP TABLE game")
            renameStatement.execute("ALTER TABLE game_new RENAME TO game")
            renameStatement.close()
            
            connection.commit()
        } catch (e: Exception) {
            connection.rollback()
            println("Migration failed: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }

    private fun wrapSingleImageInMultiFormat(imageBytes: ByteArray): ByteArray {
        // Format: [4 bytes: count][4 bytes: length][bytes]
        val buffer = ByteBuffer.allocate(4 + 4 + imageBytes.size)
        buffer.putInt(1) // count = 1
        buffer.putInt(imageBytes.size) // length of image
        buffer.put(imageBytes) // image data
        return buffer.array()
    }

    private data class GameData(
        val id: Long,
        val name: String,
        val description: String?,
        val imageBlob: ByteArray,
        val videoPath: String?,
        val category: String
    )
}
