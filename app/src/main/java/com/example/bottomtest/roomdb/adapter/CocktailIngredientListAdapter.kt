package com.example.bottomtest.roomdb.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.model.IngredientsList
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel
import com.example.bottomtest.ui.ingredients.IngredientShow
import kotlinx.android.synthetic.main.row_ingredients_cocktail.view.*
import kotlinx.android.synthetic.main.row_ingredients_table.view.ingredient_degree_txt
import kotlinx.android.synthetic.main.row_ingredients_table.view.ingredient_name_txt
import kotlinx.android.synthetic.main.row_ingredients_table.view.ingredient_picture
import kotlinx.android.synthetic.main.row_ingredients_table.view.ingredient_type_txt
import kotlinx.coroutines.coroutineScope

class CocktailIngredientListAdapter constructor(
    private val activity: Activity,
    private val context: Context
) : RecyclerView.Adapter<CocktailIngredientListAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<IngredientsList>()

    private lateinit var mIngredientViewModel: IngredientViewModel

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_ingredients_cocktail, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ingredient = ingredientsList[position]
        holder.itemView.ingredient_picture.setBackgroundResource(R.drawable.ingredient_show)
        holder.itemView.ingredient_name_txt.text = ingredient.name
        holder.itemView.ingredient_type_txt.text = ingredient.type.toString()

        if (ingredient.degree != 0) {
            holder.itemView.ingredient_degree_txt.isVisible = true
            holder.itemView.ingredient_degree_txt.text = "Крепость: ${ingredient.degree}°"
        } else {
            holder.itemView.ingredient_degree_txt.isVisible = false
        }
        holder.itemView.ingredient_volume_txt.text = ingredient.volume.toString()

        mIngredientViewModel = IngredientViewModel(activity.application)

        holder.itemView.setOnClickListener {

            val passedIngredient = Ingredients(ingredient.id,
                ingredient.name,
                ingredient.description,
                ingredient.picture,
                ingredient.type,
                ingredient.degree,
                ingredient.is_favourite,
                ingredient.created_by_user,
                ingredient.open_count,
                ingredient.shopping_count)
            val arg = Intent(activity, IngredientShow::class.java)
            arg.putExtra(IngredientShow.INGREDIENT, passedIngredient)
            ContextCompat.startActivity(activity, arg, null)
        }
    }

    fun setData(ingredient: List<IngredientsList>) {
        this.ingredientsList = ingredient
        notifyDataSetChanged()
    }
}