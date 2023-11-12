package ca.etsmtl.log.fitnesshabits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.App
import ca.etsmtl.log.fitnesshabits.ui.screens.Dashboard
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.Alcohol
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.BioBreak
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.Diabetes
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.Hydration
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.MedicationSupplement
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.Nutrition
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.Weight
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.PhysicalActivity
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.Sleep
import ca.etsmtl.log.fitnesshabits.ui.screens.UserProfile
import ca.etsmtl.log.fitnesshabits.ui.screens.Targets
import ca.etsmtl.log.fitnesshabits.ui.screens.Settings
import ca.etsmtl.log.fitnesshabits.ui.screens.Export
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.Hydration.AddHydration
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.AddHydrationViewModel

@Composable
fun NavigationGraph(viewModel: AddHydrationViewModel) {
    val navController = rememberNavController()
    val hydrationRepository = App.instance.hydrationRepository
    NavHost(navController, startDestination = "dashboard") {
        // Modules
        composable("dashboard") {  Dashboard(navController, addHydrationRepository = hydrationRepository) }
        composable("hydration") { Hydration(navController) }
        composable("nutrition") { Nutrition(navController) }
        composable("medicationSupplement") { MedicationSupplement(navController) }
        composable("sleep") { Sleep(navController) }
        composable("bioBreak") { BioBreak(navController) }
        composable("physicalActivity") { PhysicalActivity(navController) }
        composable("weight") { Weight(navController) }
        composable("alcohol") { Alcohol(navController) }
        composable("diabetes") { Diabetes(navController) }

        // Add Hydration
        composable("addHydration") {
            AddHydration(navController, viewModel) }

        // Drawer screens
        composable("userProfile") { UserProfile(navController) }
        composable("targets") { Targets(navController) }
        composable("settings") { Settings(navController) }
        composable("export") { Export(navController) }
    }
}
