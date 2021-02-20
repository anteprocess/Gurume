package com.anteprocess.food.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Result(
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int? = 0,
    @SerializedName("cheap")
    val cheap: Boolean? = false,
    @SerializedName("dairyFree")
    val dairyFree: Boolean? = false,
    @SerializedName("extendedIngredients")
    val extendedIngredients: @RawValue List<ExtendedIngredient>,
    @SerializedName("ingredients")
    val ingredients: String?,
    @SerializedName("glutenFree")
    val glutenFree: Boolean? = false,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int? = 0,
    @SerializedName("sourceName")
    val sourceName: String?,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("summary")
    val summary: String? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("vegan")
    val vegan: Boolean? = null,
    @SerializedName("vegetarian")
    val vegetarian: Boolean? = false,
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean? = false,
): Parcelable