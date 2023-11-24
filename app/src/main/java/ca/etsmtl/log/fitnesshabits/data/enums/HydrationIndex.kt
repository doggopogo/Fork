package ca.etsmtl.log.fitnesshabits.data.enums

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ca.etsmtl.log.fitnesshabits.R

enum class HydrationIndex(
    val multiplier: Float,
    @StringRes val displayName: Int,
    @DrawableRes val icon: Int
) {
    NON_HYDRATING(0.0f, R.string.non_hydrating, R.drawable.icon_non_hydrating),
    SLIGHTLY_HYDRATING(0.25f, R.string.slightly_hydrating, R.drawable.icon_slightly_hydrating),
    MODERATELY_HYDRATING(0.5f, R.string.moderately_hydrating, R.drawable.icon_moderately_hydrating),
    HYDRATING(0.75f, R.string.hydrating, R.drawable.icon_hydrating),
    FULLY_HYDRATING(1.0f, R.string.fully_hydrating, R.drawable.icon_fully_hydrating);

    @Composable
    fun getDisplayName(): String {
        return stringResource(id = displayName)
    }

    @Composable
    fun getImage(): Painter {
        return painterResource(id = icon)
    }
}
