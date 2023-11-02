package ca.etsmtl.log.fitnesshabits.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.etsmtl.log.fitnesshabits.data.preferences.SettingsEnums

class SettingsViewModel : ViewModel() {
    // Time format
    val showTimeFormatDialog = MutableLiveData(false)
    val selectedTimeFormat = MutableLiveData(SettingsEnums.TimeFormat.HOUR_24)

    // Date format
    val showDateFormatDialog = MutableLiveData(false)
    val selectedDateFormat = MutableLiveData(SettingsEnums.DateFormat.DD_MM_YYYY)

    // Unit format
    val showVolumeFormatDialog = MutableLiveData(false)
    val showWeightFormatDialog = MutableLiveData(false)
    val showHeightFormatDialog = MutableLiveData(false)
    val selectedVolumeFormat = MutableLiveData(SettingsEnums.VolumeFormat.METRIC)
    val selectedWeightFormat = MutableLiveData(SettingsEnums.WeightFormat.METRIC)
    val selectedHeightFormat = MutableLiveData(SettingsEnums.HeightFormat.METRIC)

    // Modules selection
    val hydrationEnabled = MutableLiveData(SettingsEnums.ModuleHydration.ENABLED)
    val nutritionEnabled = MutableLiveData(SettingsEnums.ModuleNutrition.ENABLED)
    val medicationSupplementEnabled = MutableLiveData(SettingsEnums.ModuleMedicationSupplement.ENABLED)
    val sleepEnabled = MutableLiveData(SettingsEnums.ModuleSleep.ENABLED)
    val bioBreakEnabled = MutableLiveData(SettingsEnums.ModuleBioBreak.ENABLED)
    val physicalActivityEnabled = MutableLiveData(SettingsEnums.ModulePhysicalActivity.ENABLED)
    val alcoholEnabled = MutableLiveData(SettingsEnums.ModuleAlcohol.ENABLED)
    val diabetesEnabled = MutableLiveData(SettingsEnums.ModuleDiabetes.ENABLED)

    // Language selection
    val showLanguageDialog = MutableLiveData(false)
    val selectedLanguage = MutableLiveData(SettingsEnums.Language.ENGLISH)

    // Theme selection
    val showThemeDialog = MutableLiveData(false)
    val selectedTheme = MutableLiveData(SettingsEnums.Theme.SYSTEM)

    // Reset
    val showResetDialog = MutableLiveData(false)
}