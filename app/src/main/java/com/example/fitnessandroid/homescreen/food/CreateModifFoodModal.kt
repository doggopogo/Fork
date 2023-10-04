package com.example.fitnessandroid.homescreen.food

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
import com.example.fitnessandroid.R
import com.example.fitnessandroid.Utilities
import com.example.fitnessandroid.common.PreviewComposable
import com.example.fitnessandroid.composable.CircularButtonWithIcon
import com.example.fitnessandroid.composable.TextFieldComposable
import com.example.fitnessandroid.composable.TextFieldNumberComposable
import com.example.fitnessandroid.composable.searchbar.SearchBarComposable
import com.example.fitnessandroid.composable.selector.DropdownSelector
import com.example.fitnessandroid.fithabdata.FitHabData
import java.util.*
import kotlin.collections.HashMap

@Composable
fun CreateModifFoodModal(
    modifier : Modifier = Modifier,
    create: Boolean = true
) {
    val (foodName, setFoodName) = remember { mutableStateOf("") }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (calories, setCalories) = remember { mutableStateOf("") }
    val (protein, setProtein) = remember { mutableStateOf("") }
    val (carbs, setCarbs) = remember { mutableStateOf("") }
    val (sugar, setSugar) = remember { mutableStateOf("") }
    val (fiber, setFiber) = remember { mutableStateOf("") }
    val (totalFat, setTotalFat) = remember { mutableStateOf("") }
    val (saturatedFat, setSaturatedFat) = remember { mutableStateOf("") }
    val (unit, setUnit) = remember { mutableStateOf(FitHabData.fitHabConst.FoodEnum.first()) }
    val researchText = remember { mutableStateOf("") }
    val selectedIdFood = remember { mutableStateOf("") }

    val isDeleted = remember { mutableStateOf(false)}
    if(isDeleted.value) {
        isDeleted.value = false
        selectedIdFood.value = ""
    }

    Column (modifier = modifier.fillMaxWidth() ){
        if(!create) {
            SearchBarComposable {text ->
                researchText.value = text
                selectedIdFood.value = ""
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            ) {
                items(FitHabData.uiState.value.createdFood.value.filter{ it.foodName.lowercase().contains(researchText.value.lowercase()) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .background(if (selectedIdFood.value == it.idFood) Color.LightGray else Color.White)
                            .clickable {
                                selectedIdFood.value = it.idFood
                                setFoodName(it.foodName)
                                setAmount(it.amount.toString())
                                setCalories(it.calories.toString())
                                setCarbs(it.carbs.toString())
                                setFiber(it.fiber.toString())
                                setProtein(it.protein.toString())
                                setSugar(it.sugar.toString())
                                setTotalFat(it.totalFat.toString())
                                setSaturatedFat(it.saturatedFat.toString())
                                setUnit(it.unit)
                            }
                    ) {
                        RowsInformation(food = it) {
                            isDeleted.value = true
                        }
                    }
                }
            }
        }
        if(selectedIdFood.value != "" || create) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextFieldComposable(
                            modifier = Modifier.fillMaxWidth(),
                            value = foodName,
                            onValueChange = setFoodName,
                            label = "Nom",
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.padding(2.dp))
                        TextFieldNumberComposable(
                            value = amount,
                            onValueChange = setAmount,
                            label = "Quantité",
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Row {
                            DropdownSelector(
                                options = FitHabData.fitHabConst.FoodEnum,
                                backgroundColor = colorResource(id = R.color.food),
                                first = unit,
                                textColor = Color.White,
                                setOptionSelected = setUnit
                            )
                        }
                        TextFieldNumberComposable(
                            value = calories,
                            onValueChange = setCalories,
                            label = "Calories",
                            color = Color.Black
                        )
                        TextFieldNumberComposable(
                            value = carbs,
                            onValueChange = setCarbs,
                            label = "Glucides",
                            color = Color.Black
                        )
                        TextFieldNumberComposable(
                            value = protein,
                            onValueChange = setProtein,
                            label = "Protéines",
                            color = Color.Black
                        )
                        TextFieldNumberComposable(
                            value = sugar,
                            onValueChange = setSugar,
                            label = "Sucre",
                            color = Color.Black
                        )
                        TextFieldNumberComposable(
                            value = fiber,
                            onValueChange = setFiber,
                            label = "Fibre",
                            color = Color.Black
                        )
                        TextFieldNumberComposable(
                            value = saturatedFat,
                            onValueChange = setSaturatedFat,
                            label = "Gras saturé",
                            color = Color.Black
                        )
                        TextFieldNumberComposable(
                            value = totalFat,
                            onValueChange = setTotalFat,
                            label = "Gras total",
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.padding(4.dp))
                    }
                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            val json = hashMapOf(
                                Pair("foodName", foodName),
                                Pair("amount", amount),
                                Pair("unit", unit),
                                Pair("calories", calories),
                                Pair("carbs", carbs),
                                Pair("sugar", sugar),
                                Pair("protein", protein),
                                Pair("fiber", fiber),
                                Pair("totalFat", totalFat),
                                Pair("saturatedFat", saturatedFat),
                            )
                            if(create) {
                                createFood(json)
                            } else {
                                json["idFood"] = selectedIdFood.value
                                updateFood(json)
                            }
                        },
                        enabled = (foodName.isNotBlank() && amount.isNotBlank()
                                && calories.isNotBlank() && protein.isNotBlank()
                                && carbs.isNotBlank() && fiber.isNotBlank()
                                && saturatedFat.isNotBlank() && totalFat.isNotBlank()
                                && sugar.isNotBlank()
                                ),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.food)),
                    ) {
                        Text(
                            text = if(create) "Créer".uppercase() else "Modifier".uppercase(),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

private fun createFood(json : HashMap<String,String>) {
    FitHabData.createFood(json)
}

private fun updateFood(json : HashMap<String,String>) {
    FitHabData.updateFood(json)
}

private fun deleteFood(json : HashMap<String,String>) {
    FitHabData.deleteFood(json)
}

@Composable
private fun RowsInformation(food: Food, isDeleted : () -> Unit) {
    Row() {
        Column(modifier = Modifier.fillMaxWidth(0.85f)) {
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
        }
        CircularButtonWithIcon(
            action = {
                deleteFood(json = hashMapOf(Pair("idFood", food.idFood)))
                isDeleted()
            },
            R.drawable.icon_delete_trashbin, "Delete Button"
        )
    }
    Spacer(modifier = Modifier.padding(2.dp))
    Divider(color = Color.LightGray, thickness = 1.dp)
}


@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserFoodModalCreatePreview() {
    FitHabData.uiState.value.createdFood.value = listOf(Utilities.fitHabFoodForPreview(), Utilities.fitHabFoodForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        CreateModifFoodModal(it, create = true)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserFoodModalModifPreview() {
    FitHabData.uiState.value.createdFood.value = listOf(Utilities.fitHabFoodForPreview(), Utilities.fitHabFoodForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        CreateModifFoodModal(it, create = false)
    }
}