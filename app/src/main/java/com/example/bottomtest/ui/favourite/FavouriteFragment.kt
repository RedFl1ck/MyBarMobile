package com.example.bottomtest.ui.favourite

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomtest.R
import com.example.bottomtest.databinding.FragmentFavouriteBinding
import com.example.bottomtest.roomdb.adapter.FavouriteListAdapter
import com.example.bottomtest.roomdb.viewmodel.FavouriteViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class FavouriteFragment : Fragment() {

    private var _binding: FragmentFavouriteBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mFavouriteViewModel: FavouriteViewModel

    private lateinit var adapter: FavouriteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)

        // Recyclerview
        adapter = FavouriteListAdapter(this.requireActivity(), this.requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // CocktailViewModel
        mFavouriteViewModel = ViewModelProvider(this)[FavouriteViewModel::class.java]
        mFavouriteViewModel.readCocktailsData.observe(viewLifecycleOwner) { cocktail ->
            adapter.setData(cocktail)
        }
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val bottomNav = activity?.findViewById<BottomNavigationView?>(R.id.nav_view)
                if (dy > 0) {
                    activity?.actionBar?.hide()//Scrolling down
                    (binding.recyclerView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
                        0,
                        169,
                        0,
                        0
                    )
                    bottomNav?.isVisible = false
                } else if (dy < 0) {
                    activity?.actionBar?.hide()//Scrolling up
                    bottomNav?.isVisible = true
                }
            }
        })

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    mFavouriteViewModel.search(query, viewLifecycleOwner, adapter)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    mFavouriteViewModel.search(newText, viewLifecycleOwner, adapter)
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