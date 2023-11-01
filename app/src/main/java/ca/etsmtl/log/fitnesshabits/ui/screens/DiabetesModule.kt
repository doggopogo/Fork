package ca.etsmtl.log.fitnesshabits.ui.screens

import ca.etsmtl.log.fitnesshabits.ui.components.ModuleHeader
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DiabetesModule(navController: NavController) {
    Column {
        ModuleHeader(title = "Diabetes", navigateBack = { navController.navigateUp() })
        // Rest of your screen content
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Diabetes",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}