package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable pour les boutons d'action personnalisable.
 *
 * Permet de définir le texte affiché, l'action à effectuer lors du clic
 * et le style du bouton.
 *
 * Par défaut, le bouton utilise des styles par défaut (ActionButtonStyle)
 *
 */
@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    style: ActionButtonStyle = ActionButtonStyle()
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = style.backgroundColor),
        shape = RoundedCornerShape(18.dp)
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            color = style.textColor,
            style = style.textStyle
        )
    }
}

data class ActionButtonStyle(
    val textColor: Color = Color.Black,
    val textStyle: TextStyle = TextStyle.Default.copy(),
    val backgroundColor: Color = Color(0xFFF1F1F1)
)

