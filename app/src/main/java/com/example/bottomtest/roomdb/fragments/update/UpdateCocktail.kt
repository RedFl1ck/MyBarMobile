package com.example.bottomtest.roomdb.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.model.Cocktail
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel
import kotlinx.android.synthetic.main.activity_update.*


class UpdateCocktail : AppCompatActivity() {


    private val args by navArgs<UpdateCocktailArgs>()

    private lateinit var mCocktailViewModel: CocktailViewModel
    private var delete_button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        delete_button = findViewById(R.id.delete_button)


        delete_button?.setOnClickListener { deleteUser() }

        mCocktailViewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)

        /*title_input2.setText(args.currentCocktail.firstName)
        author_input2.setText(args.currentCocktail.lastName)
        pages_input2.setText(args.currentCocktail.age.toString())*/

    }


    private fun updateItem(fN: String, lN: String, ag: Editable) {

        // Create Cocktail Object
        //val updatedCocktail = Cocktail(args.currentCocktail.id, fN, lN, Integer.parseInt(ag.toString()))
        // Update Current User
        //mCocktailViewModel.updateCocktail(updatedCocktail)
        Toast.makeText(this, "Updated Successfully!", Toast.LENGTH_SHORT).show()

    }

    private fun deleteUser() {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            mCocktailViewModel.deleteCocktail(args.currentCocktail)
            /*Toast.makeText(
                this,
                "Successfully removed: ${args.currentCocktail.firstName}",
                Toast.LENGTH_SHORT).show()*/
            finish()
        }
        builder.setNegativeButton("No") { _, _ -> }
        //builder.setTitle("Delete ${args.currentCocktail.firstName}?")
        //builder.setMessage("Are you sure you want to delete ${args.currentCocktail.firstName}?")
        builder.create().show()
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable?): Boolean {
        return if (age != null) {
            !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
        }
        else{
            false
        }
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
                val firstName = title_input2.text.toString()
                val lastName = author_input2.text.toString()
                val age = pages_input2.text

                if(inputCheck(firstName, lastName, age)){
                    updateItem(firstName, lastName, age)
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