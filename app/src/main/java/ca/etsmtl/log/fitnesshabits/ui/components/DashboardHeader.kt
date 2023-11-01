package ca.etsmtl.log.fitnesshabits.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.material.IconButton
import androidx.compose.material.DrawerState
import androidx.compose.runtime.rememberCoroutineScope
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.theme.appGreen
import ca.etsmtl.log.fitnesshabits.ui.theme.white
import kotlinx.coroutines.launch

@Composable
fun DashboardHeader(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = appGreen),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Hamburger menu
        IconButton(onClick = {
            scope.launch {
                drawerState.open()
            }
        }) {
            Image(
                painter = painterResource(id = R.drawable.icon_picture),
                contentDescription = "Menu"
            )
        }

        // App's Logo
        Image(
            painter = painterResource(id = R.drawable.icon_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(40.dp)
        )

        // App's Name
        Text(text = "FitnessHabits", fontWeight = FontWeight.Bold, color = white)

        // Notification button
        IconButton(onClick = {}) {
            Image(
                painter = painterResource(id = R.drawable.baseline_notifications_24_white),
                contentDescription = "Notifications"
            )
        }
    }
}
