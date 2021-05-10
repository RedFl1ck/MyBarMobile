package com.example.bottomtest.ui.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.databinding.FragmentIngredientsBinding
import com.example.bottomtest.roomdb.adapter.IngredientsListAdapter
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val bottomNav = activity?.findViewById<BottomNavigationView?>(R.id.nav_view)
                if (dy > 0) {
                    activity?.actionBar?.hide()//Scrolling down
                    (binding.recyclerView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(0, 169, 0, 0)
                    bottomNav?.isVisible = false
                    binding.fabIngredients.isVisible = false
                } else if (dy < 0) {
                    activity?.actionBar?.hide()//Scrolling up
                    bottomNav?.isVisible = true
                    binding.fabIngredients.isVisible = true
                }
            }
        })


        return view
    }
}