package ca.etsmtl.log.fitnesshabits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.ui.screens.AlcoholDrugModule
import ca.etsmtl.log.fitnesshabits.ui.screens.BioBreakModule
import ca.etsmtl.log.fitnesshabits.ui.screens.Dashboard
import ca.etsmtl.log.fitnesshabits.ui.screens.DiabetesModule
import ca.etsmtl.log.fitnesshabits.ui.screens.HydrationModule
import ca.etsmtl.log.fitnesshabits.ui.screens.MedicationSupplementModule
import ca.etsmtl.log.fitnesshabits.ui.screens.NutritionModule
import ca.etsmtl.log.fitnesshabits.ui.screens.PhysicalActivityModule
import ca.etsmtl.log.fitnesshabits.ui.screens.SleepModule

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "dashboard") {
        composable("dashboard") { Dashboard(navController) }
        composable("hydration") { HydrationModule(navController) }
        composable("nutrition") { NutritionModule(navController) }
        composable("medicationSupplement") { MedicationSupplementModule(navController) }
        composable("sleep") { SleepModule(navController) }
        composable("bioBreak") { BioBreakModule(navController) }
        composable("physicalActivity") { PhysicalActivityModule(navController) }
        composable("alcoholDrug") { AlcoholDrugModule(navController) }
        composable("diabetes") { DiabetesModule(navController) }
    }
}
