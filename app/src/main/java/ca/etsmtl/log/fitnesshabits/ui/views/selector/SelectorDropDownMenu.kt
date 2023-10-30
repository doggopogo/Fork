package ca.etsmtl.log.fitnesshabits.ui.views.selector

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.helper.Utilities.toUpperCase
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable

@Composable
fun DropdownSelector (
    modifier: Modifier = Modifier,
    first : String? = null,
    options : List<String>,
    backgroundColor : Color = Color.Magenta,
    textColor : Color = Color.Black,
    capitalize : Boolean = false,
    setOptionSelected : (String) -> Unit
){
    var expanded by remember { mutableStateOf(false) }

    val valueSelected = remember { mutableStateOf(
        first ?: options.first()
    )}

    val icon = if (expanded)
        Icons.Filled.ArrowForward
    else
        Icons.Filled.ArrowDropDown

    Column(modifier = modifier) {
        Button(
            onClick = {expanded = true},
            colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor, contentColor = textColor)
        ) {
            Text(text = if(capitalize) valueSelected.value.toUpperCase() else valueSelected.value)
            Spacer(Modifier.padding(start = 4.dp))
            Icon(imageVector = icon, contentDescription = "")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(
                    Color.White
                )
        ) {
            options.forEach { selected ->
                DropdownMenuItem(
                    onClick = {
                        setOptionSelected(selected)
                        valueSelected.value = selected
                        expanded = false
                    }
                ) {
                    Text(
                        text = if(capitalize) selected.toUpperCase() else selected,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DropDownMenuPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        val (_, setOptionSelected) = remember { mutableStateOf("kg") }
        DropdownSelector(
            modifier = it,
            options = listOf("kg", "lbs"),
            setOptionSelected = setOptionSelected
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DropDownMenuCapitalizePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        val (_, setOptionSelected) = remember { mutableStateOf("kg") }
        DropdownSelector(
            modifier = it,
            options = listOf("kg", "lbs"),
            textColor = Color.White,
            capitalize = true,
            setOptionSelected = setOptionSelected
        )
    }
}