package com.example.bottomtest.roomdb.adapter

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.ui.cocktails.CocktailsFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.row_cocktail_table.view.*
import java.io.InputStream

class FavouriteListAdapter constructor(
    private val activity: Activity,
    private val context: Context
) : RecyclerView.Adapter<FavouriteListAdapter.MyViewHolder>() {

    private var favouriteList = emptyList<Cocktail>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_cocktail_table, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = favouriteList[position]
        val ims: InputStream = context.assets.open("Images/${currentItem.picture}")
        // load image as Drawable
        val d = Drawable.createFromStream(ims, null)
        // set image to ImageView
        holder.itemView.cocktail_picture.setImageDrawable(d)
        ims.close()
        holder.itemView.cocktail_name_txt.text = currentItem.name
        holder.itemView.cocktail_volume_txt.text = "Объем: ${currentItem.volume} мл"
        holder.itemView.cocktail_degree_txt.text = "Крепость: ${currentItem.degree}°"
        holder.itemView.cocktail_group_txt.text = currentItem.cocktail_group.toString()
        holder.itemView.cocktail_taste_txt.text = currentItem.taste

        holder.itemView.setOnClickListener {
            val action = CocktailsFragmentDirections.actionNavCocktailsToCocktailsShow(currentItem)
            holder.itemView.findNavController().navigate(action)
            activity.findViewById<BottomNavigationView?>(R.id.nav_view)?.isVisible = true
        }
    }

    fun setData(cocktail: List<Cocktail>) {
        this.favouriteList = cocktail
        notifyDataSetChanged()
    }
}