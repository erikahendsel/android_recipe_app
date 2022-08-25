package com.erikahendsel.recipeapp.Model

import org.json.JSONArray

data class RecipeModelClass(
    val id: String,
    val title: String,
    val image: Int,
    val description: String,
    val ingredients: List<String>,
    val cookingSteps: List<String>,
    val tags: List<String>,
    val time: String
)