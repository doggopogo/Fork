package ca.etsmtl.log.fitnesshabits.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable
import ca.etsmtl.log.fitnesshabits.ui.views.selector.SelectorRadioButton
import ca.etsmtl.log.fitnesshabits.ui.theme.appText

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
        //modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = textValue,
            onValueChange = onTextValueChange,
            singleLine = true,
            modifier = Modifier
                .width(110.dp)
                .height(55.dp),
            placeholder = {
                Text(
                    text = textFieldLabel,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Normal,
                    color = appText
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                textColor = Color.Black
            ),
        )
        Spacer(Modifier.width(8.dp))
        SelectorRadioButton(
            options = options,
            optionSelected = optionSelected,
            setOptionSelected = setOptionSelected)
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