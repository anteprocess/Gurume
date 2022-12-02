package com.anteprocess.food.todo.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title: String,
    // We need to convert this with TypeConverter
    var priority: Priority,
    var description: String
) : Parcelable