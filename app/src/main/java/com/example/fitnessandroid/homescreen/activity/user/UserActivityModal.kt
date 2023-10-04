package com.example.fitnessandroid.homescreen.activity.user

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
import com.example.fitnessandroid.Utilities.toStringYMDTHM
import com.example.fitnessandroid.common.PreviewComposable
import com.example.fitnessandroid.composable.DatePickerComposable
import com.example.fitnessandroid.composable.TextFieldNumberComposable
import com.example.fitnessandroid.composable.TimePicker
import com.example.fitnessandroid.composable.searchbar.SearchBarComposable
import com.example.fitnessandroid.composable.selector.DropdownSelector
import com.example.fitnessandroid.fithabdata.FitHabData
import com.example.fitnessandroid.homescreen.activity.Activity
import java.util.*
import kotlin.collections.HashMap

@Composable
fun UserActivityModal(
    modifier : Modifier = Modifier,
    userActivity: UserActivity? = null
) {
    val calendar = Calendar.getInstance()
    val dateText = remember { mutableStateOf(userActivity?.timestamp ?: calendar.time) }
    val timeText = remember { mutableStateOf(userActivity?.timestamp ?: calendar.time) }

    val researchText = remember { mutableStateOf("") }
    val selectedIdActivity = remember { mutableStateOf(userActivity?.activity?.idActivity ?: "") }

    val activityDuration = remember { mutableStateOf(userActivity?.activityDuration?.toString() ?: "")}
    val activityIntensity = remember { mutableStateOf(userActivity?.activityIntensity ?:FitHabData.fitHabConst.ActivityIntensityEnum.first())}
    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBarComposable {
            researchText.value = it
            selectedIdActivity.value = ""
            FitHabData.getActivities(hashMapOf(Pair("activityName", researchText.value)))
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ) {
            items(FitHabData.uiState.value.activities.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .background(if (selectedIdActivity.value == it.idActivity) Color.LightGray else Color.White)
                        .clickable {
                            selectedIdActivity.value = it.idActivity
                        }
                ) {
                    RowsInformation(activity = it)
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldNumberComposable(
                value = activityDuration.value,
                onValueChange = {//^[\d.\b]+$
                    activityDuration.value = it
                },
                label = "Durée de l'activité en heures",
                color = Color.Black
            )
            DropdownSelector(
                first = userActivity?.activityIntensity,
                options = FitHabData.fitHabConst.ActivityIntensityEnum,
                capitalize = true,
                backgroundColor = colorResource(id = R.color.activity),
                textColor = Color.White,
                setOptionSelected = {
                    activityIntensity.value = it
                }
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
                    defaultDate = userActivity?.timestamp,
                    color = colorResource(id = R.color.activity),
                    textColor = Color.White
                ){
                    dateText.value = it
                }
                TimePicker(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(100.dp)
                        .padding(start = 20.dp),
                    defaultDate = userActivity?.timestamp,
                    color = colorResource(id = R.color.activity),
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
                    Pair("idActivity", selectedIdActivity.value),
                    Pair("activityDuration", activityDuration.value),
                    Pair("activityIntensity", activityIntensity.value),
                    Pair("timestamp", Utilities.dateAndTimeCalendar(dateText.value, timeText.value).toStringYMDTHM())
                )
                if(userActivity == null) {
                    addUserActivity(json)
                } else {
                    userActivity.idUserActivity.let { json.put("idUserActivity", it) }
                    editUserActivity(json)
                }
            },
            enabled = (selectedIdActivity.value != "" && activityDuration.value.isNotEmpty() && activityIntensity.value.isNotEmpty()),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.activity), contentColor = Color.White),
        ) {
            Text(
                text = userActivity?.let { "Modifier".uppercase() } ?: "Ajouter".uppercase(),
                color = Color.White
            )
        }
    }
}

private fun editUserActivity(json : HashMap<String,String>) {
    FitHabData.updateUserActivity(json)
}

private fun addUserActivity(json : HashMap<String,String>) {
    FitHabData.createUserActivity(json)
}

@Composable
private fun RowsInformation(activity: Activity) {
    Text(
        text = activity.activityName,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )
    Divider(color = Color.LightGray, thickness = 1.dp)
}


@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserAlcoholModalAddPreview() {
    FitHabData.uiState.value.activities.value = listOf(Utilities.fitHabActivityForPreview(), Utilities.fitHabActivityForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        UserActivityModal(it, null)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserAlcoholModalEditPreview() {
    FitHabData.uiState.value.activities.value = listOf(Utilities.fitHabActivityForPreview(), Utilities.fitHabActivityForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        UserActivityModal(it, Utilities.fitHabUiStateForPreview().userInfo.userActivity.value.first())
    }
}