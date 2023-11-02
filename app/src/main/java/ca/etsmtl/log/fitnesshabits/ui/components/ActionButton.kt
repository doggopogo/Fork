package ca.etsmtl.log.fitnesshabits.ui.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
private fun ActionButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF1F1F1))
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            color = Color.Black,
            style = TextStyle.Default.copy()
        )
    }
}