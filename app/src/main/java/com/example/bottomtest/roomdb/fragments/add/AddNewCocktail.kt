package com.example.bottomtest.roomdb.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bottomtest.R
import com.example.bottomtest.databinding.ActivityAddBinding
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel

class AddNewCocktail : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding

    private lateinit var mCocktailViewModel: CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mCocktailViewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)

    }


    private fun insertDataToDatabase(
        name: String,
        description: String,
        degree: Int,
        volume: Int,
        receipt: String,
        group: String,
        basis_id: Int,
        taste: String,
        is_favourite: Boolean) {
        // Create Cocktail Object
        val cocktail = Cocktail(
            0,
            name,
            description,
            Integer.parseInt(degree.toString()),
            null,
            Integer.parseInt(volume.toString()),
            receipt,
            group,
            Integer.parseInt(basis_id.toString()),
            taste,
            is_updatable = false,
            is_deleted = false,
            is_favourite = is_favourite
        )
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
                val name = binding.nameInput.text.toString()
                val description = binding.descriptionInput.text.toString()
                val degree = binding.degreeInput.text.toString().toInt()
                val volume = binding.volumeInput.text.toString().toInt()
                val receipt = binding.receiptInput.text.toString()
                val group = binding.groupInput.text.toString()
                val basisId = binding.basisIdInput.text.toString().toInt()
                val taste = binding.tasteInput.text.toString()
                val isUpdatable = false
                val isDeleted = false
                //TODO: FAVOURITE BUTTON
                val isFavourite = false

                if(mCocktailViewModel.inputCheck(name, description, degree, volume, receipt, group, basisId, taste, isUpdatable, isDeleted, isFavourite)){
                    insertDataToDatabase(name, description, degree, volume, receipt, group, basisId, taste, isFavourite)
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