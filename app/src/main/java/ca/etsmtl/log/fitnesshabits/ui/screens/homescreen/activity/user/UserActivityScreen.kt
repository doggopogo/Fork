package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.activity.user

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
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.helper.Utilities
import ca.etsmtl.log.fitnesshabits.helper.Utilities.isInLastXDays
import ca.etsmtl.log.fitnesshabits.helper.Utilities.nbOfDaySinceToday
import ca.etsmtl.log.fitnesshabits.helper.Utilities.toUpperCase
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable
import ca.etsmtl.log.fitnesshabits.toDelete.common.config.PERIOD_OF_TIME
import ca.etsmtl.log.fitnesshabits.ui.views.GraphComposable
import ca.etsmtl.log.fitnesshabits.ui.views.GraphComposableUiState
import ca.etsmtl.log.fitnesshabits.ui.views.RowDataUiState
import ca.etsmtl.log.fitnesshabits.ui.views.RowsDataModule
import ca.etsmtl.log.fitnesshabits.ui.views.selector.SelectorPeriodOfTime
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.UserInfo
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.ModuleModal
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.ShowListModal
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.activity.CreateModifActivityModal

@Composable
fun UserActivityScreen(userInfo : UserInfo) {
    val openDialogList = remember { mutableStateOf(false)  }
    val openDialogAdd = remember { mutableStateOf(false)  }
    val openDialogEdit = remember { mutableStateOf(false)  }
    val openDialogCreate = remember { mutableStateOf(false)  }
    val openDialogModif = remember { mutableStateOf(false)  }
    val nbOfDay = remember { mutableStateOf(PERIOD_OF_TIME.WEEK)}
    val currentUserActivity : MutableState<UserActivity?> = remember{ mutableStateOf(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (openDialogList.value) {
            ShowListModal(setShowModal = {openDialogList.value = false}) {
                ShowRows(userActivity = userInfo.userActivity.value, 36500) {
                    currentUserActivity.value = it
                    openDialogEdit.value = true
                }
            }
        }

        if (openDialogAdd.value) {
            ModuleModal(
                R.drawable.icon_activity,
                "Sport",
                R.color.activity,
                { UserActivityModal() }
            )
            {
                openDialogAdd.value = false
            }
        }
        if (openDialogEdit.value) {
            ModuleModal(
                R.drawable.icon_activity,
                "Sport",
                R.color.activity,
                { UserActivityModal(userActivity = currentUserActivity.value) }
            )
            {
                openDialogEdit.value = false
            }
        }

        if (openDialogCreate.value) {
            ModuleModal(
                R.drawable.icon_activity,
                "Sport",
                R.color.activity,
                { CreateModifActivityModal() }
            )
            {
                FitHabData.getCreatedByActivities()
                FitHabData.getActivities()
                openDialogCreate.value = false
            }
        }
        if (openDialogModif.value) {
            ModuleModal(
                R.drawable.icon_activity,
                "Sport",
                R.color.activity,
                { CreateModifActivityModal(modifier = Modifier.fillMaxHeight(), create = false) }
            )
            {
                FitHabData.getCreatedByActivities()
                FitHabData.getActivities()
                openDialogModif.value = false
            }
        }

        Graph(userActivity = userInfo.userActivity.value, nbOfDay.value)

        SelectorPeriodOfTime(color = colorResource(id = R.color.activity)) {
            nbOfDay.value = it
        }

        Column(
            Modifier.fillMaxHeight(0.6f)
        ) {
            ShowRows(userActivity = userInfo.userActivity.value, nbOfDay.value) {
                currentUserActivity.value = it
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
private fun Graph(userActivity: List<UserActivity>, nbOfDay: Int) {
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

        val filteredListUserActivity : List<UserActivity> = userActivity.filter {
            it.timestamp.isInLastXDays(nbOfDay)
        }.sortedByDescending { it.timestamp }

        Log.d("nemo filteredListUserActivity", filteredListUserActivity.toString())

        val tempsPoints = (0 until nbOfDay).map { 0f }.toMutableList()
        tempsPoints.apply {
            filteredListUserActivity.forEach {
                this[it.timestamp.nbOfDaySinceToday()] += it.activityDuration.toFloat()
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
            color = colorResource(id = R.color.activity)
        )
        )
    }
}

@Composable
private fun ShowRows(userActivity : List<UserActivity>, nbOfDay : Int, editModal:(UserActivity) -> Unit) {
    val rowsDataUiState = mutableListOf<RowDataUiState>()

    userActivity.filter {
        it.timestamp.isInLastXDays(nbOfDay)
    }.sortedByDescending { it.timestamp }.map{
        rowsDataUiState.add(
            RowDataUiState(
                title = it.timestamp.toString(),
                edit = {
                    editModal(it)
                },
                delete = { delete(it) },
                content = { RowContent(it) }
            )
        )
    }

    RowsDataModule(Modifier,rowsDataUiState)

}

private fun delete(userActivity: UserActivity) {
    FitHabData.deleteUserActivity(userActivity.idUserActivity)
}

@Composable
private fun RowContent(userActivity: UserActivity) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = userActivity.activity.activityName)
        Text(text = "${userActivity.activityDuration}h, Effort : ${userActivity.activityIntensity.toUpperCase()}")
    }
}

@Preview(showBackground = true)
@Composable
private fun RowContentPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        RowContent(Utilities.userInfoForPreview().userActivity.value.first())
    }
}

@Preview(showSystemUi = true)
@Composable
private fun UserActivityScreenPreview() {
    UserActivityScreen(userInfo = Utilities.userInfoForPreview())
}