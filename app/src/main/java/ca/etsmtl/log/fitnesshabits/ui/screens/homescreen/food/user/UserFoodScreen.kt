package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.food.user

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
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.helper.Utilities
import ca.etsmtl.log.fitnesshabits.helper.Utilities.isInLastXDays
import ca.etsmtl.log.fitnesshabits.helper.Utilities.nbOfDaySinceToday
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable
import ca.etsmtl.log.fitnesshabits.toDelete.common.config.PERIOD_OF_TIME
import ca.etsmtl.log.fitnesshabits.ui.views.GraphComposable
import ca.etsmtl.log.fitnesshabits.ui.views.GraphComposableUiState
import ca.etsmtl.log.fitnesshabits.ui.views.RowDataUiState
import ca.etsmtl.log.fitnesshabits.ui.views.RowsDataModule
import ca.etsmtl.log.fitnesshabits.ui.views.selector.DropdownSelector
import ca.etsmtl.log.fitnesshabits.ui.views.selector.SelectorPeriodOfTime
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.UserInfo
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.ModuleModal
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.ShowListModal
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.food.CreateModifFoodModal

@Composable
fun UserFoodScreen(userInfo : UserInfo) {
    val openDialogList = remember { mutableStateOf(false)  }
    val openDialogAdd = remember { mutableStateOf(false)  }
    val openDialogEdit = remember { mutableStateOf(false)  }
    val openDialogCreate = remember { mutableStateOf(false)  }
    val openDialogModif = remember { mutableStateOf(false)  }

    val nbOfDay = remember { mutableStateOf(PERIOD_OF_TIME.WEEK)}
    val graphValues = listOf("Calories", "Protéines", "Glucides", "Sucre", "Fibre", "Gras total", "Gras saturé")
    val graphValueSelected = remember { mutableStateOf(graphValues.first())}

    val currentUserFood : MutableState<UserFood?> = remember{ mutableStateOf(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (openDialogList.value) {
            ShowListModal(setShowModal = {openDialogList.value = false}) {
                ShowRows(userFoods = userInfo.userFoods.value, 36500){
                    currentUserFood.value = it
                    openDialogEdit.value = true
                }
            }
        }

        if (openDialogAdd.value) {
            ModuleModal(
                R.drawable.icon_food,
                "Nourriture",
                R.color.food,
                { UserFoodModal() }
            )
            {
                openDialogAdd.value = false
            }
        }

        if (openDialogEdit.value) {
            ModuleModal(
                R.drawable.icon_food,
                "Nourriture",
                R.color.food,
                { UserFoodModal(userFood = currentUserFood.value) }
            )
            {
                openDialogEdit.value = false
            }
        }

        if (openDialogCreate.value) {
            ModuleModal(
                R.drawable.icon_food,
                "Nourriture",
                R.color.food,
                { CreateModifFoodModal() }
            )
            {
                FitHabData.getFoods()
                FitHabData.getCreatedByFoods()
                openDialogCreate.value = false
            }
        }
        if (openDialogModif.value) {
            ModuleModal(
                R.drawable.icon_food,
                "Nourriture",
                R.color.food,
                { CreateModifFoodModal(modifier = Modifier.fillMaxHeight(), create = false) }
            )
            {
                FitHabData.getFoods()
                FitHabData.getCreatedByFoods()
                openDialogModif.value = false
            }
        }

        DropdownSelector(
            options = graphValues,
            backgroundColor = colorResource(id = R.color.food),
            textColor = Color.White,
            setOptionSelected = {
                graphValueSelected.value = it
            }
        )

        Graph(userFoods = userInfo.userFoods.value, nbOfDay.value, graphValueSelected.value)

        SelectorPeriodOfTime(color = colorResource(id = R.color.food)) {
            nbOfDay.value = it
        }

        Column(
            Modifier.fillMaxHeight(0.6f)
        ) {
            ShowRows(userFoods = userInfo.userFoods.value, nbOfDay.value){
                currentUserFood.value = it
                openDialogEdit.value = true
            }
        }

        Column(
            Modifier.fillMaxSize()
        ) {
            Row(modifier = Modifier.padding(vertical = 5.dp)) {
                Button(
                    onClick = {
                        FitHabData.getFoods()
                        openDialogAdd.value = true
                    },
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.White)
                ) {
                    Text(text = "Ajouter")
                }
                Button(
                    onClick = { userInfo.userFoods.value.isNotEmpty().let { openDialogList.value = true } },
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
                        FitHabData.getCreatedByFoods()
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
private fun Graph(userFoods: List<UserFood>, nbOfDay: Int, graphValueSelected : String) {
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

        val filteredListUserFood : List<UserFood> = userFoods.filter {
            it.timestamp.isInLastXDays(nbOfDay)
        }.sortedByDescending { it.timestamp }

        Log.d("nemo filteredListUserFood", filteredListUserFood.toString())

        val tempsPoints = (0 until nbOfDay).map { 0f }.toMutableList()
        tempsPoints.apply {
            filteredListUserFood.forEach {
                when(graphValueSelected){
                    //listOf("Calories", "Protéines", "Glucides", "Sucre", "Fibre", "Gras total", "Gras saturé")
                    "Calories" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat() * it.food.calories.toFloat()
                    "Protéines" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat() * it.food.protein
                    "Glucides" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat() * it.food.carbs
                    "Sucre" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat() * it.food.sugar
                    "Fibre" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat() * it.food.fiber
                    "Gras total" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat() * it.food.totalFat
                    "Gras saturé" -> this[it.timestamp.nbOfDaySinceToday()] += it.numberOfConsumption.toFloat() * it.food.saturatedFat
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
            color = colorResource(id = R.color.food)
        )
        )
    }
}

@Composable
private fun ShowRows(userFoods : List<UserFood>, nbOfDay : Int, editModal:(UserFood) -> Unit) {
    val rowsDataUiState = mutableListOf<RowDataUiState>()

    userFoods.filter {
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

private fun delete(userFood: UserFood) {
    FitHabData.deleteUserFood(userFood.idUserFood)
}

@Composable
private fun RowContent(userFood: UserFood) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(start = 4.dp),
            fontSize = 14.sp,
            text = "${userFood.numberOfConsumption.toFloat()}x ${userFood.food.foodName} ${userFood.food.amount}${userFood.food.unit}"
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            fontSize = 12.sp,
            text = "Calories: ${userFood.food.calories * userFood.numberOfConsumption.toFloat()}, " +
                "Glucides: ${userFood.food.carbs * userFood.numberOfConsumption.toFloat()}g, " +
                "Protéines: ${userFood.food.protein * userFood.numberOfConsumption.toFloat()}g, " +
                "Sucre: ${userFood.food.sugar * userFood.numberOfConsumption.toFloat()}g, " +
                "Fibre: ${userFood.food.fiber * userFood.numberOfConsumption.toFloat()}g, " +
                "Gras total: ${userFood.food.totalFat * userFood.numberOfConsumption.toFloat()}g, " +
                "Gras saturé: ${userFood.food.saturatedFat * userFood.numberOfConsumption.toFloat()}g"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RowContentPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        RowContent(Utilities.userInfoForPreview().userFoods.value.first())
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UserFoodScreenPreview() {
    UserFoodScreen(userInfo = Utilities.userInfoForPreview())
}