package com.anteprocess.food.adapters

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anteprocess.food.R
import com.anteprocess.food.data.database.entities.FavoritesEntity
import com.anteprocess.food.data.util.RecipesDiffUtil
import com.anteprocess.food.databinding.FavoriteRecipesRowLayoutBinding
import com.anteprocess.food.ui.fragments.favorites.FavoriteRecipesFragmentDirections
import com.anteprocess.food.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.favorite_recipes_row_layout.view.*

class FavoriteRecipesAdapter(
    private val requireActivity: FragmentActivity,
    private val mainViewModel: MainViewModel
    ): RecyclerView.Adapter<FavoriteRecipesAdapter.MyViewholder>(), ActionMode.Callback {

    private var multiSelection = false
    private var selectedRecipes = arrayListOf<FavoritesEntity>()
    private var myViewHolders = arrayListOf<MyViewholder>()

    private lateinit var mActionMode: ActionMode
    private lateinit var rootView: View

    private var favoriteRecipes = emptyList<FavoritesEntity>()
    class MyViewholder(
        private val binding: FavoriteRecipesRowLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(favoritesEntity: FavoritesEntity) {
                binding.favoritesEntity = favoritesEntity
                binding.executePendingBindings()
            }

        companion object {
            // binding the layout to the recycler view
            fun from(parent: ViewGroup) : MyViewholder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewholder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        return MyViewholder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        myViewHolders.add(holder)
        rootView = holder.itemView.rootView

        val currentRecipe = favoriteRecipes[position]
        holder.bind(currentRecipe)

        // Single click listener
        // transition from favorite to details
        holder.itemView.favoriteRecipesRowLayout.setOnClickListener {
            if (multiSelection) {
                applySelection(holder, currentRecipe)
            } else {
                val action =
                    FavoriteRecipesFragmentDirections.
                    actionFavoriteRecipesFragmentToDetailsActivity(currentRecipe.result)
                holder.itemView.findNavController().navigate(action)
            }

        }
        // Long click listener
        holder.itemView.favoriteRecipesRowLayout.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                requireActivity.startActionMode(this)
                applySelection(holder, currentRecipe)
                true
            } else {
                multiSelection = false
                false
            }
        }
    }

    private fun applyActionModeTitle() {
        when(selectedRecipes.size) {
            0 -> {
                mActionMode.finish()
            }
            1 -> {
                //when selecting one item
                mActionMode.title = "${selectedRecipes.size} item selected"
            } 
            else -> {
                mActionMode.title = "${selectedRecipes.size} items selected"
            }
        }
    }

    override fun getItemCount(): Int {
        return favoriteRecipes.size
    }

    // Action callback
    override fun onCreateActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
        actionMode?.menuInflater?.inflate(R.menu.favorites_contexual_menu, menu)
        mActionMode = actionMode!!
        //when showing
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(actionMode: ActionMode?, menu: Menu?): Boolean {
       return true
    }

    override fun onActionItemClicked(actionMode: ActionMode?, menu: MenuItem?): Boolean {
        if (menu?.itemId == R.id.delete_favorite_recipe_menu) {
            selectedRecipes.forEach { mainViewModel.deleteFavoriteRecipe(it) }
            showSnackbar("${selectedRecipes.size} Recipe/s removed")
            multiSelection = false
            selectedRecipes.clear()
            actionMode?.finish()
        }
        return true
    }

    override fun onDestroyActionMode(actionMode: ActionMode?) {
        myViewHolders.forEach { holder ->
            changeRecipeStyle(holder, R.color.cardbackgroundColor, R.color.strokeColor)
        }
        multiSelection = false
        selectedRecipes.clear()
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color: Int) {
        requireActivity.window.statusBarColor =
            ContextCompat.getColor(requireActivity, color)
    }

    private fun applySelection(holder: MyViewholder,
    currentRecipe: FavoritesEntity) {
        if (selectedRecipes.contains(currentRecipe)) {
            selectedRecipes.remove(currentRecipe)
            changeRecipeStyle(holder, R.color.cardbackgroundColor, R.color.strokeColor)
            applyActionModeTitle()
        } else {
            selectedRecipes.add(currentRecipe)
            changeRecipeStyle(holder, R.color.cardbackgroundLightColor, R.color.colorPrimary)
            applyActionModeTitle()
        }
    }

    private fun changeRecipeStyle(holder: MyViewholder, backgroundColor: Int,
    strokeColor: Int) {
        holder.itemView.favoriteRecipesRowLayout.setBackgroundColor(
            ContextCompat.getColor(requireActivity, backgroundColor)
        )
        holder.itemView.favorite_row_cardView.strokeColor =
            ContextCompat.getColor(requireActivity, strokeColor)
    }

    fun setData(newFavoriteRecipes: List<FavoritesEntity>) {
        val favoriteRecipesDiffUtil =
            RecipesDiffUtil(favoriteRecipes, newFavoriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).setAction("Okay"){}.show()
    }

    //close the contexual
    fun clearContexualActionMoe() {
        if(this::mActionMode.isInitialized) {
            mActionMode.finish()
        }
    }
}