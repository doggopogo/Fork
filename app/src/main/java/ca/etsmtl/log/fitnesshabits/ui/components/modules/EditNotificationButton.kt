package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.RoundButton

@Composable
fun EditNotificationButton(onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Paramétrer les alertes",
            color = Color(0xFF707070)
        )
        Spacer(modifier = Modifier.width(8.dp))
        RoundButton(
            R.drawable.icon_notifications_bell_2x,
            tintColor = Color(0xFF707070),
            onClick = onClick
        )
    }
}