package com.erikahendsel.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.erikahendsel.recipeapp.databinding.ActivityRecipeDetailsBinding
import kotlinx.coroutines.joinAll

class RecipeDetails : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeTitle = intent.getStringExtra("RECIPE_TITLE")
        val recipeImage = intent.getIntExtra("RECIPE_IMAGE", 0)
        val recipeDescription = intent.getStringExtra("RECIPE_DESCRIPTION")
        val recipeCookingSteps = intent.getStringArrayListExtra("RECIPE_COOKING_STEPS")
        val recipeTags = intent.getStringArrayListExtra("RECIPE_TAGS")
        val recipePrepTime = intent.getStringExtra("RECIPE_PREP_TIME")
        val recipeCookTime = intent.getStringExtra("RECIPE_COOK_TIME")
        Log.d("RECIPELIST", "$recipeImage, $recipeTitle, $recipeDescription, $recipeCookTime, $recipeTags")

        val cookingStepsArrayWithLineSeparator: ArrayList<String> = ArrayList()
        if (recipeCookingSteps != null) {
            for (i in 0 until recipeCookingSteps.size) {
                cookingStepsArrayWithLineSeparator.add("${recipeCookingSteps[i]} ${System.getProperty("line.separator")}")
                Log.d("RUNARRAY", "${recipeCookingSteps[i]}")
            }
        }
        binding.ivRecipeImage.setImageResource(recipeImage)
        binding.tvRecipeTitle.text = recipeTitle
        binding.prepTime.text = "Prep: $recipePrepTime"
        binding.cookTime.text = "Cook: $recipeCookTime"
        binding.tvRecipeDescription.text = recipeDescription
        binding.tvTags.text = "Tags: ${recipeTags.toString().replace("[", "").replace("]", "")}"
        binding.tvSteps.text = TextUtils.join("", cookingStepsArrayWithLineSeparator)
    }
}