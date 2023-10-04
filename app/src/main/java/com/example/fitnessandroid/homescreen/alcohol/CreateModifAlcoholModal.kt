package com.example.fitnessandroid.homescreen.alcohol

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
import com.example.fitnessandroid.Utilities.toUpperCase
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
fun CreateModifAlcoholModal(
    modifier : Modifier = Modifier,
    create: Boolean = true
) {
    val (alcoholName, setAlcoholName) = remember { mutableStateOf("") }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (percentAlcohol, setPercentAlcohol) = remember { mutableStateOf("") }
    val (calories, setCalories) = remember { mutableStateOf("") }
    val (carbs, setCarbs) = remember { mutableStateOf("") }
    val (unit, setUnit) = remember { mutableStateOf(FitHabData.fitHabConst.LiquidEnum.first()) }
    val (format, setFormat) = remember { mutableStateOf(FitHabData.fitHabConst.AlcoholFormatEnum.first()) }
    val researchText = remember { mutableStateOf("") }
    val selectedIdAlcohol = remember { mutableStateOf("") }

    val isDeleted = remember { mutableStateOf(false)}
    if(isDeleted.value) {
        isDeleted.value = false
        selectedIdAlcohol.value = ""
    }

    Column (modifier = modifier.fillMaxWidth() ){
        if(!create) {
            SearchBarComposable {text ->
                researchText.value = text
                selectedIdAlcohol.value = ""
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            ) {
                items(FitHabData.uiState.value.createdAlcohols.value.filter{ it.alcoholName.lowercase().contains(researchText.value.lowercase()) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .background(if (selectedIdAlcohol.value == it.idAlcohol) Color.LightGray else Color.White)
                            .clickable {
                                selectedIdAlcohol.value = it.idAlcohol
                                setAlcoholName(it.alcoholName)
                                setAmount(it.amount.toString())
                                setPercentAlcohol(it.percentAlcohol.toString())
                                setCalories(it.calories.toString())
                                setCarbs(it.carbs.toString())
                                setUnit(it.unit)
                                setFormat(it.format)
                            }
                    ) {
                        RowsInformation(alcohol = it) {
                            isDeleted.value = true
                        }
                    }
                }
            }
        }
        if(selectedIdAlcohol.value != "" || create) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldComposable(
                    modifier = Modifier.fillMaxWidth(),
                    value = alcoholName,
                    onValueChange = setAlcoholName,
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
                        modifier = Modifier.padding(horizontal = 12.dp),
                        options = FitHabData.fitHabConst.LiquidEnum,
                        backgroundColor = colorResource(id = R.color.alcohol),
                        setOptionSelected = setUnit
                    )
                    DropdownSelector(
                        modifier = Modifier.padding(horizontal = 12.dp),
                        options = FitHabData.fitHabConst.AlcoholFormatEnum,
                        capitalize = true,
                        backgroundColor = colorResource(id = R.color.alcohol),
                        setOptionSelected = setFormat
                    )
                }
                TextFieldNumberComposable(
                    value = percentAlcohol,
                    onValueChange = setPercentAlcohol,
                    label = "% Alcool",
                    color = Color.Black
                )
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

                Spacer(modifier = Modifier.padding(4.dp))
            }
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    val json = hashMapOf(
                        Pair("alcoholName", alcoholName),
                        Pair("amount", amount),
                        Pair("percentAlcohol", percentAlcohol),
                        Pair("unit", unit),
                        Pair("format", format),
                        Pair("calories", calories),
                        Pair("carbs", carbs),
                        Pair("protein", "0"),
                        Pair("fiber", "0"),
                        Pair("fat", "0"),
                    )
                    if(create) {
                        createAlcohol(json)
                    } else {
                        json["idAlcohol"] = selectedIdAlcohol.value
                        updateAlcohol(json)
                    }
                },
                enabled = (alcoholName.isNotBlank() && amount.isNotBlank()
                        && percentAlcohol.isNotBlank() && calories.isNotBlank()
                        && carbs.isNotBlank()),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.alcohol),contentColor = Color.Black),
            ) {
                Text(
                    text = if(create) "Créer".uppercase() else "Modifier".uppercase(),
                )
            }
        }
    }
}

private fun createAlcohol(json : HashMap<String,String>) {
    FitHabData.createAlcohol(json)
}

private fun updateAlcohol(json : HashMap<String,String>) {
    FitHabData.updateAlcohol(json)
}

private fun deleteAlcohol(json : HashMap<String,String>) {
    FitHabData.deleteAlcohol(json)
}

@Composable
private fun RowsInformation(alcohol: Alcohol, isDeleted : () -> Unit) {
    Row() {
        Column(modifier = Modifier.fillMaxWidth(0.85f)) {
            Text(
                text = alcohol.alcoholName,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "${alcohol.amount} ${alcohol.unit} ${alcohol.format.toUpperCase()} ${alcohol.percentAlcohol}%",
                fontSize = 12.sp
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = "${alcohol.calories} Calories, ${alcohol.carbs} Glucides",
                fontSize = 12.sp
            )
        }
        CircularButtonWithIcon(
            action = {
                deleteAlcohol(json = hashMapOf(Pair("idAlcohol", alcohol.idAlcohol)))
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
private fun UserAlcoholModalCreatePreview() {
    FitHabData.uiState.value.createdAlcohols.value = listOf(Utilities.fitHabAlcoholForPreview(), Utilities.fitHabAlcoholForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        CreateModifAlcoholModal(it, create = true)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserAlcoholModalModifPreview() {
    FitHabData.uiState.value.createdAlcohols.value = listOf(Utilities.fitHabAlcoholForPreview(), Utilities.fitHabAlcoholForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        CreateModifAlcoholModal(it, create = false)
    }
}