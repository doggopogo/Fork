package ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Item
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Serving
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButtonStyle
import ca.etsmtl.log.fitnesshabits.ui.components.modules.AddNutrientButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ColoredDivider
import ca.etsmtl.log.fitnesshabits.ui.components.modules.CustomDropdownMenu
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DeleteNutrientButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.NumberTextField
import ca.etsmtl.log.fitnesshabits.ui.screens.unixFormatDate
import ca.etsmtl.log.fitnesshabits.ui.screens.unixFormatTime
import ca.etsmtl.log.fitnesshabits.ui.theme.hydration
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.AddHydrationLogViewModel
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.HydrationLogRowUiState
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.SelectableHydrationLogRowData
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import ca.etsmtl.log.fitnesshabits.data.enums.Unit as enumUnit

@Composable
fun AddHydrationLog(navController: NavController) {
    val viewModel: AddHydrationLogViewModel = hiltViewModel()

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    val moduleColor = hydration

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .clickable(
                onClick = { focusManager.clearFocus() },
                indication = null,
                interactionSource = interactionSource
            )
            .background(Color.White)
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
            Text(
                text = stringResource(id = R.string.beverage_consumed),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 16.dp)
            )

            // region Date and Time picker
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { showDatePicker = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color.Black.copy(alpha = 0.35f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        fontSize = 15.sp,
                        text = unixFormatDate(viewModel._selectedDateAndTime),
                        color = Color.Black.copy(alpha = 0.35f)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable { showTimePicker = true }
                ) {
                    Text(
                        fontSize = 15.sp,
                        text = unixFormatTime(viewModel._selectedDateAndTime),
                        color = Color.Black.copy(alpha = 0.35f)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp),
                        tint = Color.Black.copy(alpha = 0.35f)
                    )
                }
            }

            // region Date Picker and Time picker logic
            val selectedDate = Instant.ofEpochMilli(viewModel._selectedDateAndTime)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

            val selectedTime = Instant.ofEpochMilli(viewModel._selectedDateAndTime)
                .atZone(ZoneId.systemDefault())
                .toLocalTime()

            if (showDatePicker && !showTimePicker) {
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val updatedLocalDate = LocalDate.of(year, month + 1, dayOfMonth)
                        val updatedInstant = updatedLocalDate.atTime(selectedTime)
                            .atZone(ZoneId.systemDefault())
                            .toInstant()
                        viewModel.setSelectedDateAndTime(updatedInstant.toEpochMilli())
                        showDatePicker = false
                    },
                    selectedDate.year,
                    selectedDate.monthValue - 1,
                    selectedDate.dayOfMonth
                )
                datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
                datePickerDialog.setOnCancelListener { showDatePicker = false }
                datePickerDialog.show()
            }

            if (showTimePicker && !showDatePicker) {
                val timePickerDialog = TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        val updatedLocalTime = LocalTime.of(hourOfDay, minute)
                        val updatedInstant = selectedDate.atTime(updatedLocalTime)
                            .atZone(ZoneId.systemDefault())
                            .toInstant()
                        viewModel.setSelectedDateAndTime(updatedInstant.toEpochMilli())
                        showTimePicker = false
                    },
                    selectedTime.hour,
                    selectedTime.minute,
                    true
                )
                timePickerDialog.setOnCancelListener { showTimePicker = false }
                timePickerDialog.show()
            }
            // endregion

            // endregion

            // region Table
            ColoredDivider(moduleColor)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.80f)
                    .verticalScroll(rememberScrollState())
            ) {
                viewModel._hydrationLogRow.forEachIndexed { index, selectableHydrationLogRowData ->
                    RowContent(
                        selectableHydrationLogRowData = selectableHydrationLogRowData,
                        items = viewModel._hydrationItems,
                        onUiStateChange = { uiState ->
                            viewModel.setHydrationLogRowUiState(index, uiState)
                        },
                        onSelectedItemDropdownValueChange = { selectedItem ->
                            viewModel.setHydrationLogRowSelectedItem(index, selectedItem)
                        },
                        onServingSelected = { selectedServing ->
                            viewModel.setHydrationLogRowSelectedServing(index, selectedServing)
                        },
                        onAmountValueChange = { amount ->
                            viewModel.setHydrationLogRowAmount(index, amount)
                        },
                        onDeleteButtonClick = {
                            viewModel.removeHydrationLogRow(index)
                        },
                        selectedColor = moduleColor
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AddNutrientButton(
                        color = moduleColor,
                        onClick = {
                            viewModel.addHydrationLogRow()
                        }
                    )
                }
            }
            ColoredDivider(moduleColor)
            // endregion

            // region Bottom Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(
                    text = stringResource(id = R.string.save),
                    onClick = {
                        viewModel.insertLogs()
                        navController.navigateUp()
                    },
                    style = ActionButtonStyle(
                        textColor = Color.White,
                        backgroundColor = moduleColor
                    )
                )
                ActionButton(
                    text = stringResource(id = R.string.history),
                    onClick = { navController.navigate("hydrationHistory") })
            }
            // endregion
        }
    }
}

