package com.example.bottomtest.ui.cocktails

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
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

        binding.nameInputShow.text = args.currentCocktail.name
        binding.descriptionInputShow.text = args.currentCocktail.description
        binding.degreeInputShow.text = args.currentCocktail.degree.toString()
        binding.volumeInputShow.text = args.currentCocktail.volume.toString()

        /*var receiptSteps = args.currentCocktail.receipt.split(" ").toTypedArray()
        receiptSteps.forEach{
            binding.receiptInputShow.append(it)
        }
         */
        //binding.receiptInputShow.text = receiptSteps.toString()
        binding.receiptInputShow.text = args.currentCocktail.receipt.replace("\n", "<br/>")
        binding.groupInputShow.text = args.currentCocktail.cocktail_group
        binding.basisIdInputShow.text = args.currentCocktail.basis_id.toString()
        binding.tasteInputShow.text = args.currentCocktail.taste

        binding.editButton.setOnClickListener {

        }
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