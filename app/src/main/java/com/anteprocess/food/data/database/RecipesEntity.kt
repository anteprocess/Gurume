package com.anteprocess.food.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anteprocess.food.data.util.Constants.Companion.RECIPES_TABLE
import com.anteprocess.food.models.FoodRecipe

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(foodRecipe: FoodRecipe) {
    //whenever we fetch the data from the api we will replace it when we fetch the newest data
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}