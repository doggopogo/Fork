package ca.etsmtl.log.fitnesshabits.data.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ca.etsmtl.log.fitnesshabits.R

enum class Unit(
    val symbol: String,
    @StringRes val displayName: Int,
    private val category: Category
) {
    MILLILITRE("mL", R.string.millilitre, Category.VOLUME),
    LITRE("L", R.string.litre, Category.VOLUME),

    MICROGRAM("mcg", R.string.microgram, Category.WEIGHT),
    MILLIGRAM("mg", R.string.milligram, Category.WEIGHT),
    GRAM("g", R.string.gram, Category.WEIGHT),
    KILOGRAM("kg", R.string.kilogram, Category.WEIGHT);

    enum class Category { VOLUME, WEIGHT }

    @Composable
    fun getDisplayName(): String {
        return stringResource(id = displayName)
    }

    companion object {
        fun getVolumeUnits(): List<Unit> = values().filter {
            it.category == Category.VOLUME
        }

        fun getWeightUnits(): List<Unit> = values().filter {
            it.category == Category.WEIGHT
        }
    }
}
