package com.anteprocess.food.todo.data.repository

import androidx.lifecycle.LiveData
import com.anteprocess.food.todo.data.db.ToDoDao
import com.anteprocess.food.todo.data.models.ToDoData


class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    // Sort the data
    val sortByHighPriority: LiveData<List<ToDoData>> = toDoDao.sortByHighPriority()
    val sortByLowPriority: LiveData<List<ToDoData>> = toDoDao.sortByLowPriority()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }

    // updating method
    suspend fun updateData(toDoData: ToDoData) {
        toDoDao.updateData(toDoData)
    }

    // delete method
    suspend fun deleteItem(toDoData: ToDoData) {
        toDoDao.deleteItem(toDoData)
    }

    suspend fun deleteAll() {
        toDoDao.deleteAll()
    }

    // Search data via title
    fun searchDatabase(searchQuery: String): LiveData<List<ToDoData>> {
        return toDoDao.searchDatabase(searchQuery)
    }
}