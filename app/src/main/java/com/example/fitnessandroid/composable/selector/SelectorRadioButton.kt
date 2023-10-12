package com.example.fitnessandroid.composable.selector

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.common.PreviewComposable
import com.example.fitnessandroid.ui.theme.white

@Composable
fun SelectorRadioButton(
    modifier: Modifier = Modifier,
    options : List<String>,
    optionSelected : String,
    setOptionSelected : (String) -> Unit,
    color: Color? = null // Add an optional color parameter
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach {

            if (color != null) { // check if color is provided
                RadioButton(
                    selected = optionSelected == it,
                    onClick = { setOptionSelected(it) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = color,
                        unselectedColor = color // Use the provided color
                    )
                )
                Text(text = it, color = color) // Use the provided color
            }else{
                RadioButton(
                    selected = optionSelected == it,
                    onClick = { setOptionSelected(it) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = white,
                        unselectedColor = white
                    )
                )
                Text(text = it, color = Color.White)
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectorRadioButtonPreview() {
    PreviewComposable(backgroundColor = Color.Black) {
        val (optionSelected, setOptionSelected) = remember { mutableStateOf("") }
        SelectorRadioButton(
            modifier = it,
            options = listOf("kg", "lbs"),
            optionSelected = optionSelected,
            setOptionSelected = setOptionSelected
        )
    }
}