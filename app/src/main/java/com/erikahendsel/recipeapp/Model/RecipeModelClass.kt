package com.erikahendsel.recipeapp.Model

data class RecipeModelClass(
    val id: String,
    val title: String,
    val image: Int,
    val description: String,
    val cookingSteps: List<String>,
    val tags: List<String>,
    val time: Int
)