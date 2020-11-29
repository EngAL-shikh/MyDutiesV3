package com.amroz.myduties.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.amroz.myduties.Tasks


@Database(entities = [Tasks::class],version = 1, exportSchema = false)
@TypeConverters(TasksTypeConverters::class)
abstract class TasksDatabase:RoomDatabase() {

    abstract  fun TasksDao():Dao
}

val migration=object : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Tasks ADD COLUMN suspect TEXT NOT NULL DEFAULT ''")
    }
}