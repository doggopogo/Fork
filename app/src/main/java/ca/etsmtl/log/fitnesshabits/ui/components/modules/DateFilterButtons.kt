package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun DateFilterButtons(moduleColor: Color) {
    var currentTimeFrame = remember { mutableStateOf(TimeFrames.WEEK) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (timeFrame in TimeFrames.values()) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = if (currentTimeFrame.value == timeFrame) FontWeight.Bold else FontWeight.Normal,
                            color = if (currentTimeFrame.value == timeFrame) moduleColor else Color.Black
                        )
                    ) {
                        append(timeFrame.text)
                    }
                },
                modifier = Modifier.clickable {
                    currentTimeFrame.value = timeFrame
                }
            )
        }
    }
}

enum class TimeFrames(val text: String) {
    WEEK("Semaine"),
    MONTH("Mois"),
    TRIMESTER("Trimestre"),
    SEMESTER("Semestre"),
    YEAR("Année")
}

