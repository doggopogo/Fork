package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.bloodSugar.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
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
import ca.etsmtl.log.fitnesshabits.helper.Utilities.toStringYMDTHM
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable
import ca.etsmtl.log.fitnesshabits.ui.views.selector.DropdownSelector
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.ui.screens.login.HomepageButton
import ca.etsmtl.log.fitnesshabits.ui.views.DatePickerComposable
import ca.etsmtl.log.fitnesshabits.ui.views.TextFieldNumberComposable
import ca.etsmtl.log.fitnesshabits.ui.views.TimePicker
import java.util.*
import kotlin.collections.HashMap

@Composable
fun UserBloodSugarModal(
    modifier : Modifier = Modifier,
    userBloodSugar: UserBloodSugar? = null
) {
    val calendar = Calendar.getInstance()

    val dateText = remember { mutableStateOf(userBloodSugar?.timestamp ?: calendar.time) }
    val timeText = remember { mutableStateOf(userBloodSugar?.timestamp ?: calendar.time) }

    val unitSelected  = remember { mutableStateOf(userBloodSugar?.preferenceUnit ?: FitHabData.fitHabConst.BloodSugarEnum.first()) }

    val valueSelected = remember { mutableStateOf(userBloodSugar?.bloodSugar?.toString() ?: "")}

    Column {
        Column(
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextFieldNumberComposable(
                value = valueSelected.value,
                onValueChange = {
                    valueSelected.value = it
                },
                label = "Entrée",
                color = Color.Black
            )

            DropdownSelector(
                first = userBloodSugar?.preferenceUnit,
                backgroundColor = colorResource(id = R.color.bloodSugar),
                textColor = Color.White,
                options = FitHabData.fitHabConst.BloodSugarEnum,
                setOptionSelected = {
                    unitSelected.value = it
                }
            )
        }
        Row(
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ){
            DatePickerComposable(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(100.dp),
                defaultDate = userBloodSugar?.timestamp,
                color = colorResource(id = R.color.bloodSugar),
                textColor = Color.White,
            ) {
                dateText.value = it
            }
            TimePicker(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(100.dp)
                    .padding(start = 20.dp),
                defaultDate = userBloodSugar?.timestamp,
                color = colorResource(id = R.color.bloodSugar),
                textColor = Color.White,
            ){
                timeText.value = it
            }
        }

        HomepageButton(
            text = userBloodSugar?.let { "Modifier".uppercase() } ?: "Ajouter".uppercase(),
            onClick = {
                val json = hashMapOf<String,String>()
                json["bloodSugar"] = valueSelected.value
                json["preferenceUnit"] = unitSelected.value
                json["timestamp"] = Utilities.dateAndTimeCalendar(dateText.value, timeText.value).toStringYMDTHM()
                if(userBloodSugar == null) {
                    addUserBloodSugar(json)
                } else {
                    userBloodSugar.idUserBloodSugar.let { json.put("idUserBloodSugar", it) }
                    editUserBloodSugar(json)
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            buttonColors = ButtonDefaults.buttonColors(colorResource(R.color.bloodSugar)),
            textColor = Color.White,
            enabled = (valueSelected.value.isNotEmpty())
        )
    }
}

private fun editUserBloodSugar(json : HashMap<String,String>) {
    FitHabData.updateUserBloodSugar(json)
}

private fun addUserBloodSugar(json : HashMap<String,String>) {
    FitHabData.createUserBloodSugar(json)
}

@Preview(showSystemUi = true)
@Composable
fun UserBloodSugarCreatePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        UserBloodSugarModal(it)
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserBloodSugarUpdatePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        UserBloodSugarModal(it, Utilities.fitHabUiStateForPreview().userInfo.userBloodSugar.value.first())
    }
}