package ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.NumberPicker
import ca.etsmtl.log.fitnesshabits.ui.theme.hydration
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.HydrationViewModel

data class SplitDecimalResult(var integerPart: Int, var decimalPart: Int)

@Composable
fun HydrationTargetDialog(
    onDismiss: () -> Unit,
    onSave: (Float) -> Unit,
) {
    val viewModel: HydrationViewModel = hiltViewModel()

    val splitTargetResult = SplitDecimalResult(0, 0)
    splitDecimal(viewModel._hydrationTarget, splitTargetResult)

    var newTargetNumber: Int = 0
    var newTargetDecimal: Int = 0

    AlertDialog(
        modifier = Modifier.background(Color.White),
        shape = RoundedCornerShape(13.dp),
        onDismissRequest = onDismiss,
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.enter_target_title),
                    fontWeight = FontWeight.Bold,
                    color = hydration,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 24.dp),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    NumberPicker(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 6.dp),
                        minValueNumber = 0,
                        maxValueNumber = 5,
                        startValueNumber = splitTargetResult.integerPart

                    ) {
                        newTargetNumber = it
                    }
                    Text(text = ".")
                    NumberPicker(
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 6.dp),
                        minValueNumber = 0,
                        maxValueNumber = 90,
                        incrementValueNumber = 10,
                        startValueNumber = splitTargetResult.decimalPart
                    ) {
                        newTargetDecimal = it
                    }
                }
            }

        },
        confirmButton = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = { onSave(formatTargetValue(newTargetNumber, newTargetDecimal)) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = hydration),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.save),
                        color = Color.White
                    )
                }
            }
        }
    )
}

/**
 * Combine les valeurs des 2 numberPicker pour retourner un Float combiné
 */
fun formatTargetValue(newTargetNumber: Int, newTargetDecimal: Int): Float {
    return newTargetNumber.toFloat() + (newTargetDecimal.toFloat() / 10)
}

/**
 * Divise en 2 parties la valeur de la cible actuel pour positionner
 * les 2 numberPicker à la cible actuelle
 */
fun splitDecimal(inputFloat: Float, result: SplitDecimalResult) {
    // Convert the float to a string
    val floatString = inputFloat.toString()

    // Split the string into parts using the decimal point
    val parts = floatString.split(".")

    // Extract the integer and decimal parts
    result.integerPart = if (parts.isNotEmpty()) parts[0].toInt() else 0
    result.decimalPart = if (parts.size > 1) parts[1].toInt() else 0
}


