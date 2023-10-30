package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen

import ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.NavDrawerItem

//Test
sealed class HomeModuleItem(val route: String) {
    object HomeModule : HomeModuleItem(NavDrawerItem.Home.route)
    object Food : HomeModuleItem("food")
    object Hydration : HomeModuleItem("hydration")
    object Supplements : HomeModuleItem("supplements")
    object Sleep : HomeModuleItem("sleep")
    object Toilet : HomeModuleItem("toilet")
    object Activity : HomeModuleItem("activity")
    object Weight : HomeModuleItem("weight")
    object Alcohol : HomeModuleItem("alcohol")
    object BloodSugar : HomeModuleItem("bloodsugar")
}