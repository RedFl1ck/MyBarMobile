package com.example.bottomtest.ui.shopping_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomtest.databinding.FragmentShoppingBinding
import com.example.bottomtest.roomdb.adapter.ShoppingListAdapter
import com.example.bottomtest.roomdb.viewmodel.ShoppingListViewModel

class ShoppingFragment : Fragment() {

    private var _binding: FragmentShoppingBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mShoppingViewModel: ShoppingListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        val view = binding.root

        // Recyclerview
        val adapter = ShoppingListAdapter(this@ShoppingFragment, this.requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CocktailViewModel
        mShoppingViewModel = ViewModelProvider(this).get(ShoppingListViewModel::class.java)
        mShoppingViewModel.readAllData.observe(viewLifecycleOwner, { ingredient ->
            adapter.setData(ingredient)})

        return view
    }
}