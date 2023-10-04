package com.example.fitnessandroid.homescreen.alcohol.user

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.R
import com.example.fitnessandroid.Utilities
import com.example.fitnessandroid.Utilities.isInLastXDays
import com.example.fitnessandroid.Utilities.nbOfDaySinceToday
import com.example.fitnessandroid.common.PreviewComposable
import com.example.fitnessandroid.common.config.PERIOD_OF_TIME
import com.example.fitnessandroid.composable.GraphComposable
import com.example.fitnessandroid.composable.GraphComposableUiState
import com.example.fitnessandroid.composable.RowDataUiState
import com.example.fitnessandroid.composable.RowsDataModule
import com.example.fitnessandroid.composable.selector.DropdownSelector
import com.example.fitnessandroid.composable.selector.SelectorPeriodOfTime
import com.example.fitnessandroid.fithabdata.FitHabData
import com.example.fitnessandroid.fithabdata.UserInfo
import com.example.fitnessandroid.homescreen.ModuleModal
import com.example.fitnessandroid.homescreen.ShowListModal
import com.example.fitnessandroid.homescreen.alcohol.CreateModifAlcoholModal

@Composable
fun UserAlcoholScreen(userInfo : UserInfo) {
    val openDialogList = remember { mutableStateOf(false)  }
    val openDialogAdd = remember { mutableStateOf(false)  }
    val openDialogEdit = remember { mutableStateOf(false)  }
    val openDialogCreate = remember { mutableStateOf(false)  }
    val openDialogModif = remember { mutableStateOf(false)  }
    val nbOfDay = remember { mutableStateOf(PERIOD_OF_TIME.WEEK)}
    val graphValues = listOf("Nombre de consommation", "Calories", "Glucides")
    val graphValueSelected = remember { mutableStateOf(graphValues.first())}
    val currentUserAlcohol : MutableState<UserAlcohol?> = remember{ mutableStateOf(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (openDialogList.value) {
            ShowListModal(setShowModal = {openDialogList.value = false}) {
                ShowRows(userAlcohols = userInfo.userAlcohols.value, 36500) {
                    currentUserAlcohol.value = it
                    openDialogEdit.value = true
                }
            }
        }

        if (openDialogAdd.value) {
            ModuleModal(
                R.drawable.icon_alcohol,
                "Alcool",
                R.color.alcohol,
                { UserAlcoholModal() }
            )
            {
                openDialogAdd.value = false
            }
        }
        if (openDialogEdit.value) {
            ModuleModal(
                R.drawable.icon_alcohol,
                "Alcool",
                R.color.alcohol,
                { UserAlcoholModal(userAlcohol = currentUserAlcohol.value) }
            )
            {
                openDialogEdit.value = false
            }
        }

        if (openDialogCreate.value) {
            ModuleModal(
                R.drawable.icon_alcohol,
                "Alcool",
                R.color.alcohol,
                { CreateModifAlcoholModal() }
            )
            {
                FitHabData.getCreatedByAlcohols()
                FitHabData.getAlcohols()
                openDialogCreate.value = false
            }
        }
        if (openDialogModif.value) {
            ModuleModal(
                R.drawable.icon_alcohol,
                "Alcool",
                R.color.alcohol,
                { CreateModifAlcoholModal(modifier = Modifier.fillMaxHeight(), create = false) }
            )
            {
                FitHabData.getCreatedByAlcohols()
                FitHabData.getAlcohols()
                openDialogModif.value = false
            }
        }

        DropdownSelector(
            options = graphValues,
            backgroundColor = colorResource(id = R.color.alcohol),
            setOptionSelected = {
                graphValueSelected.value = it
            }
        )

        Graph(userAlcohols = userInfo.userAlcohols.value, nbOfDay.value, graphValueSelected.value)

        SelectorPeriodOfTime(color = colorResource(id = R.color.alcohol)) {
            nbOfDay.value = it
        }

        Column(
            Modifier.fillMaxHeight(0.6f)
        ) {
            ShowRows(userAlcohols = userInfo.userAlcohols.value, nbOfDay.value) {
                currentUserAlcohol.value = it
                openDialogEdit.value = true
            }
        }

        Column(
            Modifier.fillMaxSize()
        ) {
            Row(modifier = Modifier.padding(vertical = 5.dp)) {
                Button(
                    onClick = {
                        FitHabData.getAlcohols()
                        openDialogAdd.value = true
                    },
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(text = "Ajouter")
                }
                Button(
                    onClick = { userInfo.userAlcohols.value.isNotEmpty().let { openDialogList.value = true } },
                    modifier = Modifier.padding(horizontal = 20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(text = "Afficher toute la liste")
                }
            }
            Row(modifier = Modifier.padding(vertical = 5.dp)){
                Button(
                    onClick = { openDialogCreate.value = true },
                    modifier = Modifier.padding(horizontal = 20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(text = "Créer")
                }
                Button(
                    onClick = {
                        FitHabData.getCreatedByAlcohols()
                        openDialogModif.value = true
                    },
                    modifier = Modifier.padding(horizontal = 20.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(text = "Modifier")
                }
            }
        }
    }
}

@Composable
private fun Graph(userAlcohols :  List<UserAlcohol>, nbOfDay: Int, graphValueSelected : String) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.4f)
            .fillMaxWidth()
    ) {
        val xLabelsValues :List<Int> = when(nbOfDay) {
            PERIOD_OF_TIME.WEEK -> (0 until nbOfDay).map { it + 1 }
            PERIOD_OF_TIME.MONTH -> (0 until nbOfDay step 4).map { it + 1 }.toMutableList().apply { if(last() != nbOfDay) add(nbOfDay) }
            PERIOD_OF_TIME.QUARTER -> (0 until nbOfDay step 10).map { it + 1 }.toMutableList().apply { if(last() != nbOfDay) add(nbOfDay) }
            PERIOD_OF_TIME.SEMESTER -> (0 until nbOfDay step 20).map { it + 1 }.toMutableList().apply { if(last() != nbOfDay) add(nbOfDay) }
            PERIOD_OF_TIME.YEAR -> (0 until nbOfDay step 30).map { it + 1 }.toMutableList().apply { if(last() != nbOfDay) add(nbOfDay) }
            else  -> (0 until nbOfDay).map { it + 1 }.toList()
        }

        val filteredListUserAlcohol : List<UserAlcohol> = userAlcohols.filter {
            it.timestamp.isInLastXDays(nbOfDay)
        }.sortedByDescending { it.timestamp }

        Log.d("nemo filteredListUserAlcohol", filteredListUserAlcohol.toString())

        val tempsPoints = (0 until nbOfDay).map { 0f }.toMutableList()
        tempsPoints.apply {
            filteredListUserAlcohol.forEach {
                when(graphValueSelected){
                    "Nombre de consommation" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat()
                    "Calories" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat() * it.alcohol.calories.toFloat()
                    "Glucides" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat() * it.alcohol.carbs
                }
            }
        }

        val points : List<Float> = mutableListOf<Float>().apply {
            when(nbOfDay) {
                PERIOD_OF_TIME.WEEK -> addAll(tempsPoints)
                PERIOD_OF_TIME.MONTH, PERIOD_OF_TIME.QUARTER, PERIOD_OF_TIME.SEMESTER, PERIOD_OF_TIME.YEAR -> apply {
                    xLabelsValues.forEachIndexed { index , value ->
                        if(value == nbOfDay) {
                            add(tempsPoints.subList(xLabelsValues[index -1], xLabelsValues[index]).average().toFloat())
                            return@forEachIndexed
                        }
                        this.add(
                            tempsPoints.subList(if(index == 0) 0  else xLabelsValues[index], xLabelsValues[index+1]).average().toFloat()
                        )
                    }
                }
                else -> addAll(tempsPoints)
            }
        }

        GraphComposable(uiState = GraphComposableUiState(
            xLabelValues = xLabelsValues,
            points = points,
            color = colorResource(id = R.color.alcohol)
        ))
    }
}

@Composable
private fun ShowRows(userAlcohols : List<UserAlcohol>, nbOfDay : Int, editModal:(UserAlcohol) -> Unit) {
    val rowsDataUiState = mutableListOf<RowDataUiState>()

    userAlcohols.filter {
        it.timestamp.isInLastXDays(nbOfDay)
    }.sortedByDescending { it.timestamp }.map{
        rowsDataUiState.add(
            RowDataUiState(
                title = it.timestamp.toString(),
                edit = { editModal(it) },
                delete = { delete(it) },
                content = { RowContent(it) }
            )
        )
    }

    RowsDataModule(Modifier,rowsDataUiState)

}

private fun delete(userAlcohol: UserAlcohol) {
    FitHabData.deleteUserAlcohol(userAlcohol.idUserAlcohol)
}

@Composable
private fun RowContent(userAlcohol: UserAlcohol) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "${userAlcohol.numberOfConsumption}x ${userAlcohol.alcohol.alcoholName} ${userAlcohol.alcohol.amount}${userAlcohol.alcohol.unit} ${userAlcohol.alcohol.percentAlcohol}%")
        Text(text = "Calories: ${userAlcohol.alcohol.calories * userAlcohol.numberOfConsumption.toFloat()}, Glucides: ${userAlcohol.alcohol.carbs * userAlcohol.numberOfConsumption.toFloat()}g")
    }
}

@Preview(showBackground = true)
@Composable
private fun RowContentPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        RowContent(Utilities.userInfoForPreview().userAlcohols.value.first())
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UserAlcoholScreenPreview() {
    UserAlcoholScreen(userInfo = Utilities.userInfoForPreview())
}