package com.anteprocess.food.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anteprocess.food.data.util.Constants.Companion.FAVORITE_RECIPES_TABLE
import com.anteprocess.food.models.Result

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)