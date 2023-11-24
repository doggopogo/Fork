package ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButtonStyle
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DataListDisplay
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DateFilterButtons
import ca.etsmtl.log.fitnesshabits.ui.components.modules.RowType
import ca.etsmtl.log.fitnesshabits.ui.theme.hydration
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.HydrationHistoryViewModel

@Composable
fun HydrationHistory(navController: NavController) {
    val viewModel: HydrationHistoryViewModel = hiltViewModel()

    val moduleColor = hydration
    var graphExpanded by remember { mutableStateOf(false) }
    var graphButtonText =
        if (graphExpanded) stringResource(id = R.string.hide_graph) else stringResource(id = R.string.show_graph)

    var buttonStyle = ActionButtonStyle(
        textColor = Color.White,
        backgroundColor = moduleColor
    )

    Column(modifier = Modifier.background(Color.White)) {
        Header(
            title = stringResource(id = R.string.hydration),
            moduleColor,
            navigateBack = { navController.navigateUp() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Card(
                modifier = Modifier
                    .animateContentSize(),
            ) {
                Column {
                    //button for graph
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ActionButton(
                            text = graphButtonText,
                            onClick = { graphExpanded = !graphExpanded },
                            style = buttonStyle
                        )
                    }
                    AnimatedVisibility(
                        modifier = Modifier.padding(bottom = 16.dp),
                        visible = graphExpanded
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Graph goes here
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(0.3f)
                                    .background(Color.DarkGray)
                            )
                            DateFilterButtons(buttonStyle.backgroundColor)
                        }
                    }
                }
            }
            val itemsDataState = viewModel.hydrationItemsData.observeAsState()
            val itemsData = itemsDataState.value

            val logsDataState = viewModel.hydrationLogsData.observeAsState()
            val logsData = logsDataState.value

            if (itemsData == null || logsData == null) {
                // TODO Loading
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.loading),
                        fontWeight = FontWeight.Black,
                        fontSize = 50.sp,
                        color = Color.Black.copy(0.1f)
                    )
                }
            } else if (logsData.isEmpty() || itemsData.isEmpty()) {
                // TODO Loading
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.empty),
                        fontWeight = FontWeight.Black,
                        fontSize = 50.sp,
                        color = Color.Black.copy(0.1f)
                    )
                }
            } else {
                DataListDisplay(
                    title = stringResource(id = R.string.beverage_consumption_history_title),
                    logsData = logsData,
                    itemsData = itemsData,
                    rowType = RowType.LOG,
                    isHydration = true,
                    color = moduleColor
                )
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.loadHydrationHistoryData()
    }
}