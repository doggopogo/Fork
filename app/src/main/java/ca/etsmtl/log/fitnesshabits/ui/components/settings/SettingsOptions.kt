package ca.etsmtl.log.fitnesshabits.ui.components.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.data.preferences.SettingsEnums
import ca.etsmtl.log.fitnesshabits.viewmodels.SettingsViewModel

@Composable
fun DataFormatOption(settingsViewModel: SettingsViewModel) {
    var isExpanded = remember { mutableStateOf(false) }
    SettingsOptionExpandable(
        title = stringResource(id = R.string.data_format_title),
        icon = R.drawable.icon_hashtag,
        isExpanded = isExpanded.value,
        onClick = { isExpanded.value = !isExpanded.value }
    ) {
        Column(modifier = Modifier.padding(horizontal = 32.dp)) {
            TimeFormatOption(settingsViewModel)
            DateFormatOption(settingsViewModel)
            UnitFormatOption(settingsViewModel)
        }
    }
}

@Composable
fun TimeFormatOption(settingsViewModel: SettingsViewModel) {
    SettingsOption(title = stringResource(id = R.string.time_format_title)) {
        settingsViewModel.showTimeFormatDialog.value = true
    }

    RadioButtonDialog(
        showDialog = settingsViewModel.showTimeFormatDialog,
        title = stringResource(id = R.string.time_format_description),
        options = SettingsEnums.TimeFormat.values().map { it.toString() },
        selectedOption = settingsViewModel.selectedTimeFormat,
        stringToEnum = { SettingsEnums.TimeFormat.valueOf(it) }
    )
}

@Composable
fun DateFormatOption(settingsViewModel: SettingsViewModel) {
    SettingsOption(title = stringResource(id = R.string.date_format_title)) {
        settingsViewModel.showDateFormatDialog.value = true
    }

    RadioButtonDialog(
        showDialog = settingsViewModel.showDateFormatDialog,
        title = stringResource(id = R.string.date_format_description),
        options = SettingsEnums.DateFormat.values().map { it.toString() },
        selectedOption = settingsViewModel.selectedDateFormat,
        stringToEnum = { SettingsEnums.DateFormat.valueOf(it) }
    )
}

@Composable
fun UnitFormatOption(settingsViewModel: SettingsViewModel) {
    var isExpanded = remember { mutableStateOf(false) }
    SettingsOptionExpandable(
        title = stringResource(id = R.string.unit_format_title),
        isExpanded = isExpanded.value,
        onClick = { isExpanded.value = !isExpanded.value }
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            VolumeFormatOption(settingsViewModel)
            WeightFormatOption(settingsViewModel)
            HeightFormatOption(settingsViewModel)
        }
    }
}

@Composable
fun VolumeFormatOption(settingsViewModel: SettingsViewModel) {
    SettingsOption(title = stringResource(id = R.string.volume_format_title)) {
        settingsViewModel.showVolumeFormatDialog.value = true
    }

    RadioButtonDialog(
        showDialog = settingsViewModel.showVolumeFormatDialog,
        title = stringResource(id = R.string.volume_format_description),
        options = SettingsEnums.VolumeFormat.values().map { it.toString() },
        selectedOption = settingsViewModel.selectedVolumeFormat,
        stringToEnum = { SettingsEnums.VolumeFormat.valueOf(it) }
    )
}

@Composable
fun WeightFormatOption(settingsViewModel: SettingsViewModel) {
    SettingsOption(title = stringResource(id = R.string.weight_format_title)) {
        settingsViewModel.showWeightFormatDialog.value = true
    }

    RadioButtonDialog(
        showDialog = settingsViewModel.showWeightFormatDialog,
        title = stringResource(id = R.string.weight_format_description),
        options = SettingsEnums.WeightFormat.values().map { it.toString() },
        selectedOption = settingsViewModel.selectedWeightFormat,
        stringToEnum = { SettingsEnums.WeightFormat.valueOf(it) }
    )
}

