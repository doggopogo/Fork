package ca.etsmtl.log.fitnesshabits.ui.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable

@Composable
fun TextFieldNumberComposable(
    modifier : Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    color: Color = Color.White,
    label: String,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            if (it.isEmpty() || it.matches(Regex("^[\\d]*(\\.[\\d]*)?[\b]*$"))) {
                onValueChange(it)
            }
        },
        label = { Text(text = label.uppercase(), color = color) },
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = color,
            focusedBorderColor = color,
            unfocusedBorderColor = color,
            backgroundColor = Color.Transparent,
            cursorColor = Color.LightGray.copy(alpha = ContentAlpha.medium)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
private fun TextFieldNumberComposablePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        val (number, setNumber) = remember { mutableStateOf("") }
        TextFieldNumberComposable(
            modifier = it,
            value = number,
            onValueChange = setNumber,
            label = "Nombre de consommation",
            color = Color.Black
        )
    }
}