package ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.preference

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.helper.Utilities
import ca.etsmtl.log.fitnesshabits.ui.views.LabelWithSelector
import ca.etsmtl.log.fitnesshabits.ui.views.Switches
import ca.etsmtl.log.fitnesshabits.ui.views.selector.SelectorRadioButton
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabUiState

@Composable
fun PreferenceScreen(uiState : FitHabUiState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((-40).dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(75.dp))
        }
        item {
            Text(
                text = "Format de l'heure",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
        item {
            Spacer(modifier = Modifier.height(95.dp))
        }
        item {
            val (optionSelected, setOptionTimeSelected) = remember { mutableStateOf("") }
            SelectorRadioButton(
                options = listOf("12h", "24h"),
                optionSelected = optionSelected,
                setOptionSelected = setOptionTimeSelected,
                color = Color.Black
            )
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
        item {
            RadioDateFormat()
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
        item {
            Text(
                text = "Unité de mesure par défaut",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
        item {
            Spacer(modifier = Modifier.height(75.dp))
        }
        item {
            val (heightText) = remember { mutableStateOf("Liquides") }
            val (heightSelected, setHeightSelected) = remember { mutableStateOf("") }
            LabelWithSelector(
                textValue = heightText,
                options = listOf("L/ml", "On"),
                optionSelected = heightSelected,
                setOptionSelected = setHeightSelected,
                color = Color.Black
            )
        }
        item {
            val (heightText) = remember { mutableStateOf("Poids personnel") }
            val (heightSelected, setHeightSelected) = remember { mutableStateOf("") }
            LabelWithSelector(
                textValue = heightText,
                options = listOf("Kg", "Lbs"),
                optionSelected = heightSelected,
                setOptionSelected = setHeightSelected,
                color = Color.Black
            )
        }
        item {
            val (heightText) = remember { mutableStateOf("Poids des aliments") }
            val (heightSelected, setHeightSelected) = remember { mutableStateOf("") }
            LabelWithSelector(
                textValue = heightText,
                options = listOf("g", "On"),
                optionSelected = heightSelected,
                setOptionSelected = setHeightSelected,
                color = Color.Black
            )
        }
        item {
            val (heightText) = remember { mutableStateOf("Taille") }
            val (heightSelected, setHeightSelected) = remember { mutableStateOf("") }
            LabelWithSelector(
                textValue = heightText,
                options = listOf("m", "cm", "pi.po"),
                optionSelected = heightSelected,
                setOptionSelected = setHeightSelected,
                color = Color.Black
            )
        }
        item {
            Spacer(modifier = Modifier.height(100.dp))
        }
        item {
            Text(
                text = "Activer ou désactiver les éléments",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )
        }
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
        item {
            Switches(
                items = FitHabData.getTogglesModule()
            ) { list ->
                FitHabData.setToggleModule(
                    list.map {
                        Pair(
                            it.toggleValue,
                            it.isChecked.toString()
                        )
                    }.toMap() as HashMap<String, String>
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreferenceScreenPreview() {
    PreferenceScreen(Utilities.fitHabUiStateForPreview())
}

@Composable
fun RadioDateFormat() {
    var selectedOption by remember { mutableStateOf(0) }

    Column(Modifier.padding(16.dp)) {
        Text(
            text = "Date format",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )

        Spacer(Modifier.height(16.dp))

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == 4,
                    onClick = { selectedOption = 4 },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("AAAA/MM/JJ")
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(
                    selected = selectedOption == 5,
                    onClick = { selectedOption = 5 },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("AAAA-Mmm-JJ", modifier = Modifier.padding(end = 16.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == 0,
                    onClick = { selectedOption = 0 },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("JJ/MM/AAAA")
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(
                    selected = selectedOption == 1,
                    onClick = { selectedOption = 1 },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("JJ-Mmm-AAAA", modifier = Modifier.padding(end = 16.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOption == 2,
                    onClick = { selectedOption = 2 },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("MM/JJ/AAAA")
                Spacer(modifier = Modifier.weight(1f))
                RadioButton(
                    selected = selectedOption == 3,
                    onClick = { selectedOption = 3 },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Mmm-JJ-AAAA", modifier = Modifier.padding(end = 16.dp))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Exemple : 11/04/2023",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }


        }
    }
}



