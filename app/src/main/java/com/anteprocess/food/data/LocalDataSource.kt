package com.anteprocess.food.data

import com.anteprocess.food.data.database.RecipesDao
import com.anteprocess.food.data.database.entities.FavoritesEntity
import com.anteprocess.food.data.database.entities.FoodJokeEntity
import com.anteprocess.food.data.database.entities.RecipesEntity
import com.anteprocess.food.models.FoodJoke
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val recipesDao: RecipesDao) {

    fun readRecipes() : Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    fun readFoodJoke() : Flow<List<FoodJokeEntity>> {
        return recipesDao.readFoodJoke()
    }

    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) {
        recipesDao.insertFoodJoke(foodJokeEntity)
    }

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }


}