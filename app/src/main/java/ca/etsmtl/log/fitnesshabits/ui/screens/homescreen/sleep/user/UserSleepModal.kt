package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.sleep.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
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
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.helper.Utilities
import ca.etsmtl.log.fitnesshabits.helper.Utilities.dateAndTimeCalendar
import ca.etsmtl.log.fitnesshabits.helper.Utilities.toStringYMDTHM
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable
import ca.etsmtl.log.fitnesshabits.ui.views.DatePickerComposable
import ca.etsmtl.log.fitnesshabits.ui.views.NumberPickerComposable
import ca.etsmtl.log.fitnesshabits.ui.views.TimePicker
import ca.etsmtl.log.fitnesshabits.ui.views.selector.SelectorRadioButtonImage
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.ui.screens.login.HomepageButton
import java.util.*

@Composable
fun UserSleepModal(
    modifier: Modifier = Modifier,
    userSleep: UserSleep? = null
) {
    val calendarStart = Calendar.getInstance()
    userSleep?.startSleep?.let { calendarStart.time = it }

    val calendarEnd = Calendar.getInstance()
    userSleep?.endSleep?.let { calendarEnd.time = it }

    val startDateText = remember { mutableStateOf(userSleep?.startSleep ?: calendarStart.time) }
    val startTimeText = remember { mutableStateOf(userSleep?.startSleep ?: calendarStart.time) }

    val endDateText = remember { mutableStateOf(userSleep?.endSleep ?: calendarEnd.time) }
    val endTimeText = remember { mutableStateOf(userSleep?.endSleep ?: calendarEnd.time) }

    val numberOfAwakening = remember { mutableStateOf(userSleep?.numberOfAwakening ?: 0) }

    val list = listOf(
        Pair("Reposé", R.drawable.emoticone_happy),
        Pair("Heureux", R.drawable.emoticone_very_happy),
        Pair("Fatigué", R.drawable.emoticone_disappointed),
        Pair("En colère", R.drawable.emoticone_angry)
    )
    val (optionSelected, setOptionSelected) = remember {
        mutableStateOf(userSleep?.mindset?.let {
            Utilities.convertToShowMindset(
                it
            )
        }
            ?: Utilities.convertToShowMindset(
                FitHabData.fitHabConst.SleepMindsetEnum.first()
            )
        )
    }

    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Endormi à",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Row(
            Modifier.align(Alignment.CenterHorizontally)
        ) {
            DatePickerComposable(
                modifier = Modifier.size(100.dp),
                defaultDate = userSleep?.startSleep,
                color = colorResource(id = R.color.sleep),
                textColor = Color.White
            ) {
                startDateText.value = it
            }
            TimePicker(
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 20.dp),
                defaultDate = userSleep?.startSleep,
                color = colorResource(id = R.color.sleep),
                textColor = Color.White
            ) {
                startTimeText.value = it
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray, thickness = 1.dp
        )
        Spacer(Modifier.padding(2.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Réveillé à",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Row(
            Modifier.align(Alignment.CenterHorizontally)
        ) {
            DatePickerComposable(
                modifier = Modifier.size(100.dp),
                defaultDate = userSleep?.endSleep,
                color = colorResource(id = R.color.sleep),
                textColor = Color.White
            ) {
                endDateText.value = it
            }
            TimePicker(
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 20.dp),
                defaultDate = userSleep?.endSleep,
                color = colorResource(id = R.color.sleep),
                textColor = Color.White
            ) {
                endTimeText.value = it
            }
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray, thickness = 1.dp
        )
        Spacer(Modifier.padding(8.dp))
        Row(
            Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 50.dp),
                text = "Nombre de réveils",
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.padding(start = 20.dp))
            NumberPickerComposable(
                modifier = Modifier.size(110.dp),
                startValueNumber = numberOfAwakening.value.toInt(),
                minValueNumber = 0,
                maxValueNumber = 20
            ) {
                numberOfAwakening.value = it
            }
            Spacer(modifier = Modifier.padding(start = 20.dp))
            Text(
                modifier = Modifier.padding(vertical = 50.dp),
                text = "Fois ",
                fontSize = 12.sp
            )
        }
        Spacer(Modifier.padding(8.dp))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray, thickness = 1.dp
        )
        Text(
            text = "État d'esprit au réveil",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        SelectorRadioButtonImage(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            options = list,
            optionSelected = optionSelected,
            setOptionSelected = setOptionSelected
        )
        HomepageButton(
            text = userSleep?.let { "Modifier".uppercase() } ?: "Ajouter".uppercase(),
            onClick = {
                val json = hashMapOf<String, String>().apply {
                    val newCalendarStart =
                        dateAndTimeCalendar(startDateText.value, startTimeText.value)
                    val newCalendarEnd = dateAndTimeCalendar(endDateText.value, endTimeText.value)

                    put("startSleep", newCalendarStart.toStringYMDTHM())
                    put("endSleep", newCalendarEnd.toStringYMDTHM())
                    put("numberOfAwakening", numberOfAwakening.value.toString())

                    put("mindset", Utilities.convertToSendMindset(optionSelected))

                    val diff: Long = newCalendarEnd.time - newCalendarStart.time
                    val seconds = diff / 1000
                    val minutes = seconds / 60
                    val hours = minutes / 60

                    put("totalSleepTime", hours.toInt().toString())
                }

                if (userSleep == null) {
                    addUserSleep(json)
                } else {
                    userSleep.idUserSleep.let { json.put("idUserSleep", it) }
                    editUserSleep(json)
                }
            },
            modifier = Modifier
                .padding(start = 5.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally),
            buttonColors = ButtonDefaults.buttonColors(colorResource(R.color.sleep)),
            textColor = Color.White
        )
    }
}

private fun editUserSleep(json: HashMap<String, String>) {
    FitHabData.updateUserSleep(json)
}

private fun addUserSleep(json: HashMap<String, String>) {
    FitHabData.createUserSleep(json)
}

@Preview(showBackground = true)
@Composable
fun UserSleepModalCreatePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        UserSleepModal(it)
    }
}

@Preview(showBackground = true)
@Composable
fun UserSleepModalUpdatePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        UserSleepModal(it, Utilities.userInfoForPreview().userSleep.value.first())
    }
}