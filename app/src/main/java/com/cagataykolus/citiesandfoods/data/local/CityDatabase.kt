package com.cagataykolus.citiesandfoods.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cagataykolus.citiesandfoods.data.local.dao.CityDao
import com.cagataykolus.citiesandfoods.model.City

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * CityDatabase provides DAO [CityDao] by using method [getCityDao].
 */
@Database(
    entities = [City::class],
    version = MigrationDatabase.DB_VERSION
)
abstract class CityDatabase : RoomDatabase() {

    abstract fun getCityDao(): CityDao

    companion object {
        private const val DB_NAME = "database_city"

        @Volatile
        private var INSTANCE: CityDatabase? = null

        fun getInstance(context: Context): CityDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CityDatabase::class.java,
                    DB_NAME
                ).addMigrations(*MigrationDatabase.MIGRATION_CITY).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
