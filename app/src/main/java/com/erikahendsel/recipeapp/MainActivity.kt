package com.erikahendsel.recipeapp

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.erikahendsel.recipeapp.Model.RecipeModelClass
import com.erikahendsel.recipeapp.databinding.ActivityMainBinding
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
                //val test = resources.getIdentifier(recipe.getString("image"), "drawable", packageName)
                //R.drawable.mushroom_soup
                //val test = resources.getIdentifier(recipe.getString("image"), "drawable", packageName)
                val test =  applicationContext.resources.getIdentifier(
                    recipe.getString("image"),
                    "drawable",
                    applicationContext.packageName
                )
                Log.d("res222", "${test} testing")
                val s = "R.drawable." + recipe.getString("image")
                val image = test
                //Log.d("res2222", "$image testing2 ${s}")
                val description = recipe.getString("description")
                val cookingSteps = listOf(recipe.getString("cookingSteps"))
                val tags = listOf(recipe.getString("tags"))
                val time = recipe.getInt("time")

                val recipeDetails = RecipeModelClass(id, title, image, description, cookingSteps, tags, time)
                recipeList.add(recipeDetails)
                Log.d("RECIPE2", "$recipeList")

            }

        } catch(e: JSONException) {
            e.printStackTrace()
        }

        binding.rvRecipeList.layoutManager = LinearLayoutManager(this)
        val itemAdapter = RecipeAdapter(this, recipeList)
        var adapter = itemAdapter
        binding.rvRecipeList.adapter = adapter
        adapter.setOnItemClickListener(object : RecipeAdapter.OnRecipeClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "You clicked $position", Toast.LENGTH_SHORT).show()
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
}