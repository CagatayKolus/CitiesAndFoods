package com.cagataykolus.citiesandfoods.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cagataykolus.citiesandfoods.data.local.dao.FoodDao
import com.cagataykolus.citiesandfoods.model.Food

/**
 * Created by Çağatay Kölüş on 2.04.2021.
 * cagataykolus@gmail.com
 */

/**
 * FoodDatabase provides DAO [FoodDao] by using method [getFoodDao].
 */
@Database(
    entities = [Food::class],
    version = MigrationDatabase.DB_VERSION
)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun getFoodDao(): FoodDao

    companion object {
        private const val DB_NAME = "database_food"

        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getInstance(context: Context): FoodDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    DB_NAME
                ).addMigrations(*MigrationDatabase.MIGRATION_FOOD).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
