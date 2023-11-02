package ca.etsmtl.log.fitnesshabits.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * Composable pour les petits boutons ronds (ex. alerte, edit, delete).
 */
@Composable
fun RoundButton(
    imageResId: Int,
    tintColor: Color? = null, // Optional tint color parameter
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background((Color(0xFFF1F1F1)), shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            colorFilter = tintColor?.let { ColorFilter.tint(it) },
            modifier = Modifier
                .size(25.dp)
        )
    }
}