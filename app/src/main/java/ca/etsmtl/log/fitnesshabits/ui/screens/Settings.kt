package ca.etsmtl.log.fitnesshabits.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.ui.components.settings.DataFormatOption
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.ui.components.settings.LanguageOption
import ca.etsmtl.log.fitnesshabits.ui.components.settings.ModulesOption
import ca.etsmtl.log.fitnesshabits.ui.components.settings.ResetOption
import ca.etsmtl.log.fitnesshabits.ui.components.settings.ThemeOption
import ca.etsmtl.log.fitnesshabits.viewmodels.SettingsViewModel

@Composable
fun Settings(navController: NavController) {
    val settingsViewModel: SettingsViewModel = viewModel()

    Column {
        Header(title = "Settings", navigateBack = { navController.navigateUp() })

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            DataFormatOption(settingsViewModel)
            ModulesOption()
            LanguageOption(settingsViewModel)
            ThemeOption(settingsViewModel)
            ResetOption(settingsViewModel)
        }
    }
}