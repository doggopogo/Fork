package ca.etsmtl.log.fitnesshabits.ui.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import ca.etsmtl.log.fitnesshabits.data.preferences.SettingsEnums

@Composable
fun <T : Enum<T>> RadioButtonDialog(
    showDialog: MutableLiveData<Boolean>,
    title: String,
    options: List<String>,
    selectedOption: MutableLiveData<T>,
    stringToEnum: (String) -> T
) {
    val isDialogVisible = showDialog.observeAsState(false)
    val currentSelectedOption = selectedOption.observeAsState(SettingsEnums.Language.ENGLISH)

    if (isDialogVisible.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text(text = title) },
            text = {
                Column {
                    options.forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(onClick = {
                                    selectedOption.value = stringToEnum(option)
                                    showDialog.value = false
                                }),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = (option == currentSelectedOption.value.toString()),
                                onClick = {
                                    selectedOption.value = stringToEnum(option)
                                    showDialog.value = false
                                }
                            )
                            Text(text = option)
                        }
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog.value = false }) {
                    Text("Cancel")
                }
            },
            confirmButton = {
            }
        )
    }
}