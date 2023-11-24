package ca.etsmtl.log.fitnesshabits.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * Composable pour les petits boutons ronds (ex. alerte, edit, delete).
 */
@Composable
fun RoundButton(
    imageResId: Int,
    backgroundColor: Color = Color(0xFFF8F8F8),
    tintColor: Color? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(backgroundColor, shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(23.dp),
            painter = painterResource(id = imageResId),
            contentDescription = null,
            colorFilter = tintColor?.let { ColorFilter.tint(it) }
        )
    }
}

@Composable
fun RoundButton(
    image: ImageVector,
    backgroundColor: Color = Color(0xFFF8F8F8),
    tintColor: Color? = null,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(backgroundColor, shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(23.dp),
            imageVector = image,
            contentDescription = null,
            tint = tintColor ?: Color.Unspecified
        )
    }
}