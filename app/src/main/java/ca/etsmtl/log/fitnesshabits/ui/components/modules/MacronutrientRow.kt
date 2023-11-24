package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.data.enums.Macronutrient

@Composable
fun MacronutrientRow(
    value: Int,
    onValueChange: (Int) -> Unit,
    macronutrientType: Macronutrient,
    fontSize: TextUnit = 18.sp,
    isBold: Boolean = false
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = macronutrientType.getDisplayName(),
            fontSize = fontSize,
            fontWeight =
            if (isBold) {
                FontWeight.Black
            } else {
                FontWeight.Normal
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberTextField(
                value = value,
                onValueChange = onValueChange,
                fontSize = fontSize,
                isBold = isBold
            )
            if (macronutrientType.getUnit() != null) {
                Text(
                    text = " ${macronutrientType.getUnit()!!.symbol}",
                    fontSize = (fontSize.value - 2).sp,
                    fontWeight =
                    if (isBold) {
                        FontWeight.Black
                    } else {
                        FontWeight.Normal
                    },
                    color = Color.Black.copy(alpha = 0.6f),
                )
            }
        }
    }
}