package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.helper.Utilities
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabUiState
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.activity.user.UserActivityScreen
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.alcohol.user.UserAlcoholModal
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.alcohol.user.UserAlcoholScreen
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.bloodSugar.user.UserBloodSugarModal
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.bloodSugar.user.UserBloodSugarScreen
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.food.user.UserFoodScreen
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.sleep.user.UserSleepModal
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.sleep.user.UserSleepScreen
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.toilet.user.UserToiletModal
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.toilet.user.UserToiletScreen
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.weight.user.UserWeightModal
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.weight.user.UserWeightScreen

@Composable
fun HomeScreen(fitHabUiState : FitHabUiState) {
    val navController = rememberNavController()
    HomeNavigation(navController = navController, fitHabUiState = fitHabUiState)
}

@Composable
fun HomeNavigation(navController: NavHostController, fitHabUiState : FitHabUiState) {
    NavHost(navController, startDestination = HomeModuleItem.HomeModule.route) {
        composable(HomeModuleItem.HomeModule.route) {
            ModuleList(fitHabUiState = fitHabUiState, navController = navController)
        }
        composable(HomeModuleItem.Alcohol.route) {
            FitHabData.getUserAlcohols()
            FitHabData.getAlcohols()
            FitHabData.getCreatedByAlcohols()
            UserAlcoholScreen(userInfo = fitHabUiState.userInfo)
        }
        composable(HomeModuleItem.Sleep.route) {
            FitHabData.getUserSleep()
            UserSleepScreen(userInfo = fitHabUiState.userInfo)
        }
        composable(HomeModuleItem.Toilet.route) {
            FitHabData.getUserToilet()
            UserToiletScreen(userInfo = fitHabUiState.userInfo)
        }
        composable(HomeModuleItem.Weight.route) {
            FitHabData.getUserWeight()
            UserWeightScreen(userInfo = fitHabUiState.userInfo)
        }
        composable(HomeModuleItem.BloodSugar.route) {
            FitHabData.getUserBloodSugar()
            UserBloodSugarScreen(userInfo = fitHabUiState.userInfo)
        }
        composable(HomeModuleItem.Food.route) {
            FitHabData.getUserFoods()
            FitHabData.getFoods()
            FitHabData.getCreatedByFoods()
            UserFoodScreen(userInfo = fitHabUiState.userInfo)
        }
        composable(HomeModuleItem.Activity.route) {
            FitHabData.getUserActivity()
            FitHabData.getActivities()
            FitHabData.getCreatedByActivities()
            UserActivityScreen(userInfo = fitHabUiState.userInfo)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        val list = listOf(
            ModuleUiState(
                icon = R.drawable.icon_hydration,
                backgroundColorIcon = R.color.hydration,
                title = "Hydration",
                stats = listOf("0.000", "litre"),
                statsGrid = listOf(
                    Pair("", "Autres informations")
                )
            ),
            ModuleUiState(
                icon = R.drawable.icon_food,
                backgroundColorIcon = R.color.food,
                title = "Food",
                statsGrid =  listOf(
                    Pair("Protéines", "0.00g"),
                    Pair("Glucides", "100.00g"),
                    Pair("Fibres", "250.00g"),
                    Pair("Lipides", "0.00g")
                )
            ),
            ModuleUiState(
                icon = R.drawable.icon_bloodsugar,
                backgroundColorIcon = R.color.bloodSugar,
                title = "Blood Sugar",
                stats = listOf("00.0", "mmol/L"),
                clickOnIconAction = { UserBloodSugarModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(
                    HomeModuleItem.BloodSugar.route)}
            ),
            ModuleUiState(
                icon = R.drawable.icon_alcohol,
                backgroundColorIcon = R.color.alcohol,
                title = "Alcohol",
                clickOnIconAction = { UserAlcoholModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(
                    HomeModuleItem.Alcohol.route)}
            ),
            ModuleUiState(
                icon = R.drawable.icon_weight,
                backgroundColorIcon = R.color.weight,
                title = "Weight",
                clickOnIconAction = { UserWeightModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(
                    HomeModuleItem.Weight.route)}
            ),
            ModuleUiState(
                icon = R.drawable.icon_sleep,
                backgroundColorIcon = R.color.sleep,
                title = "Sleep",
                clickOnIconAction = { UserSleepModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(
                    HomeModuleItem.Sleep.route)}
            ),
            ModuleUiState(
                icon = R.drawable.icon_toilet,
                backgroundColorIcon = R.color.toilet,
                title = "Toilet",
                clickOnIconAction = { UserToiletModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(
                    HomeModuleItem.Toilet.route)}
            )
        )
        HomeScreen(
            fitHabUiState = FitHabUiState(Utilities.userInfoForPreview(), moduleUiStates = list)
        )
    }
}