@Composable
fun RowContent(
    selectableHydrationLogRowData: SelectableHydrationLogRowData,
    items: List<Item>,

    onUiStateChange: (HydrationLogRowUiState) -> Unit,
    onSelectedItemDropdownValueChange: (Item) -> Unit,
    onServingSelected: (Pair<Serving, Float>) -> Unit,
    onAmountValueChange: (Int) -> Unit,
    onDeleteButtonClick: () -> Unit,

    fontSize: TextUnit = 18.sp,
    selectedColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DeleteNutrientButton(
            onClick = onDeleteButtonClick
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column() {
            CustomDropdownMenu(
                items = items,
                itemToString = { it.name },
                currentItemSelection = selectableHydrationLogRowData.selectedItem,
                onItemSelected = { selectedItem ->
                    onUiStateChange(HydrationLogRowUiState.SELECTED)
                    onSelectedItemDropdownValueChange(selectedItem!!)
                },
                width = 170.dp,
                selectedColor = selectedColor
            )
            if (selectableHydrationLogRowData.uiState == HydrationLogRowUiState.SELECTED) {
                Spacer(modifier = Modifier.height(6.dp))
                CustomDropdownMenu(
                    items = selectableHydrationLogRowData.servings!!,
                    itemToString = { servingPair: Pair<Serving, Float> ->
                        servingPair.first.name
                    },
                    currentItemSelection = selectableHydrationLogRowData.selectedServing,
                    onItemSelected = { onServingSelected(it!!) },
                    width = 170.dp,
                    fontSize = 13.sp,
                    selectedColor = selectedColor,
                    textColor = Color.Black.copy(alpha = 0.4f)
                )
            }
        }
        if (selectableHydrationLogRowData.uiState == HydrationLogRowUiState.UNSELECTED) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
            )
        } else if (selectableHydrationLogRowData.uiState == HydrationLogRowUiState.SELECTED) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                    NumberTextField(
                        value = selectableHydrationLogRowData.amount,
                        onValueChange = onAmountValueChange,
                        fontSize = fontSize,
                        isBold = true,
                        maxChar = 4,
                        maxWidth = 50.dp
                    )
                if (selectableHydrationLogRowData.selectedServing != null) {
                    Text(
                        text = " " + enumUnit.values()[selectableHydrationLogRowData.selectedServing!!.first.unitId].symbol,
                        fontSize = (fontSize.value - 2).sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black.copy(alpha = 0.6f),
                    )
                }
                else{
                    Text(
                        text = " " + enumUnit.values()[selectableHydrationLogRowData.servings!![0].first.unitId].symbol,
                        fontSize = (fontSize.value - 2).sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black.copy(alpha = 0.6f),
                    )
                }
            }
        }
    }
    Divider()
}