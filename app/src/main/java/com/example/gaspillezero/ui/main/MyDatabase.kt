package com.example.gaspillezero.ui.main

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gaspillezero.ui.main.sourceDeDonnées.PanierItem

@Database(entities = [PanierItem::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun panierDAO(): PanierDAO

    companion object {
        private const val DATABASE_NAME = "gaspillage_alimentaire"

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) { }
        }

        private val MIGRATION_1_4: Migration = object : Migration(1, 4) {
            override fun migrate(database: SupportSQLiteDatabase) { }
        }

        private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) { }
        }

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context, resetDatabase: Boolean): AppDatabase {
            if (resetDatabase && INSTANCE == null) {
                context.getDatabasePath(DATABASE_NAME).delete()
            }

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_3_4, MIGRATION_1_4)
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

