package com.anteprocess.food.todo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anteprocess.food.todo.data.models.ToDoData
import com.anteprocess.food.todo.data.utils.Converter

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null


        fun getDatabase(context: Context) :ToDoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            // we don't want multiple thread create multiple instance. Only one thread to create this
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}