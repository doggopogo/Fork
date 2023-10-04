package com.example.fitnessandroid.settingsdrawer.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessandroid.R
import com.example.fitnessandroid.Utilities
import com.example.fitnessandroid.fithabdata.FitHabUiState

@Composable
fun LanguageScreen(uiState: FitHabUiState) {
    val options = listOf(
        RadioOption("English", R.drawable.flag_uk),
        RadioOption("Français", R.drawable.flag_france),
        RadioOption("Espagnol", R.drawable.flag_spain)
    )
    
    var selectedOption by remember { mutableStateOf<RadioOption?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        LanguageTitle()
        Spacer(modifier = Modifier.height(20.dp))
        RadioOptions(options, selectedOption) { option ->
            selectedOption = option
        }
    }
}


@Preview
@Composable
fun LanguageTitle() {
    Text(
        "Choisissez votre langue par défaut",
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontSize = 15.sp
    )
}


@Composable
fun RadioOptions(
    options: List<RadioOption>,
    selectedOption: RadioOption?,
    onOptionSelected: (RadioOption) -> Unit
) {
    Column {
        options.forEach { option ->
            RadioOptionItem(option, selectedOption, onOptionSelected)
        }
    }
}

@Composable
fun RadioOptionItem(
    option: RadioOption,
    selectedOption: RadioOption?,
    onOptionSelected: (RadioOption) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = option == selectedOption,
            onClick = { onOptionSelected(option) },
            colors = RadioButtonDefaults.colors(Color.Black)
        )
        Spacer(modifier = Modifier.width(16.dp))
        FlagImage(option)
        Spacer(modifier = Modifier.width(16.dp))
        LanguageText(option)
    }
}


@Composable
fun FlagImage(option: RadioOption) {
    Image(
        painter = painterResource(option.imageResId),
        contentDescription = null,
        modifier = Modifier.size(32.dp)
    )
}

@Composable
fun LanguageText(option: RadioOption) {
    Text(
        text = option.text,
        style = MaterialTheme.typography.subtitle1
    )
}

data class RadioOption(val text: String, val imageResId: Int)


@Preview(showSystemUi = true)
@Composable
fun LanguageScreenPreview() {
    LanguageScreen(Utilities.fitHabUiStateForPreview())
}