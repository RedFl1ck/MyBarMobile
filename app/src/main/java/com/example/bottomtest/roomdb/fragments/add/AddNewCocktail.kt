package com.example.bottomtest.roomdb.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel
import kotlinx.android.synthetic.main.activity_add.*

class AddNewCocktail : AppCompatActivity() {

    private lateinit var mCocktailViewModel: CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mCocktailViewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)

    }


    private fun insertDataToDatabase(name: String,
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
        val cocktail = Cocktail(
            0,
            name,
            description,
            Integer.parseInt(degree.toString()),
            Integer.parseInt(volume.toString()),
            receipt,
            group,
            Integer.parseInt(basis_id.toString()),
            taste,
            is_updatable,
            is_deleted,
            is_favourite
        )
        // Add Data to Database
        mCocktailViewModel.addCocktail(cocktail)
        Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()

    }

    private fun inputCheck(name: String,
                           description: String,
                           degree: Editable,
                           volume: Editable,
                           receipt: String,
                           group: String,
                           basis_id: Editable,
                           taste: String,
                           is_favourite: Boolean): Boolean{
        return !(TextUtils.isEmpty(name)
                && TextUtils.isEmpty(description)
                && degree.isEmpty()
                && volume.isEmpty()
                && TextUtils.isEmpty(receipt)
                && TextUtils.isEmpty(group)
                && basis_id.isEmpty()
                && TextUtils.isEmpty(taste)
                && TextUtils.isEmpty(is_favourite.toString()))
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
                val name = name_input.text.toString()
                val description = description_input.text.toString()
                val degree = degree_input.text
                val volume = volume_input.text
                val receipt = receipt_input.text.toString()
                val group = group_input.text.toString()
                val basis_id = basis_id_input.text
                val taste = taste_input.text.toString()
                val is_updatable = false
                val is_deleted = false
                val is_favourite = is_favourite_input.text

                if(inputCheck(name, description, degree, volume, receipt, group, basis_id, taste, is_favourite)){
                    insertDataToDatabase(name, description, degree, volume, receipt, group, basis_id, taste, is_updatable, is_deleted, is_favourite)
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