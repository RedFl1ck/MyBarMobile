package com.example.bottomtest.ui.dashboards

import android.R.attr.data
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bottomtest.databinding.FragmentDasboardsBinding
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DashboardsFragment : Fragment() {
    private var _binding: FragmentDasboardsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mIngredientViewModel: IngredientViewModel

    // ToDo вынести в ресурсы
    private val availableCharts = listOf( "Pie", "Line" )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDasboardsBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)

        mIngredientViewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)

        val spinner = binding.chartTypeSpinner
        spinner.prompt = "Тип графика"

        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), android.R.layout.simple_spinner_item, availableCharts)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val lineChart = binding.lineChart
        val pieChart = binding.pieChart

        lifecycleScope.launch(Dispatchers.IO) {
            val data = GetDataForPieChart()
            val dataSet = PieDataSet(data, "Pie график")
            pieChart.data = PieData(dataSet)
            launch(Dispatchers.Main) {
                pieChart.invalidate()
                pieChart.isVisible = true
                lineChart.isVisible = false
            }
        }

        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Получаем выбранный объект
                    val item = parent.getItemAtPosition(position) as String

                    lifecycleScope.launch(Dispatchers.IO) {
                        val chart =
                            when (item) {
                                "Pie" -> MakePieChart(pieChart, lineChart)
                                "Line" -> MakeLineChart(pieChart, lineChart)
                                else -> null
                            }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        spinner.onItemSelectedListener = itemSelectedListener

        return view
    }

    private suspend fun MakePieChart(pieChart: PieChart, lineChart: LineChart){
        val data = GetDataForPieChart()
        val dataSet = PieDataSet(data, "")
        dataSet.colors = mutableListOf(Color.GRAY, Color.CYAN, Color.LTGRAY, Color.MAGENTA)

        pieChart.data = PieData(dataSet)
        pieChart.description.isEnabled = false
        pieChart.legend.isEnabled = false
        pieChart.maxVisibleCount
        pieChart.setNoDataText("Недостатчно данных!")
        pieChart.setEntryLabelColor(Color.BLACK)
        lifecycleScope.launch(Dispatchers.Main) {
            pieChart.invalidate()
            pieChart.isVisible = true
            lineChart.isVisible = false
        }
    }

    private suspend fun GetDataForPieChart(): MutableList<PieEntry> {
        val data = mIngredientViewModel.getDataForSoppingChart(null)
        val res = mutableListOf<PieEntry>()
        for (item in data) {
            val entry = PieEntry(item.shopping_count.toFloat(), item.name)
            res.add(entry)
        }
        return res
    }

    // ToDo сделать
    private suspend fun MakeLineChart(pieChart: PieChart, lineChart: LineChart){
        val data = GetDataForLineChart()
        val dataSet = LineDataSet(data, "Line график")
        lineChart.data = LineData(dataSet)
        lifecycleScope.launch(Dispatchers.Main) {
            lineChart.invalidate()
            lineChart.isVisible = true
            pieChart.isVisible = false
        }
    }
    
    // ToDo сделать
    private suspend fun GetDataForLineChart(): MutableList<Entry> {
        val data = mIngredientViewModel.getDataForSoppingChart(null)
        val res = mutableListOf<Entry>()
        for (item in data) {
            res.add(Entry(item.shopping_count.toFloat(), 0F))
        }
        return res
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}