package com.anteprocess.todo.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anteprocess.food.databinding.RowLayoutBinding
import com.anteprocess.food.todo.data.models.ToDoData


class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(toDoData: ToDoData) {
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }
        companion object {
            // returns the inflated layout
            fun from(parent: ViewGroup) : MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    var dataList = emptyList<ToDoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // binding everything
        val currentItem = dataList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun setData(toDoData: List<ToDoData>) {
        val toDoDiffUtil = ToDoDiffutil(dataList, toDoData)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = toDoData
        //notifyDataSetChanged()
        // We can use diff util to compare
        toDoDiffResult.dispatchUpdatesTo(this)
    }
}