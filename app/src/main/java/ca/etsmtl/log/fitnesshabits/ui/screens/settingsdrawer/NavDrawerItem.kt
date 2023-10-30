package ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer

import ca.etsmtl.log.fitnesshabits.R

sealed class NavDrawerItem(val icon: Int, val title: String, val route: String) {
    object Home : NavDrawerItem(R.drawable.icon_menu, "Accueil", "home")
    object Profile : NavDrawerItem(R.drawable.icon_edit,"Profil", "profile")
    object Export : NavDrawerItem(R.drawable.icon_export,"Exporter", "export")
    object Target : NavDrawerItem(R.drawable.icon_target,"Cibles", "target")
    object Preference : NavDrawerItem(R.drawable.icon_preference,"Préférences", "preference")
    object Language : NavDrawerItem(R.drawable.icon_language,"Langue", "language")
    object Logout  : NavDrawerItem(R.drawable.icon_exit,"Déconnexion", "logout")
}
