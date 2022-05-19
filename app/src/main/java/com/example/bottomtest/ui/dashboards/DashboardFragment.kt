package com.example.bottomtest.ui.dashboards

import android.R
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.Chart
import com.anychart.enums.Align
import com.anychart.enums.LegendLayout
import com.example.bottomtest.databinding.FragmentDasboardsBinding
import com.example.bottomtest.roomdb.model.ShoppingChartItem
import com.example.bottomtest.roomdb.viewmodel.IngredientViewModel
import kotlinx.coroutines.*


class DashboardFragment : Fragment() {

    private var _binding: FragmentDasboardsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private lateinit var mIngredientViewModel: IngredientViewModel

    private val availableCharts = listOf( "Dots", "Pie" )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDasboardsBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)
        val anyChartViewDots = binding.anyChartViewDots
        val anyChartViewPie = binding.anyChartViewPie
        val spinner = binding.chartTypeSpinner

        mIngredientViewModel = ViewModelProvider(this).get(IngredientViewModel::class.java)

        spinner.prompt = "Тип графика"
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), R.layout.simple_spinner_item, availableCharts)
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        // Применяем адаптер к элементу spinner
        spinner.adapter = adapter

        /*lifecycleScope.launch(Dispatchers.IO) {
            val chartPie = GetPieChart(anyChartViewDots, anyChartViewPie)
            //val chartDots = GetDotsChart(anyChartViewDots, anyChartViewPie)
            launch(Dispatchers.Main) {
                APIlib.getInstance().setActiveAnyChartView(anyChartViewPie)
                anyChartViewPie.setChart(chartPie)
                /*APIlib.getInstance().setActiveAnyChartView(anyChartViewDots)
                anyChartViewDots.setChart(chartDots)
                anyChartViewDots.invalidate()
                anyChartViewDots.bringToFront()*/
            }
        }*/

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
                                "Pie" -> GetPieChart(anyChartViewDots, anyChartViewPie)
                                "Dots" -> GetDotsChart(anyChartViewDots, anyChartViewPie)
                                else -> null
                            }
                        /*lifecycleScope.launch(Dispatchers.Main) {
                            when (item) {
                                "Pie" -> BringPieToFront(anyChartViewDots, anyChartViewPie, chart)
                                "Dots" -> BringDotsToFront(anyChartViewDots, anyChartViewPie, chart)
                                else -> null
                            }
                        }*/
                    }


                    /*when (item) {
                        "Pie" -> APIlib.getInstance()
                            .setActiveAnyChartView(anyChartViewPie) //BringPieToFront(anyChartViewDots, anyChartViewPie, chart)
                        "Dots" -> APIlib.getInstance()
                            .setActiveAnyChartView(anyChartViewDots) //BringDotsToFront(anyChartViewDots, anyChartViewPie, chart)
                        else -> null
                    }*/
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        spinner.onItemSelectedListener = itemSelectedListener

        return view
    }

    private suspend fun GetPieChart(anyChartViewDots: AnyChartView, anyChartViewPie: AnyChartView): Chart? {
        val chart = AnyChart.pie()

        val data = GetDataForChart()
        chart.data(data)

        chart.title("Количество закупок по ингредиентам")
        chart.labels().position("outside")

        chart.legend()
            .position("center-bottom")
            .itemsLayout(LegendLayout.HORIZONTAL_EXPANDABLE)
            .align(Align.CENTER);

        lifecycleScope.launch(Dispatchers.Main) {
            /*APIlib.getInstance().setActiveAnyChartView(anyChartViewDots)
            anyChartViewDots.invalidate()*/
            APIlib.getInstance().setActiveAnyChartView(anyChartViewPie)
            anyChartViewPie.setChart(chart)
            anyChartViewPie.invalidate()
            anyChartViewPie.bringToFront()
        }

        return chart;
    }

    private fun BringPieToFront(
        anyChartViewDots: AnyChartView,
        anyChartViewPie: AnyChartView,
        chart: Chart?
    ){
        //APIlib.getInstance().setActiveAnyChartView(anyChartViewDots)
        //anyChartViewDots.invalidate()
        APIlib.getInstance().setActiveAnyChartView(anyChartViewPie)
        anyChartViewPie.setChart(chart)
        anyChartViewPie.invalidate()
        anyChartViewPie.bringToFront()

    }

    private suspend fun GetDotsChart(anyChartViewDots: AnyChartView, anyChartViewPie: AnyChartView): Chart {
        val data: List<DataEntry> = listOf(ValueDataEntry("John", 10000),
            ValueDataEntry("Jake", 12000),
            ValueDataEntry("Peter", 0)
        )
        //val data = GetDataForChart()
        val chart = AnyChart.column()
        chart.data(data)

        lifecycleScope.launch(Dispatchers.Main) {
            /*APIlib.getInstance().setActiveAnyChartView(anyChartViewPie)
            anyChartViewPie.invalidate()*/
            APIlib.getInstance().setActiveAnyChartView(anyChartViewDots)
            anyChartViewDots.setChart(chart)
            anyChartViewDots.bringToFront()
        }

        return chart;
    }

    private fun BringDotsToFront(
        anyChartViewDots: AnyChartView,
        anyChartViewPie: AnyChartView,
        chart: Chart?
    ){
        //APIlib.getInstance().setActiveAnyChartView(anyChartViewPie)
        //anyChartViewPie.invalidate()
        APIlib.getInstance().setActiveAnyChartView(anyChartViewDots)
        anyChartViewDots.setChart(chart)
        anyChartViewDots.invalidate()
        //anyChartViewDots.bringToFront()
    }

    private suspend fun GetDataForChart(): List<DataEntry> {
        val resultData = mutableListOf<DataEntry>()
        val data: List<ShoppingChartItem> = mIngredientViewModel.getDataForSoppingChart(null)

        for (elem in data) {
            resultData.add(ValueDataEntry(elem.name, elem.shopping_count))
        }

        return resultData
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.example.bottomtest.R.menu.main, menu)

        menu.findItem(com.example.bottomtest.R.id.action_search).isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}