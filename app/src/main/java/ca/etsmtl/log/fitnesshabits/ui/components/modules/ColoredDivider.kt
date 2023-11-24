package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun ColoredDivider(color : Color) {
    Box(
        modifier = Modifier
            .background(color = color, shape = RoundedCornerShape(12.dp))
            .height(6.dp)
            .fillMaxWidth()
    )
}