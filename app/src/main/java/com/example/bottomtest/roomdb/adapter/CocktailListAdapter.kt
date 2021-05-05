package com.example.bottomtest.roomdb.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.fragments.update.UpdateCocktail
import com.example.bottomtest.ui.cocktails.CocktailsFragmentDirections
import kotlinx.android.synthetic.main.my_row.view.*

class CocktailListAdapter constructor(private val activity: Fragment, private val context: Context) : RecyclerView.Adapter<CocktailListAdapter.MyViewHolder>() {

    private var cocktailList = emptyList<Cocktail>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.my_row, parent, false))
    }

    override fun getItemCount(): Int {
        return cocktailList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = cocktailList[position]
        holder.itemView.cocktail_id_txt.text = currentItem.id.toString()
        holder.itemView.cocktail_name_txt.text = currentItem.name
        holder.itemView.cocktail_description_txt.text = currentItem.description
        holder.itemView.cocktail_degree_txt.text = currentItem.degree.toString()

        holder.itemView.setOnClickListener {
            val action = CocktailsFragmentDirections.actionNavCocktailsToUpdateActivity(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(cocktail: List<Cocktail>){
        this.cocktailList = cocktail
        notifyDataSetChanged()
    }
}