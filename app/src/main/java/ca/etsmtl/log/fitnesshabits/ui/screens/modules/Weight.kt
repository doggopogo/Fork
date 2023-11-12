package ca.etsmtl.log.fitnesshabits.ui.screens.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.modules.BottomButtons
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ColoredDivider
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DataListDisplay
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DateFilterButtons
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditNotificationButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditTargetButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.Graph

@Composable
fun Weight(navController: NavController) {
    Column {
        Header(title = "Weight", R.color.weight, navigateBack = { navController.navigateUp() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                EditNotificationButton {}
                Spacer(modifier = Modifier.width(8.dp))
                EditTargetButton("10L", R.color.weight) {}
            }

            DataListDisplay(title = "Quantité totale bues : 600 mL", color = R.color.weight) {
            }
            BottomButtons(
                onAddClick = { },
                onShowAllClick = {}
            )
        }
    }
}