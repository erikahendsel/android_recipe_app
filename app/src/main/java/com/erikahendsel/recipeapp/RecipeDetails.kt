package com.erikahendsel.recipeapp

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.core.text.toSpannable
import androidx.core.text.toSpanned
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
        val recipeIngredients = intent.getStringArrayListExtra("RECIPE_INGREDIENTS")
        val recipeCookingSteps = intent.getStringArrayListExtra("RECIPE_COOKING_STEPS")
        val recipeTags = intent.getStringArrayListExtra("RECIPE_TAGS")
        val recipePrepTime = intent.getStringExtra("RECIPE_PREP_TIME")
        val recipeCookTime = intent.getStringExtra("RECIPE_COOK_TIME")
        val recipeAuthor = intent.getStringExtra("RECIPE_AUTHOR")
        val recipeLink = intent.getStringExtra("RECIPE_LINK")
        Log.d(
            "RECIPELIST",
            "$recipeImage, $recipeTitle, $recipeDescription, $recipeCookTime, $recipeTags"
        )

        val ingredientsWithLineSeparator = addLineSeparatorToArray(recipeIngredients)
        val cookingStepsWithLineSeparator = addLineSeparatorToArray(recipeCookingSteps)

        binding.ivRecipeImage.setImageResource(recipeImage)
        binding.ivRecipeImage.contentDescription = recipeTitle
        binding.tvRecipeTitle.text = recipeTitle
        binding.prepTime.text = recipePrepTime
        binding.cookTime.text = recipeCookTime
        binding.tvRecipeDescription.text = recipeDescription
        binding.tvTags.text = "Tags: ${recipeTags.toString().replace("[", "").replace("]", "")}"
        binding.tvIngredients.text = TextUtils.join("", ingredientsWithLineSeparator)
        binding.tvSteps.text = TextUtils.join("", cookingStepsWithLineSeparator)
        binding.tvAuthor.text = "- By $recipeAuthor"
        binding.tvLink.text = recipeLink
//        binding.tvLink.setOnClickListener {
//            val webIntent: Intent = Uri.parse(recipeLink).let { webpage ->
//                Intent(Intent.ACTION_VIEW, webpage)
//            }
//            startActivity(webIntent)
//        }
    }

    private fun addLineSeparatorToArray(passedArrayList: ArrayList<String>?): ArrayList<String> {
        val passedArrayWithLineSeparator: ArrayList<String> = ArrayList()
        if (passedArrayList != null) {
            for (i in 0 until passedArrayList.size) {
                passedArrayWithLineSeparator.add("${passedArrayList[i]} ${System.getProperty("line.separator")}")
            }
        }
        return passedArrayWithLineSeparator
    }
}