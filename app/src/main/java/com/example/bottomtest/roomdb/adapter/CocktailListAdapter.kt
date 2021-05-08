package com.example.bottomtest.roomdb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.ui.cocktails.CocktailsFragmentDirections
import kotlinx.android.synthetic.main.row_cocktail_table.view.*

class CocktailListAdapter constructor(private val activity: Fragment, private val context: Context) : RecyclerView.Adapter<CocktailListAdapter.MyViewHolder>() {

    private var cocktailList = emptyList<Cocktail>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row_cocktail_table, parent, false))
    }

    override fun getItemCount(): Int {
        return cocktailList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = cocktailList[position]
        holder.itemView.cocktail_picture.setBackgroundResource(R.drawable.cocktail_show)
        holder.itemView.cocktail_name_txt.text = currentItem.name
        holder.itemView.cocktail_volume_txt.text = "Объем: ${currentItem.volume} мл"
        holder.itemView.cocktail_degree_txt.text = "Крепость: ${currentItem.degree}°"
        holder.itemView.cocktail_group_txt.text = currentItem.cocktail_group.toString()
        holder.itemView.cocktail_taste_txt.text = currentItem.taste

        holder.itemView.setOnClickListener {
            val action = CocktailsFragmentDirections.actionNavCocktailsToCocktailsShow(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(cocktail: List<Cocktail>){
        this.cocktailList = cocktail
        notifyDataSetChanged()
    }
}