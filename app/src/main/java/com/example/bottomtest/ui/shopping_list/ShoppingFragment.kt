package com.example.bottomtest.ui.shopping_list

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.databinding.FragmentShoppingBinding
import com.example.bottomtest.roomdb.adapter.ShoppingListAdapter
import com.example.bottomtest.roomdb.viewmodel.ShoppingListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class ShoppingFragment : Fragment() {

    private var _binding: FragmentShoppingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mShoppingViewModel: ShoppingListViewModel

    private lateinit var adapter: ShoppingListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)

        // Recyclerview
        adapter = ShoppingListAdapter(this.requireActivity(), this.requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CocktailViewModel
        mShoppingViewModel = ViewModelProvider(this)[ShoppingListViewModel::class.java]
        mShoppingViewModel.readAllData.observe(viewLifecycleOwner) { ingredient ->
            adapter.setData(ingredient)
        }
        setOnScrollListener()

        return view
    }

    private fun setOnScrollListener() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                (binding.recyclerView.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin =
                    activity?.findViewById<BottomNavigationView?>(R.id.nav_view)?.height ?: 0
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    mShoppingViewModel.search(query, viewLifecycleOwner, adapter)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    mShoppingViewModel.search(newText, viewLifecycleOwner, adapter)
                }
                return true
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}