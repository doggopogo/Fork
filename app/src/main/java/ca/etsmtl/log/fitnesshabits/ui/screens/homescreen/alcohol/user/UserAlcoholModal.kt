package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.alcohol.user

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.helper.Utilities
import ca.etsmtl.log.fitnesshabits.helper.Utilities.toStringYMDTHM
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable
import ca.etsmtl.log.fitnesshabits.ui.views.DatePickerComposable
import ca.etsmtl.log.fitnesshabits.ui.views.TextFieldNumberComposable
import ca.etsmtl.log.fitnesshabits.ui.views.TimePicker
import ca.etsmtl.log.fitnesshabits.ui.views.searchbar.SearchBarComposable
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.alcohol.Alcohol
import java.util.*
import kotlin.collections.HashMap

@Composable
fun UserAlcoholModal(
    modifier : Modifier = Modifier,
    userAlcohol: UserAlcohol? = null
) {
    val calendar = Calendar.getInstance()
    val dateText = remember { mutableStateOf(userAlcohol?.timestamp ?: calendar.time) }
    val timeText = remember { mutableStateOf(userAlcohol?.timestamp ?: calendar.time) }

    val researchText = remember { mutableStateOf("") }
    val selectedIdAlcohol = remember { mutableStateOf(userAlcohol?.alcohol?.idAlcohol ?: "") }

    val numberOfConsumption = remember { mutableStateOf(userAlcohol?.numberOfConsumption?.toString() ?: "")}
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        SearchBarComposable {
            researchText.value = it
            selectedIdAlcohol.value = ""
            FitHabData.getAlcohols(hashMapOf(Pair("alcoholName", researchText.value)))
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ) {
            items(FitHabData.uiState.value.alcohols.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .background(if (selectedIdAlcohol.value == it.idAlcohol) Color.LightGray else Color.White)
                        .clickable {
                            selectedIdAlcohol.value = it.idAlcohol
                        }
                ) {
                    RowsInformation(alcohol = it)
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            TextFieldNumberComposable(
                value = numberOfConsumption.value,
                onValueChange = {
                    numberOfConsumption.value = it
                },
                label = "Nombre de consommation",
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ){
                DatePickerComposable(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(100.dp),
                    defaultDate = userAlcohol?.timestamp,
                    color = colorResource(id = R.color.alcohol),
                ){
                    dateText.value = it
                }
                TimePicker(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(100.dp)
                        .padding(start = 20.dp),
                    defaultDate = userAlcohol?.timestamp,
                    color = colorResource(id = R.color.alcohol),
                ){
                    timeText.value = it
                }
            }
        }
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                val json = hashMapOf(
                    Pair("idAlcohol", selectedIdAlcohol.value),
                    Pair("numberOfConsumption", numberOfConsumption.value),
                    Pair("timestamp", Utilities.dateAndTimeCalendar(dateText.value, timeText.value).toStringYMDTHM())
                )
                if(userAlcohol == null) {
                    addUserAlcohol(json)
                } else {
                    userAlcohol.idUserAlcohol.let { json.put("idUserAlcohol", it) }
                    editUserAlcohol(json)
                }
            },
            enabled = (selectedIdAlcohol.value != "" && numberOfConsumption.value.isNotEmpty()),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.alcohol), contentColor = Color.Black),
        ) {
            Text(
                text = userAlcohol?.let { "Modifier".uppercase() } ?: "Ajouter".uppercase(),
                color = Color.White
            )
        }
    }
}

private fun editUserAlcohol(json : HashMap<String,String>) {
    FitHabData.updateUserAlcohol(json)
}

private fun addUserAlcohol(json : HashMap<String,String>) {
    FitHabData.createUserAlcohol(json)
}

@Composable
private fun RowsInformation(alcohol: Alcohol) {
    Text(
        text = alcohol.alcoholName,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
    Text(
        modifier = Modifier.padding(start = 4.dp),
        text = "${alcohol.amount} ${alcohol.unit} ${alcohol.format}, ${alcohol.percentAlcohol}%, ${alcohol.calories} calories, ${alcohol.carbs}g carbs",
        fontSize = 12.sp
    )
    Divider(color = Color.LightGray, thickness = 1.dp)
}


@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserAlcoholModalCreatePreview() {
    FitHabData.uiState.value.alcohols.value = listOf(Utilities.fitHabAlcoholForPreview(), Utilities.fitHabAlcoholForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        UserAlcoholModal(it)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserAlcoholModalUpdatePreview() {
    FitHabData.uiState.value.alcohols.value = listOf(Utilities.fitHabAlcoholForPreview(), Utilities.fitHabAlcoholForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        UserAlcoholModal(it, Utilities.fitHabUiStateForPreview().userInfo.userAlcohols.value.first())
    }
}