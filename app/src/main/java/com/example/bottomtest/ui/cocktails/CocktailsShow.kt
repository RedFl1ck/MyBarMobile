package com.example.bottomtest.ui.cocktails

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomtest.R
import com.example.bottomtest.databinding.ActivityShowCocktailsBinding
import com.example.bottomtest.roomdb.adapter.CocktailIngredientListAdapter
import com.example.bottomtest.roomdb.adapter.RecommendedCocktailsListAdapter
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel
import java.io.InputStream


class CocktailsShow : AppCompatActivity() {

    companion object {
        const val COCKTAIL = "cocktail"
    }

    private var cocktail: Cocktail? = null

    private lateinit var binding: ActivityShowCocktailsBinding

    private lateinit var mCocktailViewModel: CocktailViewModel

    private lateinit var mIngredientViewModel: IngredientViewModel

    private var menu: Menu? = null

    private var isFavourite: Boolean = false
    private var isDeleted: Boolean = false
    private var isUpdatable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowCocktailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mCocktailViewModel = CocktailViewModel(application)
        cocktail = initCocktail()
        title = cocktail?.name

        // Recyclerview
        val adapterIngredients = CocktailIngredientListAdapter(this, applicationContext)
        binding.recyclerViewIngredients.adapter = adapterIngredients
        binding.recyclerViewIngredients.layoutManager = LinearLayoutManager(this)

        // IngredientViewModel
        mIngredientViewModel = ViewModelProvider(this)[IngredientViewModel::class.java]
        cocktail?.id?.let {
            mIngredientViewModel.readSelectedIngredients(it).observe(this) { ingredientsList ->
                adapterIngredients.setData(ingredientsList)
            }
        }

        // Recyclerview
        val adapterCocktails = RecommendedCocktailsListAdapter(this, applicationContext)
        binding.recyclerViewCocktails.adapter = adapterCocktails
        val horizontalLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCocktails.layoutManager = horizontalLayoutManager

        // mCocktailViewModel
        mCocktailViewModel = ViewModelProvider(this)[CocktailViewModel::class.java]
        cocktail?.let {
            mCocktailViewModel.getRecommendedCocktails(it.taste, it.id).observe(this) { cocktails ->
                adapterCocktails.setData(cocktails)
            }
        }

        //Show Fragment
        cocktail?.let { showFragmentSet(it) }

        //Update Fragment
        cocktail?.let { updateFragmentSet(it) }
    }

    private fun initCocktail(): Cocktail? {
        return if (intent.hasExtra(COCKTAIL)) {
            intent.getParcelableExtra(COCKTAIL)
        } else {
            val args by navArgs<CocktailsShowArgs>()
            args.currentCocktail
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.change_item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (binding.cocktailShowLinear.isVisible) {
                    finish()
                } else {
                    changeFragments(true)
                }
                true
            }
            R.id.action_change -> {
                changeFragments(false)
                true
            }
            R.id.add_button -> {
                val cocktail = cocktail?.let {
                    Cocktail(
                        it.id,
                        binding.nameInputUpdate.text.toString(),
                        binding.descriptionInputUpdate.text.toString(),
                        binding.degreeInputUpdate.text.toString().toInt(),
                        it.picture,
                        binding.volumeInputUpdate.text.toString().toInt(),
                        binding.receiptInputUpdate.text.toString(),
                        binding.groupInputUpdate.text.toString(),
                        binding.basisIdInputUpdate.text.toString().toInt(),
                        binding.tasteInputUpdate.text.toString(),
                        isUpdatable,
                        isDeleted,
                        binding.checkFavourite.isChecked,
                        0
                    )
                }

                if (cocktail?.let { mCocktailViewModel.inputCheck(it) } == true) {
                    mCocktailViewModel.updateCocktail(cocktail)
                    Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show()
                    showFragmentSet(cocktail)
                    updateFragmentSet(cocktail)
                    changeFragments(true)
                    true
                } else {
                    Toast.makeText(this, "Please fill out all fields.", Toast.LENGTH_SHORT).show()
                    false
                }
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteCocktail() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            //TODO: CHECK FOR CREATED BY USER
            val deletedCocktail = cocktail?.let {
                Cocktail(
                    it.id,
                    it.name,
                    it.description,
                    Integer.parseInt(it.degree.toString()),
                    it.picture,
                    Integer.parseInt(it.volume.toString()),
                    it.receipt,
                    it.cocktail_group,
                    Integer.parseInt(it.basis_id.toString()),
                    it.taste,
                    it.is_updatable,
                    true,
                    binding.checkFavourite.isChecked,
                    it.open_count
                )
            }
            if (deletedCocktail != null) {
                mCocktailViewModel.updateCocktail(deletedCocktail)
            }
            Toast.makeText(
                this,
                "Successfully removed: ${cocktail?.name}",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${cocktail?.name}?")
        builder.setMessage("Are you sure you want to delete ${cocktail?.name}?")
        builder.create().show()
    }

    private fun showFragmentSet(cocktail: Cocktail) {
        mCocktailViewModel.incrementOpenCocktail(cocktail.id)
        binding.groupInputShow.text = cocktail.cocktail_group
        binding.basisIdInputShow.text = cocktail.basis_name
        binding.tasteInputShow.text = cocktail.taste
        binding.nameInputShow.text = cocktail.name
        binding.descriptionInputShow.text = cocktail.description
        binding.degreeInputShow.text = "Крепость: ${cocktail.degree}°"
        binding.volumeInputShow.text = "Объем: ${cocktail.volume} мл"
        isFavourite = cocktail.is_favourite
        isDeleted = cocktail.is_deleted
        isUpdatable = cocktail.is_updatable

        binding.checkFavourite.isChecked = cocktail.is_favourite

        val ims: InputStream = applicationContext.assets.open("Images/${cocktail.picture}")
        // load image as Drawable
        val d = Drawable.createFromStream(ims, null)
        // set image to ImageView
        binding.cocktailImg.setImageDrawable(d)
        ims.close()

        val receiptSteps = cocktail.receipt.split("/||/").toTypedArray()
        var number = 1
        binding.receiptInputShow.text = ""
        receiptSteps.forEach {
            binding.receiptInputShow.append("$number. $it\n")
            number++
        }

        binding.checkFavourite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mCocktailViewModel.setFavourite(cocktail.id)
            } else {
                mCocktailViewModel.setUnFavourite(cocktail.id)
            }
        }
    }

    private fun updateFragmentSet(cocktail: Cocktail) {
        binding.nameInputUpdate.setText(cocktail.name)
        binding.descriptionInputUpdate.setText(cocktail.description)
        binding.degreeInputUpdate.setText(cocktail.degree.toString())
        binding.volumeInputUpdate.setText(cocktail.volume.toString())
        binding.receiptInputUpdate.setText(cocktail.receipt)
        binding.groupInputUpdate.setText(cocktail.cocktail_group)
        binding.basisIdInputUpdate.setText(cocktail.basis_id.toString())
        binding.tasteInputUpdate.setText(cocktail.taste)

        binding.deleteButton.setOnClickListener {
            deleteCocktail()
        }
    }

    private fun changeFragments(state: Boolean) {
        if (state) {
            binding.cocktailShowLinear.isVisible = true
            binding.cocktailsUpdateLinear.isVisible = false
            menu?.clear()
            menuInflater.inflate(R.menu.change_item_menu, menu)
        } else {
            binding.cocktailShowLinear.isVisible = false
            binding.cocktailsUpdateLinear.isVisible = true
            menu?.clear()
            menuInflater.inflate(R.menu.menu_add_new, menu)
        }
    }

}