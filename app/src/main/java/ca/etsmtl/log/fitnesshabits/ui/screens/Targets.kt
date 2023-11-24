package ca.etsmtl.log.fitnesshabits.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.Header

@Composable
fun Targets(navController: NavController) {
    Column(modifier = Modifier.background(Color.White)) {
        Header(
            title = stringResource(id = R.string.targets_title),
            navigateBack = { navController.navigateUp() })
        // Rest of your screen content
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.targets_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}