package com.anteprocess.food.todo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.anteprocess.food.todo.data.models.ToDoData


@Dao
interface ToDoDao {
    // Get the list of data in the livedata

    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData() : LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
    suspend fun deleteItem(toDoData: ToDoData)

    // Custom query to delete all data

    @Query("DELETE FROM todo_table")
    suspend fun deleteAll()

    // Search query
    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>>


    //sorting

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'L%' THEN 3 END ")
    fun sortByHighPriority() : LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END ")
    fun sortByLowPriority() : LiveData<List<ToDoData>>

}