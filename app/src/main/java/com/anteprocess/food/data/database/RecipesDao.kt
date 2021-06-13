package com.anteprocess.food.data.database

import androidx.room.*
import com.anteprocess.food.data.database.entities.FavoritesEntity
import com.anteprocess.food.data.database.entities.FoodJokeEntity
import com.anteprocess.food.data.database.entities.RecipesEntity
import com.anteprocess.food.models.FoodJoke
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    //replace the old data if we get the new one. Will have the newest entities
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity)

    @Query("SELECT * FROM food_joke_table ORDER BY id ASC")
    fun readFoodJoke(): Flow<List<FoodJokeEntity>>

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes(): Flow<List<RecipesEntity>>


    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()


}