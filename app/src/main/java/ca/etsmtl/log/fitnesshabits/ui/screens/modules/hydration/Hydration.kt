package ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ca.etsmtl.log.fitnesshabits.ui.components.modules.BottomButtons
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DataListDisplay
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditNotificationButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditTargetButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.RowType
import ca.etsmtl.log.fitnesshabits.ui.theme.hydration
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.HydrationViewModel

@Composable
fun Hydration(navController: NavController) {
    val viewModel: HydrationViewModel = hiltViewModel()

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val moduleColor = hydration
    var openDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.clickable(
            onClick = { focusManager.clearFocus() },
            indication = null,
            interactionSource = interactionSource
        ).background(Color.White)
    ) {
        Header(
            title = stringResource(R.string.hydration),
            moduleColor,
            navigateBack = { navController.navigateUp() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                EditNotificationButton {}
                Spacer(modifier = Modifier.width(8.dp))
                EditTargetButton(
                    target = "${viewModel._hydrationTarget} L",
                    color = moduleColor,
                    onClick = {
                        openDialog = true
                    }
                )
            }

            val itemsDataState = viewModel.hydrationItemsData.observeAsState()
            val itemsData = itemsDataState.value
            if (itemsData == null) {
                // TODO Loading
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "LOADING",
                        fontWeight = FontWeight.Black,
                        fontSize = 50.sp,
                        color = Color.Black.copy(0.1f)
                    )
                }
            } else if (itemsData.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "EMPTY",
                        fontWeight = FontWeight.Black,
                        fontSize = 50.sp,
                        color = Color.Black.copy(0.1f)
                    )
                }
            } else {
                DataListDisplay(
                    title = "Liste de breuvages",
                    itemsData = itemsData,
                    rowType = RowType.ITEM,
                    isHydration = true,
                    color = moduleColor
                )
            }
            BottomButtons(
                addButtonEnabled = true,
                color = moduleColor,
                onAddClick = { navController.navigate("addHydrationItem") },
                onShowAllClick = {}
            )
        }
    }

    if (openDialog) {
        HydrationTargetDialog(
            onDismiss = { openDialog = false },
            onSave = {
                viewModel.setHydrationTarget(it)
                openDialog = false
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.loadHydrationItemsData()
    }
}