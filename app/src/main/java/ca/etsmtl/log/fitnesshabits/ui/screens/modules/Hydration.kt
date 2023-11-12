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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.ui.components.modules.BottomButtons
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DataListDisplay
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditNotificationButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditTargetButton

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
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                EditNotificationButton {}
                Spacer(modifier = Modifier.width(8.dp))
                EditTargetButton("10L", R.color.hydration) {}
            }

            Spacer(modifier = Modifier.height(6.dp))

            DataListDisplay(title = "Liste de breuvages", color = R.color.hydration) {
            }
            BottomButtons(
                addButtonEnabled = true,
                color = R.color.hydration,
                onAddClick = { navController.navigate("addHydration") },
                onShowAllClick = {}
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HydrationPreview() {
    Hydration(navController = rememberNavController())
}