@Composable
fun HeightFormatOption(settingsViewModel: SettingsViewModel) {
    SettingsOption(title = stringResource(id = R.string.height_format_title)) {
        settingsViewModel.showHeightFormatDialog.value = true
    }

    RadioButtonDialog(
        showDialog = settingsViewModel.showHeightFormatDialog,
        title = stringResource(id = R.string.height_format_description),
        options = SettingsEnums.HeightFormat.values().map { it.toString() },
        selectedOption = settingsViewModel.selectedHeightFormat,
        stringToEnum = { SettingsEnums.HeightFormat.valueOf(it) }
    )
}

@Composable
fun ModulesOption() {
    var isExpanded = remember { mutableStateOf(false) }
    SettingsOptionExpandable(
        title = stringResource(id = R.string.enabled_module_title),
        icon = R.drawable.icon_menu,
        isExpanded = isExpanded.value,
        onClick = { isExpanded.value = !isExpanded.value }
    ) {
        Column(modifier = Modifier.padding(horizontal = 32.dp)) {
            HydrationSwitch()
            NutritionSwitch()
            MedicationSupplementSwitch()
            SleepSwitch()
            BioBreakSwitch()
            PhysicalActivitySwitch()
            AlcoholSwitch()
            DiabetesSwitch()
        }
    }
}

@Composable
fun LanguageOption(settingsViewModel: SettingsViewModel) {
    SettingsOption(
        title = stringResource(id = R.string.language_title),
        icon = R.drawable.icon_language
    ) {
        settingsViewModel.showLanguageDialog.value = true
    }

    RadioButtonDialog(
        showDialog = settingsViewModel.showLanguageDialog,
        title = stringResource(id = R.string.language_description),
        options = SettingsEnums.Language.values().map { it.toString() },
        selectedOption = settingsViewModel.selectedLanguage,
        stringToEnum = { SettingsEnums.Language.valueOf(it) }
    )
}

@Composable
fun ThemeOption(settingsViewModel: SettingsViewModel) {
    SettingsOption(
        title = stringResource(id = R.string.theme_title),
        icon = R.drawable.icon_theme
    ) {
        settingsViewModel.showThemeDialog.value = true
    }
    RadioButtonDialog(
        showDialog = settingsViewModel.showThemeDialog,
        title = stringResource(id = R.string.theme_description),
        options = SettingsEnums.Theme.values().map { it.toString() },
        selectedOption = settingsViewModel.selectedTheme,
        stringToEnum = { SettingsEnums.Theme.valueOf(it) }
    )
}

@Composable
fun ResetOption(settingsViewModel: SettingsViewModel) {
    val showResetDialog = settingsViewModel.showResetDialog.observeAsState(false)

    SettingsOption(
        title = stringResource(id = R.string.reset_title),
        icon = R.drawable.icon_delete_trashbin
    ) {
        settingsViewModel.showResetDialog.value = true
    }

    if (showResetDialog.value) {
        AlertDialog(
            onDismissRequest = { settingsViewModel.showResetDialog.value = false },
            title = { Text(stringResource(id = R.string.reset_title)) },
            text = { Text(stringResource(id = R.string.reset_description)) },
            dismissButton = {
                TextButton(onClick = { settingsViewModel.showResetDialog.value = false }) {
                    Text(stringResource(id = R.string.cancel))
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    settingsViewModel.showResetDialog.value = false
                }) {
                    Text(stringResource(id = R.string.ok))
                }
            }
        )
    }
}

@Composable
fun SettingsOption(
    title: String,
    icon: Int = -1,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != -1) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun SettingsOptionExpandable(
    title: String,
    icon: Int = -1,
    isExpanded: Boolean = false,
    onClick: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (icon != -1) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontSize = 16.sp
                )
            }
            AnimatedVisibility(visible = isExpanded) {
                content()
            }
        }
    }
}