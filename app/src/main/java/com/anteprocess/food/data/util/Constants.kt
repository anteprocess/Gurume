package com.anteprocess.food.data.util

open class Constants {
    companion object {
        const val API_KEY = "4b97c2996f9f403a96fd7cc30948417c"
        const val BASE_URL = "https://api.spoonacular.com"
        const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

        const val RECIPE_RESULT = "recipeBundle"
        // API Query Keys
        const val QUERY_SEARCH = "query"
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFO = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        //Room Database
        const val DATABASE_NAME = "recipes_database"
        const val RECIPES_TABLE = "recipes_table"
        const val FAVORITE_RECIPES_TABLE = "favorite_recipes_table"
        const val FOOD_JOKE_TABLE = "food_joke_table"

        //Bottom-sheet preferences
        const val DEFAULT_RECIPES_NUMBER = "30"
        const val DEFAULT_MEAL_TYPE= "main course"
        const val DEFAULT_DIET_TYPE = "gluten free"

        // key const for the data store
        const val PREFERENCES_MEAL_TYPE = "mealType"
        const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
        const val PREFERENCES_DIET_TYPE = "dietType"
        const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"

        const val PREFERENCES_NAME = "foody_preferences"

        const val PREFERENCES_BACK_ONLINE = "backonline"

    }
}