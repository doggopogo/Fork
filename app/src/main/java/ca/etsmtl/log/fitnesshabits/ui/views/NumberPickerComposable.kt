package ca.etsmtl.log.fitnesshabits.ui.views

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
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable

@Composable
fun NumberPickerComposable(
    modifier: Modifier = Modifier,
    startValueNumber : Int? = null,
    minValueNumber: Int,
    maxValueNumber: Int,
    setValue: (Int) -> Unit
) {
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black),
            RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp))
        ,
        factory = { context ->
            NumberPicker(context).apply {
                setOnValueChangedListener { numberPicker, oldVal, newVal ->  setValue(newVal)}
                minValue = minValueNumber
                maxValue = maxValueNumber
                startValueNumber?.let {value = it}
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun NumberPickerPreview(){
    PreviewComposable(backgroundColor = Color.White) {
        val value = remember { mutableStateOf(3)}
        val min = 3
        val max = 33
        NumberPickerComposable(modifier = it, minValueNumber = min, maxValueNumber = max) {
            value.value = it
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NumberPickerWithStartPreview(){
    PreviewComposable(backgroundColor = Color.White) {
        val value = remember { mutableStateOf(3)}
        val min = 3
        val max = 33
        NumberPickerComposable(modifier = it, startValueNumber = 7, minValueNumber = min, maxValueNumber = max) {
            value.value = it
        }
    }
}