package com.example.fitnessandroid.homescreen.activity

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
import com.example.fitnessandroid.composable.searchbar.SearchBarComposable
import com.example.fitnessandroid.fithabdata.FitHabData
import java.util.*
import kotlin.collections.HashMap

@Composable
fun CreateModifActivityModal(
    modifier : Modifier = Modifier,
    create: Boolean = true
) {
    val (activityName, setActivityName) = remember { mutableStateOf("") }
    val researchText = remember { mutableStateOf("") }
    val selectedIdActivity = remember { mutableStateOf("") }

    val isDeleted = remember { mutableStateOf(false)}
    if(isDeleted.value) {
        isDeleted.value = false
        selectedIdActivity.value = ""
    }

    Column (modifier = modifier.fillMaxWidth() ){
        if(!create) {
            SearchBarComposable {text ->
                researchText.value = text
                selectedIdActivity.value = ""
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
            ) {
                items(FitHabData.uiState.value.createdActivities.value.filter{ it.activityName.lowercase().contains(researchText.value.lowercase()) }) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(2.dp)
                            .background(if (selectedIdActivity.value == it.idActivity) Color.LightGray else Color.White)
                            .clickable {
                                selectedIdActivity.value = it.idActivity
                                setActivityName(it.activityName)
                            }
                    ) {
                        RowsInformation(activity = it) {
                            isDeleted.value = true
                        }
                    }
                }
            }
        }
        if(selectedIdActivity.value != "" || create) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                TextFieldComposable(
                    modifier = Modifier.fillMaxWidth(),
                    value = activityName,
                    onValueChange = setActivityName,
                    label = "Nom",
                    color = Color.Black
                )

                Spacer(modifier = Modifier.padding(4.dp))
            }
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    val json = hashMapOf(
                        Pair("activityName", activityName),
                    )
                    if(create) {
                        createActivity(json)
                    } else {
                        json["idActivity"] = selectedIdActivity.value
                        updateActivity(json)
                    }
                },
                enabled = (activityName.isNotBlank()),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.activity)),
            ) {
                Text(
                    text = if(create) "Créer".uppercase() else "Modifier".uppercase(),
                    color = Color.White
                )
            }
        }
    }
}

private fun createActivity(json : HashMap<String,String>) {
    FitHabData.createActivity(json)
}

private fun updateActivity(json : HashMap<String,String>) {
    FitHabData.updateActivity(json)
}

private fun deleteActivity(json : HashMap<String,String>) {
    FitHabData.deleteActivity(json)
}

@Composable
private fun RowsInformation(activity: Activity, isDeleted : () -> Unit) {
    Row() {
        Column(modifier = Modifier.fillMaxWidth(0.85f)) {
            Text(
                text = activity.activityName,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
        CircularButtonWithIcon(
            action = {
                deleteActivity(json = hashMapOf(Pair("idActivity", activity.idActivity)))
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
    FitHabData.uiState.value.createdActivities.value = listOf(Utilities.fitHabActivityForPreview(), Utilities.fitHabActivityForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        CreateModifActivityModal(it, create = true)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showSystemUi = true)
@Composable
private fun UserAlcoholModalModifPreview() {
    FitHabData.uiState.value.createdActivities.value = listOf(Utilities.fitHabActivityForPreview(), Utilities.fitHabActivityForPreview())
    PreviewComposable(backgroundColor = Color.White) {
        CreateModifActivityModal(it, create = false)
    }
}