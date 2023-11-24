package ca.etsmtl.log.fitnesshabits.data.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ca.etsmtl.log.fitnesshabits.R

enum class Macronutrient(
    @StringRes val displayName: Int,
    private val unit: Unit?
) {
    CALORIES(R.string.calories, null),

    TOTAL_FAT(R.string.total_fat, Unit.GRAM),
    SATURATED_FAT(R.string.saturated_fat, Unit.GRAM),
    TRANS_FAT(R.string.trans_fat, Unit.GRAM),
    POLYUNSATURATED_FAT(R.string.polyunsaturated_fat, Unit.GRAM),
    MONOUNSATURATED_FAT(R.string.monounsaturated_fat, Unit.GRAM),

    TOTAL_CARBOHYDRATE(R.string.total_carbohydrate, Unit.GRAM),
    DIETARY_FIBRE(R.string.dietary_fibre, Unit.GRAM),
    TOTAL_SUGARS(R.string.total_sugars, Unit.GRAM),
    ADDED_SUGARS(R.string.added_sugars, Unit.GRAM),

    PROTEIN(R.string.protein, Unit.GRAM),

    SODIUM(R.string.sodium, Unit.MILLIGRAM),
    CHOLESTEROL(R.string.cholesterol, Unit.MILLIGRAM);

    @Composable
    fun getDisplayName(): String {
        return stringResource(id = displayName)
    }

    @Composable
    fun getUnit(): Unit? {
        return unit
    }
}