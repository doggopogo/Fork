package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.weight.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import ca.etsmtl.log.fitnesshabits.ui.views.DatePickerComposable
import ca.etsmtl.log.fitnesshabits.ui.views.TextFieldNumberComposable
import ca.etsmtl.log.fitnesshabits.ui.views.TimePicker
import ca.etsmtl.log.fitnesshabits.ui.views.selector.DropdownSelector
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.ui.screens.login.HomepageButton
import ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.drawer.DrawerActivity
import java.util.*
import kotlin.collections.HashMap

@Composable
fun UserWeightModal(
    modifier : Modifier = Modifier,
    userWeight: UserWeight? = null
) {
    val weightInput = remember { mutableStateOf(userWeight?.weight?.toString() ?:"") }
    val calendar = Calendar.getInstance()

    val graphValues = FitHabData.fitHabConst.WeightEnum
    val graphValueSelected = remember { mutableStateOf(graphValues.first())}

    val dateText = remember { mutableStateOf(userWeight?.timestamp ?: calendar.time) }
    val timeText = remember { mutableStateOf(userWeight?.timestamp ?: calendar.time) }

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextFieldNumberComposable(
            value = weightInput.value,
            onValueChange = {
                weightInput.value = it
            },
            label = "Poids",
            color = Color.Black
        )

        Spacer(Modifier.padding(4.dp))

        DropdownSelector(
            options = graphValues,
            backgroundColor = colorResource(id = R.color.weight),
            textColor = Color.White,
            setOptionSelected = {
                graphValueSelected.value = it
            }
        )

        Spacer(Modifier.padding(4.dp))

        Row(modifier = Modifier
            .padding(start = 20.dp)
            .align(Alignment.CenterHorizontally)){
            DatePickerComposable(
                modifier = Modifier.size(100.dp),
                defaultDate = userWeight?.timestamp,
                color = colorResource(id = R.color.weight),
                textColor = Color.White
            ) {
                dateText.value = it
            }
            TimePicker(
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 20.dp),
                defaultDate = userWeight?.timestamp,
                color = colorResource(id = R.color.weight),
                textColor = Color.White
            ) {
                timeText.value = it
            }
        }

        HomepageButton(
            text = userWeight?.let { "Modifier".uppercase() } ?: "Ajouter".uppercase(),
            onClick = {
                val json = hashMapOf<String,String>()
                if(weightInput.value.toInt() > 0) {
                    json["weight"] = weightInput.value
                    json["preferenceUnit"] = graphValueSelected.value

                    json["timestamp"] = Utilities.dateAndTimeCalendar(dateText.value, timeText.value).toStringYMDTHM()

                    if(userWeight == null) {
                        addUserWeight(json)
                    } else {
                        userWeight.idUserWeight.let { json.put("idUserWeight", it) }
                        editUserWeight(json)
                    }
                } else {
                    DrawerActivity.showErrorMessage("Le poids doit être plus grand que 0")
                }

            },
            modifier = Modifier
                .padding(start = 5.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally),
            buttonColors = ButtonDefaults.buttonColors(colorResource(R.color.weight)),
            textColor = Color.White,
            enabled = (weightInput.value.isNotEmpty())
        )
    }
}

private fun editUserWeight(json: HashMap<String, String>) {
    FitHabData.updateUserWeight(json)
}

private fun addUserWeight(json: HashMap<String, String>) {
    FitHabData.createUserWeight(json)
}

@Preview(showSystemUi = true)
@Composable
fun UserWeightModalCreatePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        UserWeightModal(it)
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserWeightModalUpdatePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        UserWeightModal(it, Utilities.userInfoForPreview().userWeight.value.first())
    }
}