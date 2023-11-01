package ca.etsmtl.log.fitnesshabits.ui.screens

import ca.etsmtl.log.fitnesshabits.ui.components.AppDrawer
import ca.etsmtl.log.fitnesshabits.ui.components.DashboardHeader
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.DrawerValue
import androidx.compose.material.rememberDrawerState
import androidx.navigation.NavController

@Composable
fun Dashboard(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    AppDrawer(drawerState = drawerState) {
        Column {
            DashboardHeader(drawerState = drawerState)

            Spacer(modifier = Modifier.height(16.dp))  // Adds vertical spacing
            Button(onClick = { navController.navigate("hydration") }) {
                Text("Go to Hydration Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))  // Adds vertical spacing
            Button(onClick = { navController.navigate("nutrition") }) {
                Text("Go to Nutrition Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))  // Adds vertical spacing
            Button(onClick = { navController.navigate("medicationSupplement") }) {
                Text("Go to Medication Supplement Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))  // Adds vertical spacing
            Button(onClick = { navController.navigate("sleep") }) {
                Text("Go to Sleep Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))  // Adds vertical spacing
            Button(onClick = { navController.navigate("bioBreak") }) {
                Text("Go to Bio break Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))  // Adds vertical spacing
            Button(onClick = { navController.navigate("physicalActivity") }) {
                Text("Go to Physical activity Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))  // Adds vertical spacing
            Button(onClick = { navController.navigate("alcoholDrug") }) {
                Text("Go to Alcohol Drug Screen")
            }
            Spacer(modifier = Modifier.height(16.dp))  // Adds vertical spacing
            Button(onClick = { navController.navigate("diabetes") }) {
                Text("Go to Diabetes Screen")
            }
        }
    }
}


