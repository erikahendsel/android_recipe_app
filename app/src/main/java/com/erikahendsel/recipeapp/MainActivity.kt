package com.erikahendsel.recipeapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.erikahendsel.recipeapp.Model.RecipeModelClass
import com.erikahendsel.recipeapp.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeList: ArrayList<RecipeModelClass> = ArrayList()
        try {
            val obj = JSONObject(getJSONFromAssets()!!)
            val recipesArray = obj.getJSONArray("recipes")
            for (i in 0..3) {
                val recipe = recipesArray.getJSONObject(i)
                val id = recipe.getString("id")
                val title = recipe.getString("title")
                val image = applicationContext.resources.getIdentifier(
                    recipe.getString("image"),
                    "drawable",
                    applicationContext.packageName
                )
                val description = recipe.getString("description")
                val ingredients = convertJSONArrayToStringList(recipe.getJSONArray("ingredients"))
                val cookingSteps = convertJSONArrayToStringList(recipe.getJSONArray("cookingSteps"))
                val tags =  convertJSONArrayToStringList(recipe.getJSONArray("tags"))
                Log.d("TAGS", "${tags}")
                val prepTime = recipe.getString("prepTime")
                val cookTime = recipe.getString("cookTime")
                val recipeDetails = RecipeModelClass(id, title, image, description, ingredients, cookingSteps, tags, prepTime, cookTime)
                recipeList.add(recipeDetails)

            }

        } catch(e: JSONException) {
            e.printStackTrace()
        }

        //LinearLayoutManager was used before
        binding.rvRecipeList.layoutManager = GridLayoutManager(this, 2)
        val itemAdapter = RecipeAdapter(this, recipeList)
        var adapter = itemAdapter
        binding.rvRecipeList.adapter = adapter
        adapter.setOnItemClickListener(object : RecipeAdapter.OnRecipeClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "You clicked $position", Toast.LENGTH_SHORT).show()
                Log.d("RECIPELIST", "${recipeList[position].image} image")


                Intent(this@MainActivity, RecipeDetails::class.java).also {
                    it.putExtra("RECIPE_TITLE", recipeList[position].title)
                    it.putExtra("RECIPE_IMAGE", recipeList[position].image)
                    it.putExtra("RECIPE_DESCRIPTION", recipeList[position].description)
                    it.putStringArrayListExtra("RECIPE_INGREDIENTS", ArrayList(recipeList[position].ingredients))
                    it.putStringArrayListExtra("RECIPE_COOKING_STEPS", ArrayList(recipeList[position].cookingSteps))
                    it.putStringArrayListExtra("RECIPE_TAGS", ArrayList(recipeList[position].tags))
                    it.putExtra("RECIPE_PREP_TIME", recipeList[position].prepTime)
                    it.putExtra("RECIPE_COOK_TIME", recipeList[position].cookTime)
                    startActivity(it)
                }
            }

        })
    }

    private fun getJSONFromAssets(): String? {
        var json: String? = null
        var charset: Charset = Charsets.UTF_8
        try {
            val recipesJSONFile = assets.open("recipes.json")

            val size = recipesJSONFile.available()
            val buffer = ByteArray(size)
            recipesJSONFile.read(buffer)
            recipesJSONFile.close()
            json = String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    private fun convertJSONArrayToStringList(passedJSONArray: JSONArray): List<String> {
        var listPlaceHolder : ArrayList<String> = ArrayList()
        for (i in 0 until passedJSONArray.length()) {
            listPlaceHolder.add(passedJSONArray[i].toString())
        }
        return listPlaceHolder
    }
}