package com.anteprocess.todo.fragments.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.anteprocess.food.todo.data.models.ToDoData


class ToDoDiffutil(
    private val oldList: List<ToDoData>,
    private val newList: List<ToDoData>
    ): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
       return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].description == newList[newItemPosition].description
    }

}