package com.example.bottomtest.ui.dashboards

import android.R
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.core.Chart
import com.example.bottomtest.databinding.FragmentDasboardsBinding


class DashboardFragment : Fragment() {

    private var _binding: FragmentDasboardsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val availableCharts = listOf("Dots", "Pie", "Else")

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

        spinner.prompt = "Тип графика"
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireActivity(), R.layout.simple_spinner_item, availableCharts)
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        // Применяем адаптер к элементу spinner
        spinner.adapter = adapter

        APIlib.getInstance().setActiveAnyChartView(anyChartViewDots)
        anyChartViewDots.setChart(GetDotsChart(anyChartViewDots, anyChartViewPie))

        APIlib.getInstance().setActiveAnyChartView(anyChartViewPie)
        anyChartViewPie.setChart(GetPieChart(anyChartViewDots, anyChartViewPie))

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

                    val chart: Chart? = when (item) {
                        "Pie" -> GetPieChart(anyChartViewDots, anyChartViewPie)
                        "Dots" -> GetDotsChart(anyChartViewDots, anyChartViewPie)
                        else -> AnyChart.pie()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        spinner.onItemSelectedListener = itemSelectedListener

        return view
    }

    private fun GetPieChart(anyChartViewDots: AnyChartView, anyChartViewPie: AnyChartView): Chart? {
        val data: List<DataEntry> = listOf(ValueDataEntry("John", 10000),
            ValueDataEntry("Jake", 12000),
            ValueDataEntry("Peter", 18000)
        )
        val chart = AnyChart.pie()
        chart.data(data)
        APIlib.getInstance().setActiveAnyChartView(anyChartViewDots)
        anyChartViewDots.invalidate()
        APIlib.getInstance().setActiveAnyChartView(anyChartViewPie)
        anyChartViewPie.bringToFront()
        return chart;
    }

    private fun GetDotsChart(anyChartViewDots: AnyChartView, anyChartViewPie: AnyChartView): Chart {
        val data: List<DataEntry> = listOf(ValueDataEntry("John", 10000),
            ValueDataEntry("Jake", 12000),
            ValueDataEntry("Peter", 0)
        )
        val chart = AnyChart.column()
        chart.data(data)
        APIlib.getInstance().setActiveAnyChartView(anyChartViewPie)
        anyChartViewPie.invalidate()
        APIlib.getInstance().setActiveAnyChartView(anyChartViewDots)
        anyChartViewDots.bringToFront()
        return chart;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}