package com.anteprocess.food.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anteprocess.food.data.util.RecipesDiffUtil
import com.anteprocess.food.databinding.RecipesRowLayoutBinding
import com.anteprocess.food.models.FoodRecipe
import com.anteprocess.food.models.Result

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {
    private var recipes = emptyList<Result>()
    class MyViewHolder(private val binding: RecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(result: Result) {
                binding.result = result
                //updates the layout when there are changes
                binding.executePendingBindings()
            }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapter.MyViewHolder {
       return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecipesAdapter.MyViewHolder, position: Int) {
       val currentRecipe = recipes[position]
        holder.bind(currentRecipe) // needs to call the bind from
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    //set the data of the recipes
    fun setData(newData: FoodRecipe) {
        Log.d("Test", newData.results.get(0).vegan.toString())
        val recipesDiffUtil = RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}