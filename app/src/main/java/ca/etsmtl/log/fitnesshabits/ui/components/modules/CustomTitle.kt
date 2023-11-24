package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.ui.theme.hydration

@Composable
fun CustomTitle(
    text: String,
    fontSize: TextUnit = 24.sp,
    isItalic: Boolean = false
) {
    Text(
        modifier = Modifier.padding(4.dp),
        text = text,
        fontSize = fontSize,
        fontStyle = if (isItalic) {
            FontStyle.Italic
        } else {
            FontStyle.Normal
        },
        color = hydration
    )
}