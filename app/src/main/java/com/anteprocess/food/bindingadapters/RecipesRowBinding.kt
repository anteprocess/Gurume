package com.anteprocess.food.bindingadapters

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import coil.load
import com.anteprocess.food.R
import com.anteprocess.food.models.Result
import com.anteprocess.food.ui.fragments.recipes.RecipesFragmentDirections
import org.jsoup.Jsoup
import java.lang.Exception

class RecipesRowBinding {

    companion object {

        // Add a onClick for the item
        @BindingAdapter("onRecipeClickListener")
        @JvmStatic
        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: Result) {
            Log.d("onRecipeClickListener", "CALLED")
            recipeRowLayout.setOnClickListener {
                try {
                    var test = result
                    val action = RecipesFragmentDirections.
                    actionRecipesFragmentToDetailsActivity(result)


                    it.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onRecipeClickListener", e.toString())
                }
            }
        }

        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                //add error placeholder
                error(R.drawable.ic_error_placeholder)
            }
        }

//        @BindingAdapter("setNumberOfLikes")
//        @JvmStatic
//        fun setNumberOfLikes(textView: TextView, likes: Int){
//            textView.text = likes.toString()
//        }
//
//        @BindingAdapter("setNumberOfMinutes")
//        @JvmStatic
//        fun setNumberOfMinutes(textView: TextView, minutes: Int){
//            textView.text = minutes.toString()
//        }

        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {
            if(vegan){
                when(view){
                    is TextView -> {
                        view.setTextColor(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    }
                    is ImageView -> {
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    }
                }
            } else {

            }
        }

        @BindingAdapter("parseHtml")
        @JvmStatic
        fun parseHtml(textView: TextView, description: String?){
            if(description != null) {
                val desc = Jsoup.parse(description).text()
                textView.text = desc
            }
        }

    }

}