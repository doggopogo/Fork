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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DateFilterButtons() {
    var currentTimeFrame = remember { mutableStateOf(TimeFrames.WEEK) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (timeFrame in TimeFrames.values()) {
            Text(
                text = timeFrame.text,
                fontWeight =
                if (currentTimeFrame.value == timeFrame) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
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
