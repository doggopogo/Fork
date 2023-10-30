package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.bloodSugar.user

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

@Composable
fun UserBloodSugarScreen(userInfo : UserInfo) {
    val openDialogList = remember { mutableStateOf(false)  }
    val openDialogAdd = remember { mutableStateOf(false)  }
    val openDialogEdit = remember { mutableStateOf(false)  }
    val nbOfDay = remember { mutableStateOf(PERIOD_OF_TIME.WEEK)}

    val graphValues = FitHabData.fitHabConst.BloodSugarEnum
    val graphValueSelected = remember { mutableStateOf(graphValues.first())}
    val currentUserBloodSugar : MutableState<UserBloodSugar?> = remember{ mutableStateOf(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (openDialogList.value) {
            ShowListModal(setShowModal = {openDialogList.value = false}) {
                ShowRows(
                    userBloodSugar = userInfo.userBloodSugar.value,
                    36500
                ){
                    currentUserBloodSugar.value = it
                    openDialogEdit.value = true
                }
            }
        }
        if (openDialogAdd.value) {
            ModuleModal(
                R.drawable.icon_bloodsugar,
                "Glycémie",
                R.color.bloodSugar ,
                { UserBloodSugarModal() }
            )
            {
                openDialogAdd.value = false
            }
        }
        if (openDialogEdit.value) {
            ModuleModal(
                R.drawable.icon_bloodsugar,
                "Glycémie",
                R.color.bloodSugar ,
                { UserBloodSugarModal(userBloodSugar = currentUserBloodSugar.value) }
            )
            {
                openDialogEdit.value = false
            }
        }

        DropdownSelector(
            options = graphValues,
            backgroundColor = colorResource(id = R.color.bloodSugar),
            textColor = Color.White,
            setOptionSelected = {
                graphValueSelected.value = it
            }
        )

        Graph(userBloodSugar = userInfo.userBloodSugar.value, nbOfDay.value, graphValueSelected.value)

        SelectorPeriodOfTime(color = colorResource(id = R.color.bloodSugar)) {
            nbOfDay.value = it
        }

        Column(
            Modifier.fillMaxHeight(0.75f)
        ) {
            ShowRows(userBloodSugar = userInfo.userBloodSugar.value, nbOfDay.value){
                currentUserBloodSugar.value = it
                openDialogEdit.value = true
            }
        }

        Row(
            Modifier.fillMaxSize()
        ) {
            Button(
                onClick = {openDialogAdd.value = true},
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(text = "Ajouter")
            }

            Button(
                onClick = { userInfo.userBloodSugar.value.isNotEmpty().let { openDialogList.value = true } },
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(text = "Afficher toute la liste")
            }
        }
    }
}

@Composable
private fun Graph(userBloodSugar :  List<UserBloodSugar>, nbOfDay: Int, graphValueSelected : String) {
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

        val filteredListUserBloodSugar : List<UserBloodSugar> = userBloodSugar.filter {
            it.timestamp.isInLastXDays(nbOfDay)
        }.sortedByDescending { it.timestamp }

        val tempsPoints = (0 until nbOfDay).map { 0f }.toMutableList()
        tempsPoints.apply {
            filteredListUserBloodSugar.forEach {    userBloodSugar ->
                var bloodSugar :Number = userBloodSugar.bloodSugar
                FitHabData.uiState.value.userInfo.user?.let {
                    bloodSugar = if(userBloodSugar.preferenceUnit == graphValueSelected) {//mmol/L == mmol/L or mg/dl == mg/dl
                        userBloodSugar.bloodSugar
                    } else {
                        if(userBloodSugar.preferenceUnit == "mg/dl"){//mmol/L from mg/dl
                            userBloodSugar.bloodSugar.toFloat() * 0.0555f
                        } else { // mg/dl from mmol/L
                            userBloodSugar.bloodSugar.toFloat() * 18f
                        }
                    }
                }
                this[userBloodSugar.timestamp.nbOfDaySinceToday()] = bloodSugar.toFloat()
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
            limitLines = if (graphValueSelected == "mmol/L") listOf(4f,10f) else listOf(72f,180f),
            color = colorResource(id = R.color.bloodSugar)
        )
        )
    }
}

@Composable
private fun ShowRows(userBloodSugar : List<UserBloodSugar>, nbOfDay : Int, editModal:(UserBloodSugar) -> Unit) {
    val rowsDataUiState = mutableListOf<RowDataUiState>()

    userBloodSugar.filter {
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

private fun delete(userBloodSugar: UserBloodSugar) {
    FitHabData.deleteUserBloodSugar(userBloodSugar.idUserBloodSugar)
}

@Composable
private fun RowContent(userBloodSugar: UserBloodSugar) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Taux de glycémie : " + userBloodSugar.bloodSugar + " " + userBloodSugar.preferenceUnit)
    }
}

@Preview(showBackground = true)
@Composable
private fun RowContentPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        RowContent(Utilities.userInfoForPreview().userBloodSugar.value.first())
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UserBloodSugarScreenPreview() {
    UserBloodSugarScreen(userInfo = Utilities.userInfoForPreview())
}