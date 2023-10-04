package com.example.fitnessandroid.homescreen.toilet.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.fitnessandroid.composable.selector.SelectorPeriodOfTime
import com.example.fitnessandroid.fithabdata.FitHabData
import com.example.fitnessandroid.fithabdata.UserInfo
import com.example.fitnessandroid.homescreen.ModuleModal
import com.example.fitnessandroid.homescreen.ShowListModal

@Composable
fun UserToiletScreen(userInfo : UserInfo) {
    val openDialogList = remember { mutableStateOf(false)  }
    val openDialogAdd = remember { mutableStateOf(false)  }
    val openDialogEdit = remember { mutableStateOf(false)  }

    val nbOfDay = remember { mutableStateOf(PERIOD_OF_TIME.WEEK)}
    val currentUserToilet : MutableState<UserToilet?> = remember{ mutableStateOf(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (openDialogList.value) {
            ShowListModal(setShowModal = {openDialogList.value = false}) {
                ShowRows(
                    userToilet = userInfo.userToilet.value,
                    36500
                ){
                    currentUserToilet.value = it
                    openDialogEdit.value = true
                }
            }
        }
        if (openDialogAdd.value) {
            ModuleModal(
                R.drawable.icon_toilet,
                "Toilette",
                R.color.toilet ,
                { UserToiletModal() }
            )
            {
                openDialogAdd.value = false
            }
        }

        if (openDialogEdit.value) {
            ModuleModal(
                R.drawable.icon_toilet,
                "Toilette",
                R.color.toilet ,
                { UserToiletModal(userToilet = currentUserToilet.value) }
            )
            {
                openDialogEdit.value = false
            }
        }

        Graph(userToilet = userInfo.userToilet.value, nbOfDay.value)

        SelectorPeriodOfTime(color = colorResource(id = R.color.toilet)) {
            nbOfDay.value = it
        }

        Column(
            Modifier.fillMaxHeight(0.75f)
        ) {
            ShowRows(userToilet = userInfo.userToilet.value, nbOfDay.value){
                currentUserToilet.value = it
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
                onClick = { userInfo.userToilet.value.isNotEmpty().let { openDialogList.value = true } },
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(text = "Afficher toute la liste")
            }
        }
    }
}

@Composable
private fun Graph(userToilet :  List<UserToilet>, nbOfDay: Int) {
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

        val filteredListUserAlcohol : List<UserToilet> = userToilet.filter {
            it.timestamp.isInLastXDays(nbOfDay)
        }.sortedByDescending { it.timestamp }

        val tempsPoints = (0 until nbOfDay).map { 0f }.toMutableList()
        tempsPoints.apply {
            filteredListUserAlcohol.forEach {
                this[it.timestamp.nbOfDaySinceToday()] += it.numberOfUrine.toFloat()
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
            color = colorResource(id = R.color.toilet)
        ))
    }
}

@Composable
private fun ShowRows(userToilet : List<UserToilet>, nbOfDay : Int, editModal:(UserToilet) -> Unit) {
    val rowsDataUiState = mutableListOf<RowDataUiState>()

    userToilet.filter {
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

private fun delete(userToilet: UserToilet) {
    FitHabData.deleteUserToilet(userToilet.idUserToilet)
}

@Composable
private fun RowContent(userToilet: UserToilet) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Nombre d'urine: " + userToilet.numberOfUrine)
        if (userToilet.fecesType.toInt() > 0) Text(text = "Type de selles: " + userToilet.fecesType)
    }
}

@Preview(showBackground = true)
@Composable
private fun RowContentPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        RowContent(Utilities.userInfoForPreview().userToilet.value.first())
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UserToiletScreenPreview() {
   UserToiletScreen(userInfo = Utilities.userInfoForPreview())
}