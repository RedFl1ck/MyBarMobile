package com.example.bottomtest.ui.cocktails

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.navArgs
import com.example.bottomtest.R
import com.example.bottomtest.databinding.ActivityShowCocktailsBinding
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel
import java.io.InputStream


class CocktailsShow : AppCompatActivity() {

    private lateinit var binding: ActivityShowCocktailsBinding

    private val args by navArgs<CocktailsShowArgs>()

    private lateinit var mCocktailViewModel: CocktailViewModel

    private var menu : Menu? = null

    private var isFavourite: Boolean = false
    private var isDeleted: Boolean = false
    private var isUpdatable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowCocktailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mCocktailViewModel = CocktailViewModel(application)

        //Show Fragment
        showFragmentSet(args.currentCocktail)

        //Update Fragment
        updateFragmentSet(args.currentCocktail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu
        menuInflater.inflate(R.menu.change_item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (binding.cocktailShowLinear.isVisible){
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
                val cocktail = Cocktail(args.currentCocktail.id,
                    binding.nameInputUpdate.text.toString(),
                    binding.descriptionInputUpdate.text.toString(),
                    binding.degreeInputUpdate.text.toString().toInt(),
                    args.currentCocktail.picture,
                    binding.volumeInputUpdate.text.toString().toInt(),
                    binding.receiptInputUpdate.text.toString(),
                    binding.groupInputUpdate.text.toString(),
                    binding.basisIdInputUpdate.text.toString().toInt(),
                    binding.tasteInputUpdate.text.toString(),
                    isUpdatable,
                    isDeleted,
                    binding.checkFavourite.isChecked)

                if(mCocktailViewModel.inputCheck(cocktail)){
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
            val deletedCocktail = Cocktail(args.currentCocktail.id,
                args.currentCocktail.name,
                args.currentCocktail.description,
                Integer.parseInt(args.currentCocktail.degree.toString()),
                args.currentCocktail.picture,
                Integer.parseInt(args.currentCocktail.volume.toString()),
                args.currentCocktail.receipt,
                args.currentCocktail.cocktail_group,
                Integer.parseInt(args.currentCocktail.basis_id.toString()),
                args.currentCocktail.taste,
                args.currentCocktail.is_updatable,
                true,
                binding.checkFavourite.isChecked)
            mCocktailViewModel.updateCocktail(deletedCocktail)
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

    private fun showFragmentSet(cocktail: Cocktail) {
        binding.groupInputShow.text = cocktail.cocktail_group
        binding.basisIdInputShow.text = cocktail.basis_id.toString()
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
        receiptSteps.forEach{
            binding.receiptInputShow.append("$number. $it\n")
            number++
        }

        binding.checkFavourite.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                mCocktailViewModel.setFavourite(args.currentCocktail.id)
            }
            else {
                mCocktailViewModel.setUnFavourite(args.currentCocktail.id)
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