package com.anteprocess.food.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anteprocess.food.data.database.entities.FavoritesEntity
import com.anteprocess.food.data.database.entities.FoodJokeEntity
import com.anteprocess.food.data.database.entities.RecipesEntity

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class, FoodJokeEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase : RoomDatabase(){
    abstract fun recipesDao() : RecipesDao
}