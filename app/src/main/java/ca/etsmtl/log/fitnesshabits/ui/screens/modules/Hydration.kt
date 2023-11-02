package ca.etsmtl.log.fitnesshabits.ui.screens.modules

import ca.etsmtl.log.fitnesshabits.ui.components.Header
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ColoredDivider
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DataListDisplay
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DateFilterButtons
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditNotificationButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditTargetButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.Graph


@Composable
fun Hydration(navController: NavController) {
    Column {
        Header(
            title = "Hydration",
            R.color.hydration,
            navigateBack = { navController.navigateUp() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                EditNotificationButton {}
                Spacer(modifier = Modifier.height(8.dp))
                EditTargetButton("10L", R.color.hydration) {}
            }
            Graph()
            ColoredDivider(color = R.color.hydration)
            DateFilterButtons()
            DataListDisplay(title = "Quantité totale bue : 600 mL") {
            }
        }
    }
}
