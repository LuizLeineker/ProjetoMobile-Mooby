package com.example.mooby.ui.theme.components

import android.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.*

@Composable
fun LineChartCompose(
    values: List<Float>,
    labels: List<String>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                setTouchEnabled(true)
                setPinchZoom(true)
            }
        },
        update = { chart ->
            val entries = values.mapIndexed { i, v ->
                Entry(i.toFloat(), v)
            }

            val dataSet = LineDataSet(entries, "Gastos").apply {
                color = Color.BLUE
                valueTextColor = Color.BLACK
                lineWidth = 3f
                circleRadius = 4f
                setCircleColor(Color.BLUE)
            }

            chart.data = LineData(dataSet)
            chart.invalidate()
        }
    )
}
