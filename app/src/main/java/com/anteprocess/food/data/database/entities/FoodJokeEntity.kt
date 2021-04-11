package com.anteprocess.food.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anteprocess.food.data.util.Constants.Companion.FOOD_JOKE_TABLE
import com.anteprocess.food.models.FoodJoke

@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJoke: FoodJoke
) {

    @PrimaryKey(autoGenerate = false)
    var id:Int = 0

}