package com.example.bottomtest.roomdb.fragments.add

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bottomtest.R
import com.example.bottomtest.databinding.ActivityAddCocktailBinding
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel

class AddNewCocktail : AppCompatActivity() {

    private lateinit var binding: ActivityAddCocktailBinding

    private lateinit var mCocktailViewModel: CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCocktailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mCocktailViewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)

    }


    private fun insertDataToDatabase(cocktail: Cocktail) {
        // Add Data to Database
        mCocktailViewModel.addCocktail(cocktail)
        Toast.makeText(this, "Successfully added!", Toast.LENGTH_LONG).show()
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
                val cocktail = Cocktail(0,
                    binding.nameInput.text.toString(),
                    binding.descriptionInput.text.toString(),
                    binding.degreeInput.text.toString().toInt(),
                    "22.13.jpg",
                    binding.volumeInput.text.toString().toInt(),
                    binding.receiptInput.text.toString(),
                    binding.groupInput.text.toString(),
                    binding.basisIdInput.text.toString().toInt(),
                    binding.tasteInput.text.toString(),
                    is_updatable = false,
                    is_deleted = false,
                    is_favourite = false)
                //TODO: FAVOURITE BUTTON

                if(mCocktailViewModel.inputCheck(cocktail)){
                    insertDataToDatabase(cocktail)
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