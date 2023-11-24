package ca.etsmtl.log.fitnesshabits.ui.components

import android.widget.NumberPicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun NumberPicker(
    modifier: Modifier = Modifier,
    startValueNumber: Int? = null,
    minValueNumber: Int,
    maxValueNumber: Int,
    incrementValueNumber: Int? = null,
    setValue: (Int) -> Unit
) {

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .border(
                BorderStroke(7.dp, Color.Black.copy(alpha = 0.05f)),
                RoundedCornerShape(12.dp)
            ),
        factory = { context ->
            NumberPicker(context).apply {

                minValue = minValueNumber

                // Si l'increment est defini, ajuste-le ainsi que les choix de valeurs
                incrementValueNumber?.let {
                    val displayedValues = mutableListOf<String>()
                    val valuesCount = maxValueNumber / incrementValueNumber

                    for (i in 0..valuesCount) {
                        val number = Integer.toString(i * incrementValueNumber)
                        displayedValues.add(number)
                        //value = it
                    }

                    maxValue = displayedValues.size - 1 // dernier item de la liste
                    setDisplayedValues(displayedValues.toTypedArray());

                } ?: run {
                    // Sinon, par defaut increment = 1 et max value est celui defini
                    maxValue = maxValueNumber
                }

                setOnValueChangedListener { numberPicker, oldVal, newVal ->
                    setValue(newVal)
                }
                startValueNumber?.let { value = it }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun NumberPickerPreview() {
    val value = remember { mutableStateOf(3) }
    val min = 3
    val max = 33
    NumberPicker(minValueNumber = min, maxValueNumber = max) {
        //value.value = it
    }
}

@Preview(showBackground = true)
@Composable
private fun NumberPickerWithStartPreview() {
    val value = remember { mutableStateOf(3) }
    val min = 0
    val max = 90
    NumberPicker(
        incrementValueNumber = 10,
        minValueNumber = min,
        maxValueNumber = max
    ) {
        //value.value = it
    }
}
