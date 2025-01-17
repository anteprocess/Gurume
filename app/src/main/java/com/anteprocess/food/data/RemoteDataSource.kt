package com.anteprocess.food.data

import android.util.Log
import com.anteprocess.food.data.network.FoodRecipesApi
import com.anteprocess.food.models.FoodJoke
import com.anteprocess.food.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {
    //requests the data from the api
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        val resp = foodRecipesApi.getRecipes(queries)
        Log.d("TEST1", resp.body().toString())
       return resp
    }

    // Request Query search
    suspend fun searchRecipes(searchQuery: Map<String, String>) : Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

    suspend fun getFoodJoke(apiKey: String) : Response<FoodJoke> {
        return foodRecipesApi.getFoodJoke(apiKey)
    }


}