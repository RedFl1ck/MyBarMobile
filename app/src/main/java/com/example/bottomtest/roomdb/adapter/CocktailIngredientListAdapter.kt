package com.example.bottomtest.roomdb.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.CocktailsIngredients
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.model.IngredientsList
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel
import kotlinx.android.synthetic.main.row_ingredients_cocktail.view.*
import kotlinx.android.synthetic.main.row_ingredients_table.view.*
import kotlinx.android.synthetic.main.row_ingredients_table.view.ingredient_degree_txt
import kotlinx.android.synthetic.main.row_ingredients_table.view.ingredient_name_txt
import kotlinx.android.synthetic.main.row_ingredients_table.view.ingredient_picture
import kotlinx.android.synthetic.main.row_ingredients_table.view.ingredient_type_txt
import kotlinx.android.synthetic.main.row_shopping_table.view.*

class CocktailIngredientListAdapter constructor(private val activity: Activity, private val context: Context) : RecyclerView.Adapter<CocktailIngredientListAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<CocktailsIngredients>()

    private lateinit var mIngredientViewModel: IngredientViewModel

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_ingredients_cocktail, parent, false))
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = ingredientsList[position]
        holder.itemView.ingredient_picture.setBackgroundResource(R.drawable.ingredient_show)
        holder.itemView.ingredient_name_txt.text = currentItem.cocktail_id.toString()
        holder.itemView.ingredient_type_txt.text = currentItem.ingredient_id.toString()

        /*if (currentItem.degree != 0){
            holder.itemView.ingredient_degree_txt.isVisible = true
            holder.itemView.ingredient_degree_txt.text = "Крепость: ${currentItem.degree}°"
        }else{
            holder.itemView.ingredient_degree_txt.isVisible = false
        }
        holder.itemView.ingredient_type_txt.text = currentItem.type.toString()
        holder.itemView.ingredient_volume_txt.text = currentItem.volume.toString()*/

        mIngredientViewModel = IngredientViewModel(activity.application)

        /*holder.itemView.setOnClickListener {
            val action = IngredientsFragmentDirections.actionNavIngredientsToIngredientShow(currentItem)
            holder.itemView.findNavController().navigate(action)
        }*/
    }

    fun setData(ingredient: List<CocktailsIngredients>){
        this.ingredientsList = ingredient
        notifyDataSetChanged()
    }
}