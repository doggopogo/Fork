package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessAndroidTheme

@Composable
fun ShowListModal(
    setShowModal : () -> Unit,
    content : @Composable () -> Unit
) {
    FitnessAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
        ) {
            AlertDialog(
                onDismissRequest = {
                    setShowModal()
                },
                {
                    content()
                },
                backgroundColor = Color.White
            )
        }
    }
}