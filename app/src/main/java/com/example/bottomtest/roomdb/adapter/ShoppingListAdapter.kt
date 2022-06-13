package com.example.bottomtest.roomdb.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.ItemShopping
import com.example.bottomtest.roomdb.viewmodel.ShoppingListViewModel
import kotlinx.android.synthetic.main.row_shopping_table.view.*

class ShoppingListAdapter constructor(
    private val activity: Activity,
    private val context: Context
) : RecyclerView.Adapter<ShoppingListAdapter.MyViewHolder>() {

    private var shoppingList = emptyList<ItemShopping>()

    private lateinit var mShoppingViewModel: ShoppingListViewModel

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.row_shopping_table, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return shoppingList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = shoppingList[position]
        holder.itemView.count_name_txt.text = currentItem.name
        holder.itemView.count_type_txt.text = currentItem.type
        holder.itemView.count_shopping.setText(currentItem.number.toString())
        holder.itemView.count_volume.setText(currentItem.measuring)

        mShoppingViewModel = ShoppingListViewModel(activity.application)

        holder.itemView.count_add.setOnClickListener {
            holder.itemView.count_shopping.setText(
                (holder.itemView.count_shopping.text.toString().toFloat() + 1).toString()
            )
            mShoppingViewModel.setValue(
                holder.itemView.count_shopping.text.toString().toFloat(),
                holder.itemView.count_volume.text.toString(),
                currentItem.id
            )
        }

        holder.itemView.count_delete.setOnClickListener {
            if (holder.itemView.count_shopping.text.toString().toFloat() != 0f) {
                holder.itemView.count_shopping.setText(
                    (holder.itemView.count_shopping.text.toString().toFloat() - 1).toString()
                )
                mShoppingViewModel.setValue(
                    holder.itemView.count_shopping.text.toString().toFloat(),
                    holder.itemView.count_volume.text.toString(),
                    currentItem.id
                )
            }
        }

        holder.itemView.count_delete_item.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(context)
            builder.setPositiveButton("Да") { _, _ ->
                mShoppingViewModel.delete(currentItem.id)
            }
            builder.setNegativeButton("Нет") { _, _ -> }
            builder.setTitle("Удалить ${currentItem.name}?")
            builder.setMessage("Вы уверены, что хотите удалить ${currentItem.name}?")
            builder.create().show()
        }

        holder.itemView.count_done_item.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(context)
            builder.setPositiveButton("Да") { _, _ ->
                // инкрементируем кол-во для статистики
                mShoppingViewModel.updateShoppingCount(currentItem.id, currentItem.number.toInt())

                mShoppingViewModel.delete(currentItem.id)
            }
            builder.setNegativeButton("Нет") { _, _ -> }
            builder.setTitle("Учет ${currentItem.name}?")
            builder.setMessage("Вы уверены, что хотите завершить работу с ${currentItem.name}?")
            builder.create().show()
        }

    }

    fun setData(item: List<ItemShopping>) {
        this.shoppingList = item
        notifyDataSetChanged()
    }
}