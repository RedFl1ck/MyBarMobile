package com.example.bottomtest.roomdb.fragments.update

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.example.bottomtest.R
import com.example.bottomtest.databinding.ActivityUpdateCocktailBinding
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel


class UpdateCocktail : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateCocktailBinding

    private val args by navArgs<UpdateCocktailArgs>()

    private lateinit var mCocktailViewModel: CocktailViewModel

    private var isFavourite: Boolean = false
    private var isDeleted: Boolean = false
    private var isUpdatable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCocktailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.deleteButton.setOnClickListener {
            deleteCocktail()
        }

        mCocktailViewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)

        binding.nameInputUpdate.setText(args.currentCocktail.name)
        binding.descriptionInputUpdate.setText(args.currentCocktail.description)
        binding.degreeInputUpdate.setText(args.currentCocktail.degree.toString())
        binding.volumeInputUpdate.setText(args.currentCocktail.volume.toString())
        binding.receiptInputUpdate.setText(args.currentCocktail.receipt)
        binding.groupInputUpdate.setText(args.currentCocktail.cocktail_group)
        binding.basisIdInputUpdate.setText(args.currentCocktail.basis_id.toString())
        binding.tasteInputUpdate.setText(args.currentCocktail.taste)
        isFavourite = args.currentCocktail.is_favourite
        isDeleted = args.currentCocktail.is_deleted
        isUpdatable = args.currentCocktail.is_updatable

    }


    private fun updateItem(
        name: String,
        description: String,
        degree: Int,
        volume: Int,
        receipt: String,
        group: String?,
        basis_id: Int,
        taste: String,
        is_updatable: Boolean,
        is_deleted: Boolean,
        is_favourite: Boolean) {
        // Create Cocktail Object
        val updatedCocktail = Cocktail(args.currentCocktail.id, name,
                    description,
                    Integer.parseInt(degree.toString()),
                    null,
                    Integer.parseInt(volume.toString()),
                    receipt,
                    group,
                    Integer.parseInt(basis_id.toString()),
                    taste,
                    is_updatable,
                    is_deleted,
                    is_favourite)
        // Update Current Cocktail
        mCocktailViewModel.updateCocktail(updatedCocktail)
    }

    private fun deleteCocktail() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            //TODO: CHECK FOR CREATED BY USER
            isDeleted = true
            updateItem(args.currentCocktail.name,
                args.currentCocktail.description,
                args.currentCocktail.degree,
                args.currentCocktail.volume,
                args.currentCocktail.receipt,
                args.currentCocktail.cocktail_group,
                args.currentCocktail.basis_id,
                args.currentCocktail.taste, isUpdatable, isDeleted, isFavourite)
            Toast.makeText(
                this,
                "Successfully removed: ${args.currentCocktail.name}",
                Toast.LENGTH_SHORT).show()
            finish()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentCocktail.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentCocktail.name}?")
        builder.create().show()
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
                val name = binding.nameInputUpdate.text.toString()
                val description = binding.descriptionInputUpdate.text.toString()
                val degree = binding.degreeInputUpdate.text.toString().toInt()
                val volume = binding.volumeInputUpdate.text.toString().toInt()
                val receipt = binding.receiptInputUpdate.text.toString()
                val group = binding.groupInputUpdate.text.toString()
                val basisId = binding.basisIdInputUpdate.text.toString().toInt()
                val taste = binding.tasteInputUpdate.text.toString()

                if(mCocktailViewModel.inputCheck(name, description, degree, volume, receipt, group, basisId, taste, isUpdatable, isDeleted, isFavourite)){
                    updateItem(name, description, degree, volume, receipt, group, basisId, taste, isUpdatable, isDeleted, isFavourite)
                    Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show()
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