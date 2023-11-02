package ca.etsmtl.log.fitnesshabits.data.preferences

class SettingsEnums {
    enum class TimeFormat {
        HOUR_24,
        HOUR_12
    }

    enum class DateFormat {
        MM_DD_YYYY,
        DD_MM_YYYY,
        YYYY_MM_DD
    }

    enum class VolumeFormat {
        IMPERIAL,
        METRIC
    }

    enum class WeightFormat {
        IMPERIAL,
        METRIC
    }

    enum class HeightFormat {
        IMPERIAL,
        METRIC
    }

    enum class ModuleHydration {
        ENABLED,
        DISABLED
    }

    enum class ModuleNutrition {
        ENABLED,
        DISABLED
    }

    enum class ModuleMedicationSupplement {
        ENABLED,
        DISABLED
    }

    enum class ModuleSleep {
        ENABLED,
        DISABLED
    }

    enum class ModuleBioBreak {
        ENABLED,
        DISABLED
    }

    enum class ModulePhysicalActivity {
        ENABLED,
        DISABLED
    }

    enum class ModuleAlcohol {
        ENABLED,
        DISABLED
    }

    enum class ModuleDiabetes {
        ENABLED,
        DISABLED
    }

    enum class Language {
        ENGLISH,
        FRENCH,
        SPANISH
    }

    enum class Theme {
        SYSTEM,
        LIGHT,
        DARK
    }
}