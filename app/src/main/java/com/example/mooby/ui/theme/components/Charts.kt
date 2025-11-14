package com.example.mooby.ui.theme.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SimpleBarChart(modifier: Modifier = Modifier, data: List<Float>, labels: List<String>) {
    val max = (data.maxOrNull() ?: 1f).coerceAtLeast(1f)
    Box(modifier = modifier.height(220.dp).padding(8.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val barWidth = w / (data.size * 2f)

            data.forEachIndexed { i, v ->
                val left = i * 2f * barWidth + barWidth * 0.5f
                val right = left + barWidth
                val top = h - (v / max) * h
                drawRect(color = Color(0xFF00695C), topLeft = Offset(left, top), size = androidx.compose.ui.geometry.Size(barWidth, h - top))
            }
        }
    }
}
