package com.example.bottomtest.ui.cocktails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomtest.R
import com.example.bottomtest.databinding.FragmentCocktailsBinding
import com.example.bottomtest.roomdb.adapter.CocktailListAdapter
import com.example.bottomtest.roomdb.fragments.add.AddNewCocktail
import com.example.bottomtest.roomdb.viewmodel.CocktailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import java.util.*


class CocktailsFragment : Fragment() {

    private var _binding: FragmentCocktailsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mCocktailViewModel: CocktailViewModel
    
    private var chip1 = false
    private var chip2 = false
    private var chip3 = false
    private var chip4 = false
    private var chip2_1 = false
    private var chip2_2 = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailsBinding.inflate(inflater, container, false)
        val view = binding.root


        binding.fabCocktails.setOnClickListener {
            startActivity(Intent(activity, AddNewCocktail::class.java))
        }

        //empty_imageview = view.findViewById(R.id.empty_imageview)
        //no_data = root.findViewById(R.id.no_data)
        //filter_button = root.findViewById(R.id.filter_cocktails_button)
        //sort_button = root.findViewById(R.id.sort_cocktails_button)


        // Recyclerview
        val adapter = CocktailListAdapter(this@CocktailsFragment, this.requireContext())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // CocktailViewModel
        mCocktailViewModel = ViewModelProvider(this).get(CocktailViewModel::class.java)
        mCocktailViewModel.readNotDeletedData.observe(viewLifecycleOwner, { cocktail ->
            adapter.setData(cocktail)
        })


        val parent = view.findViewById<ViewGroup>(R.id.container_cocktail)
        //SHEET FILTER
        val btnsheetfilter = layoutInflater.inflate(R.layout.sheet_filter_cocktails, parent, false)

        binding.filterCocktailsButton.setOnClickListener{
            //TODO: add filter sheet logic
            val bottomSheetDialog =  BottomSheetDialog(this.requireContext())
            bottomSheetDialog.setContentView(btnsheetfilter)
            val bottomSheetView = LayoutInflater.from(this.requireContext()).inflate(R.layout.sheet_filter_cocktails, view.findViewById(R.id.cocktails_filter_sheet))
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()

            if (chip1) bottomSheetView.findViewById<Chip>(R.id.chip_1).isChecked = true
            if (chip2) bottomSheetView.findViewById<Chip>(R.id.chip_2).isChecked = true
            if (chip3) bottomSheetView.findViewById<Chip>(R.id.chip_3).isChecked = true
            if (chip4) bottomSheetView.findViewById<Chip>(R.id.chip_4).isChecked = true
            if (chip2_1) bottomSheetView.findViewById<Chip>(R.id.chip2_1).isChecked = true
            if (chip2_2) bottomSheetView.findViewById<Chip>(R.id.chip2_2).isChecked = true
            //bottomSheetView.findViewById<Chip>(R.id.chip_1).onRestoreInstanceState(1)

            bottomSheetView.findViewById<Chip>(R.id.chip_1).setOnCheckedChangeListener {
                compoundButton: CompoundButton, isChecked: Boolean ->
                //bottomSheetView.findViewById<Chip>(R.id.chip_1).onSaveInstanceState()
                chip1 = if (isChecked){
                    Toast.makeText(this.requireContext(), "you checked filter 1", Toast.LENGTH_SHORT).show()
                    true
                } else {
                    Toast.makeText(this.requireContext(), "you unchecked filter 1", Toast.LENGTH_SHORT).show()
                    false
                }
            }

            bottomSheetView.findViewById<Chip>(R.id.chip_2).setOnCheckedChangeListener { compoundButton: CompoundButton, isChecked: Boolean ->
                chip2 = if (isChecked){
                    Toast.makeText(this.requireContext(), "you checked filter 2", Toast.LENGTH_SHORT).show()
                    true
                } else {
                    Toast.makeText(this.requireContext(), "you unchecked filter 2", Toast.LENGTH_SHORT).show()
                    false
                }
            }

            bottomSheetView.findViewById<Chip>(R.id.chip_3).setOnCheckedChangeListener { compoundButton: CompoundButton, isChecked: Boolean ->
                chip3 = isChecked
            }

            bottomSheetView.findViewById<Chip>(R.id.chip_4).setOnCheckedChangeListener { compoundButton: CompoundButton, isChecked: Boolean ->
                chip4 = isChecked
            }

            bottomSheetView.findViewById<Chip>(R.id.chip2_1).setOnCheckedChangeListener { compoundButton: CompoundButton, isChecked: Boolean ->
                chip2_1 = isChecked
            }

            bottomSheetView.findViewById<Chip>(R.id.chip2_2).setOnCheckedChangeListener { compoundButton: CompoundButton, isChecked: Boolean ->
                chip2_2 = isChecked
            }

            bottomSheetView.findViewById<Button>(R.id.filter_sheet_done).setOnClickListener {
                bottomSheetDialog.dismiss()
            }


        }

        //SHEET SORT
        val btnsheetsort = layoutInflater.inflate(R.layout.sheet_sort_cocktails, parent, false)

        binding.sortCocktailsButton.setOnClickListener{
            //TODO: add sort sheet logic
            val bottomSheetDialog =  BottomSheetDialog(this.requireContext())
            bottomSheetDialog.setContentView(btnsheetsort)
            val bottomSheetView = LayoutInflater.from(this.requireContext()).inflate(R.layout.sheet_sort_cocktails, view.findViewById(R.id.cocktails_sort_sheet))
            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()

            bottomSheetView.findViewById<Button>(R.id.sort_sheet_done).setOnClickListener {
                bottomSheetDialog.dismiss()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}