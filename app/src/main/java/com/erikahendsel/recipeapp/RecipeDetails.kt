package com.erikahendsel.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.erikahendsel.recipeapp.databinding.ActivityRecipeDetailsBinding
import com.erikahendsel.recipeapp.databinding.RecipeRowLayoutBinding

class RecipeDetails : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeTitle = intent.getStringExtra("RECIPE_TITLE")
        //val recipeImage = intent.getStringExtra("RECIPE_IMAGE")
        val recipeDescription = intent.getStringExtra("RECIPE_DESCRIPTION")
        val recipeCookingSteps = intent.getStringArrayListExtra("RECIPE_COOKING_STEPS")
        val recipeTags = intent.getStringArrayListExtra("RECIPE_TAGS")
        val recipeTime = intent.getStringExtra("RECIPE_TIME")
        Log.d("RECIPELIST", "$recipeTitle, $recipeDescription, $recipeTime, $recipeTags")

        binding.tvRecipeTitle.text = recipeTitle.toString()
        binding.tvRecipeDescription.text = recipeDescription.toString()
        binding.tvTags.text = recipeTags.toString()
        binding.tvSteps.text = recipeCookingSteps.toString()
    }
}