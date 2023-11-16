package ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration

import ca.etsmtl.log.fitnesshabits.ui.components.Header
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.ui.SampleData
import ca.etsmtl.log.fitnesshabits.ui.components.modules.BottomButtons
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DataListDisplay
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditNotificationButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditTargetButton
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.AddHydrationItemViewModel
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ListItem

@Composable
fun Hydration(navController: NavController) {
    val addHydrationItemViewModel: AddHydrationItemViewModel = hiltViewModel()

    var targetNumber = 5
    val openDialog = remember { mutableStateOf(false) }

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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                EditNotificationButton {}
                Spacer(modifier = Modifier.width(8.dp))
                EditTargetButton(
                    "$targetNumber L",
                    R.color.hydration,
                    onClick = { openDialog.value = true }
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            DataListDisplay(
                title = "Liste de breuvages",
                dataList = SampleData.drinks.map { ListItem.DrinkItem(it) },
                color = R.color.hydration
            )
            BottomButtons(
                addButtonEnabled = true,
                color = R.color.hydration,
                onAddClick = { navController.navigate("addHydrationItem") },
                onShowAllClick = {}
            )
        }
    }

    if (openDialog.value) {
        HydrationTargetDialog(
            openDialog = openDialog.value,
            onDismiss = { openDialog.value = false },
            onSave = {
                addHydrationItemViewModel.updateHydrationTarget(newValue = it)
                openDialog.value = false
            },
            hydrationVM = addHydrationItemViewModel
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HydrationPreview() {
}