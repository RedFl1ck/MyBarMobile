package com.example.bottomtest.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomtest.R
import com.example.bottomtest.databinding.FragmentFavouriteBinding
import com.example.bottomtest.databinding.FragmentIngredientsBinding
import com.example.bottomtest.roomdb.adapter.FavouriteListAdapter
import com.example.bottomtest.roomdb.adapter.IngredientsListAdapter
import com.example.bottomtest.roomdb.viewmodel.FavouriteViewModel
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mFavouriteViewModel: FavouriteViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recyclerview
        val adapter = FavouriteListAdapter(this@FavouriteFragment, this.requireContext())
        binding.recyclerView1.adapter = adapter
        binding.recyclerView1.layoutManager = LinearLayoutManager(requireContext())


        // CocktailViewModel
        mFavouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        mFavouriteViewModel.readAllData.observe(viewLifecycleOwner, { cocktail ->
            adapter.setData(cocktail)})

        return view
    }
}