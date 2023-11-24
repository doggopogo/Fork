package ca.etsmtl.log.fitnesshabits.data.enums

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import ca.etsmtl.log.fitnesshabits.R

enum class NutritionType(
    @StringRes val displayName: Int
) {
    HYDRATION( R.string.hydration),
    FOOD(R.string.food),
    MEDICATION_SUPPLEMENT(R.string.medication_supplement),
    ALCOHOL(R.string.alcohol);

    @Composable
    fun getDisplayName(): String {
        return stringResource(id = displayName)
    }
}