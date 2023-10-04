package com.example.fitnessandroid.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.common.PreviewComposable
import com.example.fitnessandroid.composable.selector.SelectorRadioButton

@Composable
fun EntryWithSelector(
    modifier: Modifier = Modifier,
    textFieldLabel: String,
    textValue: String,
    onTextValueChange: (String) -> Unit,
    options : List<String>,
    optionSelected : String,
    setOptionSelected : (String) -> Unit,
) {
    Row(
        modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textValue,
            onValueChange = onTextValueChange,
            label = { Text(textFieldLabel) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black
            ),
        )
        Spacer(Modifier.width(8.dp))
        SelectorRadioButton(options = options, optionSelected = optionSelected, setOptionSelected = setOptionSelected)
    }
}

@Preview(showBackground = true)
@Composable
private fun EntryWithSelectorPreview() {
    PreviewComposable(backgroundColor = Color.Black) {
        val (text, setText) = remember { mutableStateOf("") }
        val (optionSelected, setOptionSelected) = remember { mutableStateOf("") }
        EntryWithSelector(
            modifier = it,
            textFieldLabel = "Weight",
            textValue = text,
            onTextValueChange = setText,
            options = listOf("kg", "lbs"),
            optionSelected = optionSelected,
            setOptionSelected = setOptionSelected
        )
    }
}