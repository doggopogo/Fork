package ca.etsmtl.log.fitnesshabits.ui.components.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.R

@Composable
fun HydrationSwitch() {
    ModuleSwitch(name = "Hydration", image = R.drawable.icon_hydration) {}
}

@Composable
fun NutritionSwitch() {
    ModuleSwitch(name = "Nutrition", image = R.drawable.icon_food) {}
}

@Composable
fun MedicationSupplementSwitch() {
    ModuleSwitch(name = "Medication Supplement", image = R.drawable.icon_supplements) {}
}

@Composable
fun SleepSwitch() {
    ModuleSwitch(name = "Sleep", image = R.drawable.icon_sleep) {}
}

@Composable
fun BioBreakSwitch() {
    ModuleSwitch(name = "Bio Break", image = R.drawable.icon_toilet) {}
}

@Composable
fun PhysicalActivitySwitch() {
    ModuleSwitch(name = "Physical Activity", image = R.drawable.icon_activity) {}
}

@Composable
fun AlcoholSwitch() {
    ModuleSwitch(name = "Alcohol", image = R.drawable.icon_alcohol) {}
}

@Composable
fun DiabetesSwitch() {
    ModuleSwitch(name = "Diabetes", image = R.drawable.icon_bloodsugar) {}
}

@Composable
fun ModuleSwitch(
    name: String,
    image: Int,
    enabled: Boolean = true,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "module icon",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 12.dp)
            )
            Text(name)
        }
        Switch(
            checked = enabled,
            onCheckedChange = onToggle
        )
    }
}