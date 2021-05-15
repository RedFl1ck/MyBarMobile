package com.example.bottomtest.roomdb.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.ui.cocktails.CocktailsFragmentDirections
import com.example.bottomtest.ui.cocktails.CocktailsShow
import com.example.bottomtest.ui.ingredients.IngredientShow
import kotlinx.android.synthetic.main.row_cocktail_table.view.*
import java.io.InputStream

class CocktailListAdapter constructor(private val activity: Activity, private val context: Context) : RecyclerView.Adapter<CocktailListAdapter.MyViewHolder>() {

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
        val ims: InputStream = context.assets.open("Images/${currentItem.picture}")
        // load image as Drawable
        val d = Drawable.createFromStream(ims, null)
        // set image to ImageView
        holder.itemView.cocktail_picture.setImageDrawable(d)
        ims.close()
        holder.itemView.back_picture.setBackgroundResource(R.drawable.full_moon)
        holder.itemView.cocktail_name_txt.text = currentItem.name
        holder.itemView.cocktail_volume_txt.text = "Объем: ${currentItem.volume} мл"
        holder.itemView.cocktail_degree_txt.text = "Крепость: ${currentItem.degree}°"
        holder.itemView.cocktail_group_txt.text = currentItem.cocktail_group.toString()
        holder.itemView.cocktail_taste_txt.text = currentItem.taste

        holder.itemView.setOnClickListener {
            val act = activity.toString().split(".").toTypedArray()
            if ("ui" in act){
                val arg = Intent(activity, CocktailsShow::class.java)
                arg.putExtra(CocktailsShow.COCKTAIL, currentItem)
                ContextCompat.startActivity(activity, arg, null)
            } else {
                val action = CocktailsFragmentDirections.actionNavCocktailsToCocktailsShow(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    fun setData(cocktail: List<Cocktail>){
        this.cocktailList = cocktail
        notifyDataSetChanged()
    }
}