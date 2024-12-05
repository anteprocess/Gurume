package com.anteprocess.food

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.anteprocess.food.todo.data.db.ToDoDao
import com.anteprocess.food.todo.data.db.ToDoDatabase
import com.anteprocess.food.todo.data.models.Priority
import com.anteprocess.food.todo.data.models.ToDoData
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class ToDoDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: ToDoDao
    private lateinit var database: ToDoDatabase

    @Before
    fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, ToDoDatabase::class.java
        ).allowMainThreadQueries()
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
        dao = database.toDoDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveTodoTest() = runBlocking {

        val toDoData1 = ToDoData(
            id = 0,
            title = "Test",
            priority = Priority.LOW,
            description = "Test Description 1"
        )

        val toDoData = dao.getAllData().value?.get(0)?.title
        // Check null
        Truth.assertThat(toDoData).isEqualTo(null)

        dao.insertData(toDoData1)

        // Check inserted value
        Truth.assertThat(toDoData).isEqualTo(toDoData1.title)

    }
}