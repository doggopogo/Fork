package ca.etsmtl.log.fitnesshabits.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.ui.screens.Dashboard
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration.Hydration
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration.AddHydrationItem
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.alcohol.Alcohol
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.biobreak.BioBreak
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.diabetes.Diabetes
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.medicationsupplement.MedicationSupplement
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.food.Food
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.weight.Weight
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.physicalactivity.PhysicalActivity
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.sleep.Sleep
import ca.etsmtl.log.fitnesshabits.ui.screens.UserProfile
import ca.etsmtl.log.fitnesshabits.ui.screens.Targets
import ca.etsmtl.log.fitnesshabits.ui.screens.Settings
import ca.etsmtl.log.fitnesshabits.ui.screens.Export
import ca.etsmtl.log.fitnesshabits.ui.screens.modulesHistory.HydrationHistory

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "dashboard") {

        // Dashboard
        composable("dashboard") { Dashboard(navController) }

        // region Modules

        // Hydration
        composable("hydration") { Hydration(navController) }
        composable("addHydrationItem") { AddHydrationItem(navController) }

        // Food
        composable("food") { Food(navController) }

        // Medication & Supplement
        composable("medicationSupplement") { MedicationSupplement(navController) }

        // Sleep
        composable("sleep") { Sleep(navController) }

        // Bio Break
        composable("bioBreak") { BioBreak(navController) }

        // Physical Activity
        composable("physicalActivity") { PhysicalActivity(navController) }

        // Weight
        composable("weight") { Weight(navController) }

        // Alcohol
        composable("alcohol") { Alcohol(navController) }

        // Diabetes
        composable("diabetes") { Diabetes(navController) }

        // endregion

        // Hydration History
        composable(route = "hydrationHistory") {
            HydrationHistory(navController)
        }

        // Drawer screens
        composable("userProfile") { UserProfile(navController) }
        composable("targets") { Targets(navController) }
        composable("settings") { Settings(navController) }
        composable("export") { Export(navController) }
    }
}
