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


    private fun insertDataToDatabase(fN: String, lN: String, ag: Editable) {
        // Create Cocktail Object
        val cocktail = Cocktail(
            0,
            fN,
            lN,
            Integer.parseInt(ag.toString())
        )
        // Add Data to Database
        mCocktailViewModel.addCocktail(cocktail)
        Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_new, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_button -> {
                val firstName = title_input.text.toString()
                val lastName = author_input.text.toString()
                val age = pages_input.text

                if(inputCheck(firstName, lastName, age)){
                    insertDataToDatabase(firstName, lastName, age)
                    finish()
                    true
                } else {
                    Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
                    false
                }

            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}