package com.example.bottomtest.ui.cocktails

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.example.bottomtest.R
import com.example.bottomtest.databinding.ActivityShowCocktailsBinding
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel

class CocktailsShow : AppCompatActivity() {

    private lateinit var binding: ActivityShowCocktailsBinding

    private val args by navArgs<CocktailsShowArgs>()

    private lateinit var mCocktailViewModel: CocktailViewModel

    //private var receiptSteps = arrayListOf<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowCocktailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mCocktailViewModel = CocktailViewModel(application)

        binding.nameInputShow.text = args.currentCocktail.name
        binding.descriptionInputShow.text = args.currentCocktail.description
        binding.degreeInputShow.text = "Крепость: ${args.currentCocktail.degree}°"
        binding.volumeInputShow.text = "Объем: ${args.currentCocktail.volume} мл"

        val receiptSteps = args.currentCocktail.receipt.split("/||/").toTypedArray()
        var number = 1
        receiptSteps.forEach{
            binding.receiptInputShow.append("$number. $it\n")
            number++
        }
        binding.groupInputShow.text = args.currentCocktail.cocktail_group
        binding.basisIdInputShow.text = args.currentCocktail.basis_id.toString()
        binding.tasteInputShow.text = args.currentCocktail.taste
        binding.checkFavourite.isPressed = args.currentCocktail.is_favourite
        binding.checkFavourite.isChecked = args.currentCocktail.is_favourite

        binding.checkFavourite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mCocktailViewModel.setFavourite(args.currentCocktail.id)
            }
            else {
                mCocktailViewModel.setUnfavourite(args.currentCocktail.id)
            }
        }

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
