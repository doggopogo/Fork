package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.food.user

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
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.food.Food
import java.util.*
import kotlin.collections.HashMap

@Composable
fun UserFoodModal(
    modifier : Modifier = Modifier,
    userFood: UserFood? = null
) {
    val calendar = Calendar.getInstance()
    val dateText = remember { mutableStateOf(userFood?.timestamp ?: calendar.time) }
    val timeText = remember { mutableStateOf(userFood?.timestamp ?: calendar.time) }

    val researchText = remember { mutableStateOf("") }
    val selectedIdFood = remember { mutableStateOf(userFood?.food?.idFood ?: "") }

    val numberOfConsumption = remember { mutableStateOf(userFood?.numberOfConsumption?.toString() ?: "")}
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        SearchBarComposable {
            researchText.value = it
            selectedIdFood.value = ""
            FitHabData.getFoods(hashMapOf(Pair("foodName", researchText.value)))
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ) {
            items(FitHabData.uiState.value.foods.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .background(if (selectedIdFood.value == it.idFood) Color.LightGray else Color.White)
                        .clickable {
                            selectedIdFood.value = it.idFood
                        }
                ) {
                    RowsInformation(food = it)
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
                    defaultDate = userFood?.timestamp,
                    color = colorResource(id = R.color.food),
                    textColor = Color.White
                ){
                    dateText.value = it
                }
                TimePicker(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(100.dp)
                        .padding(start = 20.dp),
                    defaultDate = userFood?.timestamp,
                    color = colorResource(id = R.color.food),
                    textColor = Color.White
                ){
                    timeText.value = it
                }
            }
        }

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                val json = hashMapOf(
                    Pair("idFood", selectedIdFood.value),
                    Pair("numberOfConsumption", numberOfConsumption.value),
                    Pair("timestamp", Utilities.dateAndTimeCalendar(dateText.value, timeText.value).toStringYMDTHM())
                )
                if(userFood == null) {
                    addUserFood(json)
                } else {
                    userFood.idUserFood.let { json.put("idUserFood", it) }
                    editUserFood(json)
                }
            },
            enabled = (selectedIdFood.value != "" && numberOfConsumption.value.isNotEmpty()),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.food), contentColor = Color.White),
        ) {
            Text(
                text = userFood?.let { "Modifier".uppercase() } ?: "Ajouter".uppercase(),
            )
        }
    }
}

private fun editUserFood(json : HashMap<String,String>) {
    FitHabData.updateUserFood(json)
}

private fun addUserFood(json : HashMap<String,String>) {
    FitHabData.createUserFood(json)
}

@Composable
private fun RowsInformation(food: Food) {
    Text(
        text =  "${food.foodName}, ${food.amount} ${food.unit}",
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
    Text(
        modifier = Modifier.padding(start = 4.dp),
        text = "Calories: ${food.calories}, " +
            "Glucides: ${food.carbs}g, " +
            "Protéines: ${food.protein}g, " +
            "Sucre: ${food.sugar}g, " +
            "Fibre: ${food.fiber}g, " +
            "Gras total: ${food.totalFat}g, " +
            "Gras saturé: ${food.saturatedFat}g",
        fontSize = 12.sp
    )
    Divider(color = Color.LightGray, thickness = 1.dp)
}


@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserFoodModalCreatePreview() {
    FitHabData.uiState.value.foods.value = listOf(Utilities.fitHabFoodForPreview(), Utilities.fitHabFoodForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        UserFoodModal(it)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserFoodModalUpdatePreview() {
    FitHabData.uiState.value.foods.value = listOf(Utilities.fitHabFoodForPreview(), Utilities.fitHabFoodForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        UserFoodModal(it, Utilities.fitHabUiStateForPreview().userInfo.userFoods.value.first())
    }
}