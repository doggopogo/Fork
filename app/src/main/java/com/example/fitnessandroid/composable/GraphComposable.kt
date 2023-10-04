package com.example.fitnessandroid.composable

import android.graphics.Paint
import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessandroid.ui.theme.FitnessAndroidTheme
import kotlin.math.ceil

data class GraphComposableUiState(
    val xLabelValues: List<Int>,
    val points: List<Float>,
    val paddingSpace: Dp = 16.dp,
    val limitLines: List<Float> = emptyList(),
    val color: Color = Color(0xFF24a788)
)

@Composable
fun GraphComposable(
    modifier: Modifier = Modifier,
    uiState: GraphComposableUiState,
) {
    val controlPoints1 = mutableListOf<PointF>()
    val controlPoints2 = mutableListOf<PointF>()
    val coordinates = mutableListOf<PointF>()

    val miny: Int = uiState.points.minOrNull()?.toInt() ?: 0
    val maxy: Int = uiState.points.maxOrNull()?.toInt() ?: 1
    val delta = maxy - miny
    val verticalStep: Float = if (delta >= 6) ceil(maxy.toFloat() / 6) else 1f

    val density = LocalDensity.current
    val textPaint = remember(density) {
        Paint().apply {
            color = android.graphics.Color.BLACK
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }

    Box(
        modifier = modifier
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 12.dp),
        contentAlignment = Center
    ) {
        if (uiState.points.isNotEmpty() && uiState.xLabelValues.isNotEmpty()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val xAxisSpace = (size.width - uiState.paddingSpace.toPx()) / uiState.xLabelValues.size
                val yAxisSpace = (size.height / verticalStep) / 6
                /** placing x axis points */
                controlPoints1.clear()
                controlPoints2.clear()
                coordinates.clear()
                for (i in uiState.xLabelValues.indices) {
                    drawContext.canvas.nativeCanvas.drawText(
                        "${uiState.xLabelValues[i]}",
                        xAxisSpace * (i + 1),
                        size.height - 30,
                        textPaint
                    )
                }
                /** placing y axis labels points */
                val yAxisLabel = mutableListOf<Int>().apply {
                    add(miny)
                    addAll(miny..maxy step verticalStep.toInt())
                    if (!contains(maxy)) {
                        add(maxy)
                    }
                    if(last() == miny) {
                        add(miny+1)
                    }
                }
                for (i in yAxisLabel) {
                    drawContext.canvas.nativeCanvas.drawText(
                        i.toString(),
                        uiState.paddingSpace.toPx() / 2f,
                        size.height - yAxisSpace * (i + 1),
                        textPaint
                    )
                }
                /** placing our x axis points */
                for (i in uiState.points.indices) {
                    val x1 = xAxisSpace * (i+1)
                    val y1 = size.height - (yAxisSpace * (uiState.points[i])) - yAxisSpace
                    coordinates.add(PointF(x1, y1))
                    /** drawing circles to indicate all the points */
                    drawCircle(
                        color = Color.Red,
                        radius = 10f,
                        center = Offset(x1, y1)
                    )
                }
                /** calculating the connection points */
                for (i in 1 until coordinates.size) {
                    controlPoints1.add(
                        PointF(
                            (coordinates[i].x + coordinates[i - 1].x) / 2,
                            coordinates[i - 1].y
                        )
                    )
                    controlPoints2.add(
                        PointF(
                            (coordinates[i].x + coordinates[i - 1].x) / 2,
                            coordinates[i].y
                        )
                    )
                }

                /** drawing the path */
                val stroke = Path().apply {
                    reset()
                    moveTo(coordinates.first().x, coordinates.first().y)
                    for (i in 0 until coordinates.size - 1) {
                        cubicTo(
                            controlPoints1[i].x, controlPoints1[i].y,
                            controlPoints2[i].x, controlPoints2[i].y,
                            coordinates[i + 1].x, coordinates[i + 1].y
                        )
                    }
                }

                /** filling the area under the path */
                val fillPath = android.graphics.Path(stroke.asAndroidPath())
                    .asComposePath()
                    .apply {
                        lineTo(xAxisSpace * uiState.xLabelValues.last(), size.height - yAxisSpace)
                        lineTo(xAxisSpace, size.height - yAxisSpace)
                        close()
                    }
                drawPath(
                    fillPath,
                    brush = Brush.verticalGradient(
                        listOf(
                            uiState.color,
                            Color.Transparent,
                        ),
                        endY = size.height - yAxisSpace
                    ),
                )
                /*Stroke Graph with Black*/
                drawPath(
                    stroke,
                    color = Color.Black,
                    style = Stroke(
                        width = 5f,
                        cap = StrokeCap.Square
                    )
                )
                uiState.limitLines.forEach { limitLine ->
                    drawLine(
                        start = Offset(x = 0f, y = size.height - yAxisSpace * (limitLine + 1)),
                        end = Offset(
                            x = size.width,
                            y = size.height - yAxisSpace * (limitLine + 1)
                        ),
                        color = Color.Red,
                        strokeWidth = 5F,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                }
            }
        } else {
            Text(
                text = "Aucune information à afficher",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun GraphPreview() {
    FitnessAndroidTheme {
        val points = listOf(0F, 0F, 0F, 0F, 0F, 0F, 0.7F)
        GraphComposable(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            uiState = GraphComposableUiState(
                xLabelValues = (0..20 step 3).map { it + 1 },
                points = points,
                paddingSpace = 16.dp,
                limitLines = listOf(11f, 15f)
            ),
        )
    }
}
