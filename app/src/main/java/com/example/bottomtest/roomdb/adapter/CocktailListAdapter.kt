package com.example.bottomtest.roomdb.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.fragments.update.UpdateCocktail
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
        holder.itemView.book_id_txt.text = currentItem.id.toString()
        holder.itemView.book_author_txt.text = currentItem.firstName
        holder.itemView.book_title_txt.text = currentItem.lastName
        holder.itemView.book_pages_txt.text = currentItem.age.toString()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, UpdateCocktail::class.java)
            intent.putExtra("id", currentItem.id.toString())
            intent.putExtra("title", currentItem.firstName)
            intent.putExtra("author", currentItem.lastName)
            intent.putExtra("pages", currentItem.age.toString())
            activity.startActivityForResult(intent, 1)
        }

        /*holder.itemView.cardView.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }*/
    }

    fun setData(cocktail: List<Cocktail>){
        this.cocktailList = cocktail
        notifyDataSetChanged()
    }
}