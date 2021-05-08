package com.example.bottomtest.ui.ingredients

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.navArgs
import com.example.bottomtest.R
import com.example.bottomtest.databinding.ActivityShowCocktailsBinding
import com.example.bottomtest.databinding.ActivityShowIngredientsBinding
import com.example.bottomtest.ui.cocktails.CocktailsShowArgs

class IngredientShow : AppCompatActivity() {

    private lateinit var binding: ActivityShowIngredientsBinding

    private val args by navArgs<IngredientShowArgs>()
    //private lateinit var mIngredientViewModel: IngredientViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowIngredientsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.nameInputShow.text = args.currentIngredient.name
        binding.descriptionInputShow.text = args.currentIngredient.description
        if (args.currentIngredient.degree != 0){
            binding.degreeInputShow.isVisible = true
            binding.degreeInputShow.text = "Крепость: ${args.currentIngredient.degree}°"
        }
        binding.typeInputShow.text = args.currentIngredient.type.toString()

        /*var receiptSteps = args.currentCocktail.receipt.split(" ").toTypedArray()
        receiptSteps.forEach{
            binding.receiptInputShow.append(it)
        }
         */
        //binding.receiptInputShow.text = receiptSteps.toString()
        binding.editButton.setOnClickListener {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.change_item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}