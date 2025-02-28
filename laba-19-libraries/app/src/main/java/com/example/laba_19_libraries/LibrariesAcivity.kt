package com.example.laba_19_libraries

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet



class LibrariesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lineChart = LineChart(this)
        setContentView(lineChart)

        val entries = mutableListOf<Entry>()
        for (i in 0..10) {
            entries.add(Entry(i.toFloat(), (Math.random() * 10).toFloat()))
        }

        val dataSet = LineDataSet(entries, "Random Data").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
            lineWidth = 2f
        }

        lineChart.data = LineData(dataSet)
        lineChart.description = Description().apply { text = "График случайных данных" }
        lineChart.invalidate() // Обновляем график
    }
}
