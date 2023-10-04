package com.example.fitnessandroid.composable.selector

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.common.PreviewComposable
import com.example.fitnessandroid.common.config.PERIOD_OF_TIME

@Composable
fun SelectorButton(
    modifier: Modifier = Modifier,
    options: List<String>,
    color : Color = Color(0xFF51A489),
    onOptionSelected: (String) -> Unit
) {
    var selectedValue by remember { mutableStateOf(options.first()) }
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
        columns = GridCells.Fixed(3)
    ) {
        items(options) { option ->
            Button(
                modifier = Modifier.padding(2.dp),
                onClick = {
                    onOptionSelected(option)
                    selectedValue = option
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (selectedValue == option) color else Color.White
                )
            ) {
                Text(option, color = if (selectedValue == option) Color.White else Color.Black)
            }
            Spacer(Modifier.width(16.dp))
        }
    }
}

@Composable
fun SelectorPeriodOfTime(
    modifier: Modifier = Modifier,
    color : Color = Color(0xFF51A489),
    onOptionSelected: (Int) -> Unit
) {
    val periodOfTime = listOf("Semaine", "Mois", "Trimestre", "Semestre", "Année")
    SelectorButton(modifier = modifier, options = periodOfTime, color = color) { option ->
        onOptionSelected(
            when (option) {
                "Semaine" -> PERIOD_OF_TIME.WEEK
                "Mois" -> PERIOD_OF_TIME.MONTH
                "Trimestre" -> PERIOD_OF_TIME.QUARTER
                "Semestre" -> PERIOD_OF_TIME.SEMESTER
                "Année" -> PERIOD_OF_TIME.YEAR
                else -> PERIOD_OF_TIME.WEEK
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SelectorButtonPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        var gender by remember { mutableStateOf("") }
        SelectorButton(options = listOf("Male", "Female", "Non-binary")) { selectedGender ->
            gender = selectedGender
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PeriodOfTimePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        val nbOfDay = remember { mutableStateOf(7)}
        SelectorPeriodOfTime {
            nbOfDay.value = it
        }
    }
}