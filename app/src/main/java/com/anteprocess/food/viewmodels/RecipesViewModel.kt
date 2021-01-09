package com.anteprocess.food.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.anteprocess.food.data.util.Constants
import com.anteprocess.food.data.util.Constants.Companion.QUERY_ADD_RECIPE_INFO
import com.anteprocess.food.data.util.Constants.Companion.QUERY_API_KEY
import com.anteprocess.food.data.util.Constants.Companion.QUERY_DIET
import com.anteprocess.food.data.util.Constants.Companion.QUERY_TYPE
import com.anteprocess.food.data.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.anteprocess.food.data.util.Constants.Companion.QUERY_NUMBER

class RecipesViewModel(application: Application): AndroidViewModel(application) {

    //returns Hashmap queries
    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        //queries[QUERY_NUMBER] = "50"
        queries[QUERY_API_KEY] = Constants.API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFO] = "true"
        //queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

}