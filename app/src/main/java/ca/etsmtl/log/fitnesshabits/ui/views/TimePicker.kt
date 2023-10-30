package ca.etsmtl.log.fitnesshabits.ui.views

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ca.etsmtl.log.fitnesshabits.helper.Utilities.toStringHM
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable
import java.util.*

@Composable
fun TimePicker(
    modifier: Modifier = Modifier,
    defaultDate : Date? = null,
    color : Color = Color.Magenta,
    textColor : Color = Color.Black,
    setTime: (Date) -> Unit,
){
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    defaultDate?.let { calendar.time = it }

    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    var selectedTimeText by remember { mutableStateOf(defaultDate?.toStringHM() ?: calendar.time.toStringHM()) }

    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour: Int, selectedMinute: Int ->
            calendar.set(
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DAY_OF_MONTH],
                selectedHour,
                selectedMinute
            )
            selectedTimeText = calendar.time.toStringHM()
            setTime(calendar.time)
        }, hour, minute, true
    )

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(
            text = if (selectedTimeText.isNotEmpty()) {
                "Heure : $selectedTimeText"
            } else {
                "00:00"
            }
        )

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = textColor),
            onClick = {
                timePicker.show()
            }
        ) {
            Text(text = "Heure")
        }
    }
}

@Composable
@Preview
fun TimePickerPreview() {
    PreviewComposable(backgroundColor = Color.White) { modifier ->
        val calendar = Calendar.getInstance()
        val timeText = remember { mutableStateOf(calendar.time) }
        TimePicker(modifier){
            timeText.value = it
        }
    }
}