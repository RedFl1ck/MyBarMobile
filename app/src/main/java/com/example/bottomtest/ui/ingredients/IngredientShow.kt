package com.example.bottomtest.ui.ingredients

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomtest.R
import com.example.bottomtest.databinding.ActivityShowIngredientsBinding
import com.example.bottomtest.roomdb.adapter.CocktailListAdapter
import com.example.bottomtest.roomdb.model.Ingredients
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel

class IngredientShow : AppCompatActivity() {

    companion object {const val INGREDIENT = "ingredient"}

    private var ingredient: Ingredients? = null

    private lateinit var binding: ActivityShowIngredientsBinding

    private lateinit var mIngredientViewModel: IngredientViewModel

    private lateinit var mCocktailViewModel: CocktailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowIngredientsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mIngredientViewModel = IngredientViewModel(application)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ingredient = initIngredient()
        title = ingredient?.name
        // Recyclerview
        val adapter = CocktailListAdapter(this, applicationContext)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // CocktailViewModel
        mCocktailViewModel = ViewModelProvider(this)[CocktailViewModel::class.java]
        ingredient?.id?.let {
            mCocktailViewModel.readSelectedCocktails(it).observe(this) { ingredientID ->
                adapter.setData(ingredientID)
            }
        }

        binding.nameInputShow.text = ingredient?.name
        binding.descriptionInputShow.text = ingredient?.description
        binding.checkFavourite.isChecked = ingredient?.is_favourite == true
        if (ingredient?.degree != 0) {
            binding.degreeInputShow.isVisible = true
            binding.degreeInputShow.text = "Крепость: ${ingredient?.degree}°"
        }
        binding.typeInputShow.text = ingredient?.type.toString()

        binding.checkFavourite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                ingredient?.id?.let { mIngredientViewModel.setFavourite(it) }
            } else {
                ingredient?.id?.let { mIngredientViewModel.setUnFavourite(it) }
            }
        }
    }

    private fun initIngredient(): Ingredients?{
        return if (intent.hasExtra(INGREDIENT)){
            intent.getParcelableExtra(INGREDIENT)
        } else {
            val args by navArgs<IngredientShowArgs>()
            args.currentIngredient
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