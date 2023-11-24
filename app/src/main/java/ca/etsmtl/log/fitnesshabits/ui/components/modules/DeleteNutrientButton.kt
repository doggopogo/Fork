package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DeleteNutrientButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(35.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Red.copy(0.3f)
        ),
        shape = RoundedCornerShape(13.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = "–",
            fontWeight = FontWeight.Black,
            fontSize = 22.sp,
            color = Color.Black.copy(0.4f)
        )
    }
}