package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R

@Composable
fun EditTargetButton(
    target : String,
    color: Int = R.color.appGreen,
    onClick: () -> Unit
) {
    Row{
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(color))
        ) {
            Text(
                text = "Cible: $target",
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}