package com.example.bottomtest.ui.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomtest.databinding.FragmentIngredientsBinding
import com.example.bottomtest.roomdb.adapter.CocktailListAdapter
import com.example.bottomtest.roomdb.adapter.IngredientsListAdapter
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel

class IngredientsFragment : Fragment() {

    private var _binding: FragmentIngredientsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mIngredientViewModel: IngredientViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recyclerview
        val adapter = IngredientsListAdapter(this@IngredientsFragment, this.requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // CocktailViewModel
        mIngredientViewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)
        mIngredientViewModel.readAllData.observe(viewLifecycleOwner, { ingredient ->
            adapter.setData(ingredient)})

        return view
    }
}