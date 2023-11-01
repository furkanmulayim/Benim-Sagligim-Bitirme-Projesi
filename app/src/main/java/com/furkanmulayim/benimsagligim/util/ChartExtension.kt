package com.furkanmulayim.benimsagligim.util

import android.graphics.Color
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

fun PieChart.fillPieChart(veri1: Float, veri2: Float) {
    // Veri girişleri
    val entries = ArrayList<PieEntry>()
    entries.add(PieEntry(veri1))
    entries.add(PieEntry(veri2))

    // Veri setini
    val dataSet = PieDataSet(entries, "")

    // Renkler
    dataSet.colors = arrayListOf(Color.parseColor("#F58220"), Color.parseColor("#CCCCCC"))

    // Veri setini PieData nesnesine ekledik
    val data = PieData(dataSet)

// PieData nesnesini PieChart'a ekledik
    this.data = data
    this.description.isEnabled = false
    data.setDrawValues(false)
    this.legend.isEnabled = false
    this.animateY(1400)

    // Grafiği çizdirdik
    this.invalidate()
}
