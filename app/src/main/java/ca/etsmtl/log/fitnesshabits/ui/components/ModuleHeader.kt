package ca.etsmtl.log.fitnesshabits.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.ui.theme.appGreen
import ca.etsmtl.log.fitnesshabits.ui.theme.white

@Composable
fun ModuleHeader(title: String, navigateBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = appGreen),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navigateBack() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = white
                )
        }
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,  // Adjust the size as needed
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = white
        )
    }
}
