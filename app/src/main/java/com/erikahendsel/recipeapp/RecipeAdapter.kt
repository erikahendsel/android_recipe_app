package com.erikahendsel.recipeapp

import android.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.erikahendsel.recipeapp.Model.RecipeModelClass
import com.erikahendsel.recipeapp.databinding.RecipeRowLayoutBinding


class RecipeAdapter(var context: Context, var items: ArrayList<RecipeModelClass>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private  lateinit var recipeListener : OnRecipeClickListener

    interface OnRecipeClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnRecipeClickListener) {
        recipeListener = listener
    }

    class RecipeViewHolder(val binding: RecipeRowLayoutBinding, listener: OnRecipeClickListener): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.llRecipeRow.setOnClickListener {
                listener.onItemClick(absoluteAdapterPosition)
            }
        }
    }

    //onCreateViewHolder gets called only once in order to create the item view holder instance
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater  = LayoutInflater.from(context)
        val binding = RecipeRowLayoutBinding.inflate(layoutInflater , parent, false)

        return RecipeViewHolder(binding, recipeListener)
    }

    //onBindViewHolder gets called every time an item gets recycled or comes into into view
    //That's why, it's not a great idea to put a onCLickListener here
    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binding.apply {

//            imgImage.setImageURI(items[position].image.toUri())
//         val test: Int = this@RecipeAdapter.context.resources.getIdentifier(items[position].image, "drawable", this@RecipeAdapter.context.packageName)
            //val test = context.resources.getIdentifier(items[position].image, "drawable", context.packageName)
            //getResources().getIdentifier(recipe.getString("image"), "drawable", getPackageName());

            Log.d("TESTING", "${items[position].image}")
//            imgImage.setImageResource(items[position].image)
            imgImage.setImageDrawable(context.getDrawable(items[position].image))
            tvTitle.text = items[position].title
            tvTags.text = items[position].tags.toString()

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}