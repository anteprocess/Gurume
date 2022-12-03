package com.anteprocess.food.todo.data.viewmodel

import android.app.Application
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.anteprocess.food.R
import com.anteprocess.food.todo.data.models.Priority
import com.anteprocess.food.todo.data.models.ToDoData

class SharedViewModel(application: Application): AndroidViewModel(application) {


    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(false)

    fun checkIfdatabaseEmpty(toDoData: List<ToDoData>) {
        emptyDatabase.value = toDoData.isEmpty()
    }

    // This is a listener for the spinner to change the color of the text
    val listener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            when(position) {
                // High priority
                0 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.red))
                }
                1 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.yellow))
                }
                // Low priority
                2 -> {
                    (parent?.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(application, R.color.green))
                }
                else ->  {

                }
            }
        }
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
    }

    fun verifyDataFromUser(title: String, description: String) : Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }

    fun parsePriority(priority: String) : Priority {
        return when (priority) {
            "High Priority" -> {
                Priority.HIGH}
            "Medium Priority" -> {
                Priority.MEDIUM}
            "Low Priority" -> {
                Priority.LOW}
            else -> Priority.LOW
        }
    }
}