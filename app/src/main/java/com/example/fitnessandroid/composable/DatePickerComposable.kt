package com.example.fitnessandroid.composable

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import com.example.fitnessandroid.Utilities.toStringYMD
import com.example.fitnessandroid.common.PreviewComposable
import java.util.*

@Composable
fun DatePickerComposable(
    modifier: Modifier = Modifier,
    defaultDate : Date? = null,
    color : Color = Color.Magenta,
    textColor : Color = Color.Black,
    selectedDate: (Date) -> Unit
){
    val context = LocalContext.current
    val calendarToday = Calendar.getInstance()
    val calendar = Calendar.getInstance()

    defaultDate?.let { calendar.time = it }

    var selectedDateText by remember { mutableStateOf(defaultDate?.toStringYMD() ?: calendar.time.toStringYMD()) }

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            calendar.set(selectedYear,selectedMonth,selectedDayOfMonth)
            selectedDateText = calendar.time.toStringYMD()
            selectedDate(calendar.time)
        }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
    )

    datePicker.datePicker.maxDate = calendarToday.timeInMillis

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(text = "Date : $selectedDateText")

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = color, contentColor = textColor),
            onClick = {
                datePicker.show()
            }
        ) {
            Text(text = "Date")
        }
    }
}

@Composable
@Preview
fun DatePickerPreview() {
    PreviewComposable(backgroundColor = Color.White) { modifier ->
        val calendar = Calendar.getInstance()
        val dateText = remember { mutableStateOf(calendar.time) }
        DatePickerComposable(modifier){
            dateText.value = it
        }
    }
}