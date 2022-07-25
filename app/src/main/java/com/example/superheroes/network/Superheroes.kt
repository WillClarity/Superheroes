package com.example.superheroes.network

import com.squareup.moshi.Json

/**
 * This data class defines a Superhero which includes an ID, name, image URL, and wins and losses.
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
data class Superheroes(
    @Json(name = "_id") val id: String,
    val name: String,
    @Json(name = "image") val imgSrcUrl: String,
    val willWinAgainst: MutableList<String>,
    val willLoseAgainst: MutableList<String>

)