package com.anteprocess.food.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.anteprocess.food.data.DataStoreRepository
import com.anteprocess.food.data.util.Constants
import com.anteprocess.food.data.util.Constants.Companion.API_KEY
import com.anteprocess.food.data.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.anteprocess.food.data.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.anteprocess.food.data.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.anteprocess.food.data.util.Constants.Companion.QUERY_ADD_RECIPE_INFO
import com.anteprocess.food.data.util.Constants.Companion.QUERY_API_KEY
import com.anteprocess.food.data.util.Constants.Companion.QUERY_DIET
import com.anteprocess.food.data.util.Constants.Companion.QUERY_TYPE
import com.anteprocess.food.data.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.anteprocess.food.data.util.Constants.Companion.QUERY_NUMBER
import com.anteprocess.food.data.util.Constants.Companion.QUERY_SEARCH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var networkStatus = false
    var backOnline = false

    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    var readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(
                mealType,
                mealTypeId,
                dietType,
                dietTypeId
            )
        }
    }

    fun saveBackOnline(backOnline: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }

    //returns Hash-map queries
    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER //"50"
        queries[QUERY_API_KEY] = Constants.API_KEY
        queries[QUERY_TYPE] = mealType //"snack"
        queries[QUERY_DIET] = dietType //"vegan"
        queries[QUERY_ADD_RECIPE_INFO] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        queries["instructionsRequired"] = "true"
        return queries
    }

    fun applySearchQuery(searchQuery: String) : HashMap<String, String>{
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFO] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        queries["instructionsRequired"] = "true"
        return queries
    }


    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(),
                "No internet connection", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(),
                    "We are Back Online", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }

}