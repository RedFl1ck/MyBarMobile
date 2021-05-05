package com.example.bottomtest.roomdb.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel
import kotlinx.android.synthetic.main.activity_update.*


class UpdateCocktail : AppCompatActivity() {


    private val args by navArgs<UpdateCocktailArgs>()

    private lateinit var mCocktailViewModel: CocktailViewModel
    private var delete_button: Button? = null

    private var isFavourite: Boolean = false
    private var isDeleted: Boolean = false
    private var isUpdatable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        delete_button = findViewById(R.id.delete_button)


        delete_button?.setOnClickListener { deleteCocktail() }

        mCocktailViewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)

        /*
        name_input_update.setText(args.currentCocktail.name)
        description_input_update.setText(args.currentCocktail.description)
        degree_input_update.setText(args.currentCocktail.degree)
        volume_input_update.setText(args.currentCocktail.volume)
        receipt_input_update.setText(args.currentCocktail.receipt)
        group_input_update.setText(args.currentCocktail.group)
        basis_id_input_update.setText(args.currentCocktail.basis_id)
        taste_input_update.setText(args.currentCocktail.taste)
        isFavourite = args.currentCocktail.is_favourite
        isDeleted = args.currentCocktail.is_deleted
        isUpdatable = args.currentCocktail.is_updatable
        */

    }


    private fun updateItem(name: String,
                           description: String,
                           degree: Editable,
                           volume: Editable,
                           receipt: String,
                           group: String,
                           basis_id: Editable,
                           taste: String,
                           is_updatable: Boolean,
                           is_deleted: Boolean,
                           is_favourite: Boolean) {

        // Create Cocktail Object
        //val updatedCocktail = Cocktail(args.currentCocktail.id, name,
        //            description,
        //            Integer.parseInt(degree.toString()),
        //            Integer.parseInt(volume.toString()),
        //            receipt,
        //            group,
        //            Integer.parseInt(basis_id.toString()),
        //            taste,
        //            is_updatable,
        //            is_deleted,
        //            is_favourite)
        // Update Current Cocktail
        //mCocktailViewModel.updateCocktail(updatedCocktail)
        Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show()

    }

    private fun deleteCocktail() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            args.currentCocktail.is_deleted = true
            /*Toast.makeText(
                this,
                "Successfully removed: ${args.currentCocktail.firstName}",
                Toast.LENGTH_SHORT).show()*/
            finish()
        }
        builder.setNegativeButton("No") { _, _ -> }
        //builder.setTitle("Delete ${args.currentCocktail.firstName}?")
        //builder.setMessage("Are you sure you want to delete ${args.currentCocktail.firstName}?")
        builder.create().show()
    }

    private fun inputCheck(name: String,
                           description: String,
                           degree: Editable?,
                           volume: Editable?,
                           receipt: String,
                           group: String,
                           basis_id: Editable?,
                           taste: String,
                           is_updatable: Boolean,
                           is_favourite: Boolean): Boolean {
        return if (degree != null && volume != null && basis_id != null) {
            !(TextUtils.isEmpty(name)
                    && TextUtils.isEmpty(description)
                    && degree.isEmpty()
                    && volume.isEmpty()
                    && TextUtils.isEmpty(receipt)
                    && TextUtils.isEmpty(group)
                    && basis_id.isEmpty()
                    && TextUtils.isEmpty(taste)
                    && TextUtils.isEmpty(is_updatable.toString())
                    && TextUtils.isEmpty(is_favourite.toString()))
        }
        else{
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_new, menu)
        return true
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_button -> {
                val name = name_input_update.text.toString()
                val description = description_input_update.text.toString()
                val degree = degree_input_update.text
                val volume = volume_input_update.text
                val receipt = receipt_input_update.text.toString()
                val group = group_input_update.text.toString()
                val basis_id = basis_id_input_update.text
                val taste = taste_input_update.text.toString()
                val is_updatable = if ()
                val is_deleted = false
                val is_favourite = is_favourite_input_update.text.toString()

                if(inputCheck(name, description, degree, volume, receipt, group, basis_id, taste, is_updatable, is_favourite)){
                    updateItem(name, description, degree, volume, receipt, group, basis_id, taste, is_updatable, is_deleted, is_favourite)
                    finish()
                    true
                } else {
                    Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
                    false
                }

            }
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}