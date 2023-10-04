package com.example.fitnessandroid.composable

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessandroid.common.PreviewComposable
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DateComposable(
    modifier : Modifier = Modifier,
    labelName : String,
    context: Context,
    onBirthdaySelected: (String) -> Unit
) {
    var selectedDate by remember { mutableStateOf("") }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$labelName:",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White
        )
        Spacer(Modifier.width(16.dp))
        Button(
            onClick = {
                val selectedCalendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val selectedDateCalendar = Calendar.getInstance()
                        selectedDateCalendar.set(year, month, dayOfMonth)
                        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        selectedDate = dateFormat.format(selectedDateCalendar.time)
                        onBirthdaySelected(selectedDate)
                    },
                    selectedCalendar.get(Calendar.YEAR),
                    selectedCalendar.get(Calendar.MONTH),
                    selectedCalendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            )
        ) {
            Text(
                selectedDate.ifEmpty { "Select your $labelName" },
                color = if (selectedDate.isEmpty()) Color.LightGray else Color.Black
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DatePreview(){
    PreviewComposable(backgroundColor = Color.Black) {
        val selectedBirthday = remember { mutableStateOf("") }
        DateComposable(modifier = it, labelName = "Birthday", context = LocalContext.current) { date ->
            selectedBirthday.value = date
        }
    }
}