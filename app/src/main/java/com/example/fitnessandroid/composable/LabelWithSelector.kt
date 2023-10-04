package com.example.fitnessandroid.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.common.PreviewComposable
import com.example.fitnessandroid.composable.selector.SelectorRadioButton

@Composable
fun LabelWithSelector(
    modifier: Modifier = Modifier,
    textValue: String,
    options : List<String>,
    optionSelected : String,
    setOptionSelected : (String) -> Unit,
    color: Color? = null
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = textValue,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.width(8.dp))
        SelectorRadioButton(
            options = options,
            optionSelected = optionSelected,
            setOptionSelected = setOptionSelected,
            color = color
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LabelWithSelectorPreview() {
    PreviewComposable(backgroundColor = Color.Black) {
        val (text, setText) = remember { mutableStateOf("Selection des unités") }
        val (optionSelected, setOptionSelected) = remember { mutableStateOf("") }
        LabelWithSelector(
            modifier = it,
            textValue = text,
            options = listOf("kg", "lbs"),
            optionSelected = optionSelected,
            setOptionSelected = setOptionSelected
        )
    }
}