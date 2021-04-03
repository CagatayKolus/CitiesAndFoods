package com.cagataykolus.citiesandfoods.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cagataykolus.citiesandfoods.model.City
import com.cagataykolus.citiesandfoods.model.Food

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * Each Migration has a start and end versions and Room runs these migrations to bring the
 * database to the latest version. The migration object that can modify the database and
 * to the necessary changes.
 */
object MigrationDatabase {
    const val DB_VERSION = 2

    val MIGRATION_CITY: Array<Migration>
        get() = arrayOf(
            migrationCity()
        )

    val MIGRATION_FOOD: Array<Migration>
        get() = arrayOf(
            migrationFood()
        )

    /**
     *  Creates a new migration between version 1 and 2 for [Food.TABLE_FOOD] table.
     */
    private fun migrationCity(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${Food.TABLE_FOOD} ADD COLUMN body TEXT")
        }
    }

    /**
     *  Creates a new migration between version 1 and 2 for [City.TABLE_CITY] table.
     */
    private fun migrationFood(): Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${City.TABLE_CITY} ADD COLUMN body TEXT")
        }
    }
}
