package ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.NumberPicker
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.AddHydrationItemViewModel

data class SplitDecimalResult(var integerPart: Int, var decimalPart: Int)

@Composable
fun HydrationTargetDialog(
    openDialog: Boolean,
    onDismiss: () -> Unit,
    onSave: (Float) -> Unit,
    hydrationVM: AddHydrationItemViewModel
) {
    val currentTargetValue = hydrationVM.hydrationTarget.value
    val splitTargetResult = SplitDecimalResult(0, 0)
    splitDecimal(currentTargetValue, splitTargetResult)

    var newTargetNumber: Int = 0
    var newTargetDecimal: Int = 0

    if (openDialog){
        AlertDialog(
            onDismissRequest =
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                onDismiss
            ,
            title = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Entrez votre cible",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.hydration),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(bottom = 16.dp),
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
                        Text("ml")

                    }
                }

            },
            confirmButton = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Button(
                        onClick = { onSave(formatTargetValue(newTargetNumber, newTargetDecimal)) },
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.hydration))
                    ) {
                        Text(text = "Enregistrer")
                    }
                }
            }
        )
    }
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


