package com.anteprocess.food.todo.data.utils

import androidx.room.TypeConverter
import com.anteprocess.food.todo.data.models.Priority

// TypeConverter to convert custom object to primitive
class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }

}