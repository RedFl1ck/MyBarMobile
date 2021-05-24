package com.example.bottomtest.roomdb.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel
import com.example.bottomtest.ui.ingredients.IngredientsFragmentDirections
import kotlinx.android.synthetic.main.row_ingredients_table.view.*

class IngredientsListAdapter constructor(private val activity: Activity, private val context: Context) : RecyclerView.Adapter<IngredientsListAdapter.MyViewHolder>() {

    private var ingredientsList = emptyList<Ingredients>()
    private var shoppingList = emptyList<Int>()

    private lateinit var mIngredientViewModel: IngredientViewModel

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_ingredients_table, parent, false))
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = ingredientsList[position]
        holder.itemView.ingredient_picture.setBackgroundResource(R.drawable.ingredient_show)
        holder.itemView.ingredient_name_txt.text = currentItem.name
        if (currentItem.degree != 0){
            holder.itemView.ingredient_degree_txt.isVisible = true
            holder.itemView.ingredient_degree_txt.text = "Крепость: ${currentItem.degree}°"
        }else{
            holder.itemView.ingredient_degree_txt.isVisible = false
        }
        holder.itemView.ingredient_type_txt.text = currentItem.type.toString()
        holder.itemView.ingredient_add_to_cart.isVisible = currentItem.id !in shoppingList
        mIngredientViewModel = IngredientViewModel(activity.application)
        holder.itemView.ingredient_add_to_cart.setOnClickListener {
            mIngredientViewModel.addToCart(currentItem.id)
        }

        holder.itemView.setOnClickListener {
            val action = IngredientsFragmentDirections.actionNavIngredientsToIngredientShow(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(ingredient: List<Ingredients>){
        this.ingredientsList = ingredient
        notifyDataSetChanged()
    }

    fun setShopping(shoppingList: List<Int>){
        this.shoppingList = shoppingList
        notifyDataSetChanged()
    }
}