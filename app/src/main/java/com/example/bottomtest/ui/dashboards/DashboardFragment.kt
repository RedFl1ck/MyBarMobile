package com.example.bottomtest.ui.dashboards

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.bottomtest.databinding.FragmentDasboardsBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDasboardsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDasboardsBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)

        val pie = AnyChart.pie()

        val data: MutableList<DataEntry> = ArrayList()
        data.add(ValueDataEntry("John", 10000))
        data.add(ValueDataEntry("Jake", 12000))
        data.add(ValueDataEntry("Peter", 18000))

        pie.data(data)

        val anyChartView = binding.anyChartView
        anyChartView.setChart(pie)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}