package com.example.bottomtest.roomdb.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import com.example.bottomtest.R
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*


class UpdateCocktail : AppCompatActivity() {

    var editTitle: EditText? = null
    var editAuthor: EditText? = null
    var editPages: EditText? = null

    //private val args by navArgs<UpdateCocktailArgs>()

    private lateinit var mCocktailViewModel: CocktailViewModel
    private var delete_button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        editTitle = findViewById(R.id.title_input2)
        editAuthor = findViewById(R.id.author_input2)
        editPages = findViewById(R.id.pages_input2)
        delete_button = findViewById(R.id.delete_button)


        //TODO: UPDATE STUPID ITEMS
        //delete_button?.setOnClickListener { deleteUser() }

        //val view = inflater.inflate(R.layout.activity_update, container_cocktail, false)

        mCocktailViewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)

        /*view.updateFirstName_et.setText(args.currentUser.firstName)
        view.updateLastName_et.setText(args.currentUser.lastName)
        view.updateAge_et.setText(args.currentUser.age.toString())*/

    }


    /*private fun updateItem(fN: String, lN: String, ag: Editable) {

        // Create Cocktail Object
        val updatedCocktail = Cocktail(args.currentUser.id, firstName, lastName, age)
        // Update Current User
        mUserViewModel.updateUser(updatedUser)
        Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
        // Navigate Back
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)

    }

    private fun deleteUser() {
        val builder = android.app.AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }*/

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
                val firstName = editTitle?.text.toString()
                val lastName = editAuthor?.text.toString()
                val age = editPages?.text

                if(inputCheck(firstName, lastName, age)){
                    //updateItem(firstName, lastName, age)
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