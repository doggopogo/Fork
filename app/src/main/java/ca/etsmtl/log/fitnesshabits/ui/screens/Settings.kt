package ca.etsmtl.log.fitnesshabits.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.ui.components.settings.DataFormatOption
import ca.etsmtl.log.fitnesshabits.ui.components.settings.LanguageOption
import ca.etsmtl.log.fitnesshabits.ui.components.settings.ModulesOption
import ca.etsmtl.log.fitnesshabits.ui.components.settings.ResetOption
import ca.etsmtl.log.fitnesshabits.ui.components.settings.ThemeOption
import ca.etsmtl.log.fitnesshabits.viewmodels.SettingsViewModel

@Composable
fun Settings(navController: NavController) {
    val settingsViewModel: SettingsViewModel = viewModel()

    Column (modifier = Modifier.background(Color.White)){
        Header(
            title = stringResource(id = R.string.settings_title),
            navigateBack = { navController.navigateUp() })

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
            DataFormatOption(settingsViewModel)
            ModulesOption()
            LanguageOption(settingsViewModel)
            ThemeOption(settingsViewModel)
            ResetOption(settingsViewModel)
        }
    }
}