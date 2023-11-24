package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.ui.theme.appGreen

@Composable
fun EditTargetButton(
    target : String,
    color: Color = appGreen,
    onClick: () -> Unit
) {
    Row{
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            shape = RoundedCornerShape(15.dp)
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