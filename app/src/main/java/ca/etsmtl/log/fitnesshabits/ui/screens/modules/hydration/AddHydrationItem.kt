package ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.data.enums.Macronutrient
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButtonStyle
import ca.etsmtl.log.fitnesshabits.ui.components.modules.AddNutrientButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.CustomDropdownMenu
import ca.etsmtl.log.fitnesshabits.ui.components.modules.HeaderTextField
import ca.etsmtl.log.fitnesshabits.ui.components.modules.HydrationIndexDropdownMenu
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ItemSection
import ca.etsmtl.log.fitnesshabits.ui.components.modules.LetterTextField
import ca.etsmtl.log.fitnesshabits.ui.components.modules.MacronutrientRow
import ca.etsmtl.log.fitnesshabits.ui.components.modules.NumberTextField
import ca.etsmtl.log.fitnesshabits.ui.components.modules.SelectableNutrientRow
import ca.etsmtl.log.fitnesshabits.ui.theme.hydration
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.AddHydrationItemViewModel
import ca.etsmtl.log.fitnesshabits.data.enums.Unit as EnumUnit

@Composable
fun AddHydrationItem(navController: NavController) {
    val viewModel: AddHydrationItemViewModel = hiltViewModel()

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val context = LocalContext.current
    val moduleColor = hydration

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
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // region Item
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderTextField(
                    value = viewModel._itemName,
                    onValueChange = viewModel::setItemName,
                    fontSize = 36.sp,
                    isBold = true
                )
                HeaderTextField(
                    value = viewModel._itemDescription,
                    onValueChange = viewModel::setItemDescription,
                    fontSize = 22.sp,
                )
                HydrationIndexDropdownMenu(
                    onHydrationIndexSelected = viewModel::onHydrationIndexSelected,
                    selectedColor = moduleColor
                )
            }
            // endregion

            Divider()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // region Serving
                ItemSection(
                    name = stringResource(id = R.string.serving_title),
                    color = moduleColor,
                    isExpandedOnLoad = true
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        NumberTextField(
                            value = viewModel._servingSize,
                            onValueChange = viewModel::setServingSize,
                            fontSize = 18.sp,
                            maxWidth = 40.dp,
                            maxChar = 2,
                            isCentered = true,
                            withBackground = true
                        )
                        LetterTextField(
                            value = viewModel._servingName,
                            onValueChange = viewModel::setServingName,
                            fontSize = 18.sp,
                            maxWidth = 100.dp,
                            maxChar = 25,
                            isCentered = true,
                            withBackground = true
                        )
                        Text(
                            text = " : ",
                            fontSize = 18.sp
                        )
                        NumberTextField(
                            value = viewModel._servingAmount,
                            onValueChange = viewModel::setServingAmount,
                            fontSize = 18.sp,
                            maxWidth = 50.dp,
                            maxChar = 3,
                            isCentered = true,
                            withBackground = true
                        )
                        CustomDropdownMenu(
                            items = EnumUnit.values().toList(),
                            itemToString = { it.symbol },
                            currentItemSelection = viewModel._servingUnit,
                            onItemSelected = { item ->
                                viewModel.setServingUnit(item!!)
                            },
                            width = 65.dp,
                            selectedColor = moduleColor
                        )
                    }
                }
                // endregion

                Divider()

                // region Macronutrient
                ItemSection(
                    name = stringResource(id = R.string.macronutrient),
                    color = moduleColor
                ) {
                    MacronutrientRow(
                        value = viewModel._macronutrientCalories,
                        onValueChange = viewModel::setMacronutrientCalories,
                        macronutrientType = Macronutrient.CALORIES,
                        fontSize = 24.sp,
                        isBold = true
                    )
                    MacronutrientRow(
                        value = viewModel._macronutrientTotalFat,
                        onValueChange = viewModel::setMacronutrientTotalFat,
                        macronutrientType = Macronutrient.TOTAL_FAT,
                        fontSize = 20.sp
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        MacronutrientRow(
                            value = viewModel._macronutrientSaturatedFat,
                            onValueChange = viewModel::setMacronutrientSaturatedFat,
                            macronutrientType = Macronutrient.SATURATED_FAT,
                            fontSize = 16.sp
                        )
                        MacronutrientRow(
                            value = viewModel._macronutrientTransFat,
                            onValueChange = viewModel::setMacronutrientTransFat,
                            macronutrientType = Macronutrient.TRANS_FAT,
                            fontSize = 16.sp
                        )
                        MacronutrientRow(
                            value = viewModel._macronutrientPolyunsaturatedFat,
                            onValueChange = viewModel::setMacronutrientPolyunsaturatedFat,
                            macronutrientType = Macronutrient.POLYUNSATURATED_FAT,
                            fontSize = 16.sp
                        )
                        MacronutrientRow(
                            value = viewModel._macronutrientMonounsaturatedFat,
                            onValueChange = viewModel::setMacronutrientMonounsaturatedFat,
                            macronutrientType = Macronutrient.MONOUNSATURATED_FAT,
                            fontSize = 16.sp
                        )
                    }

                    MacronutrientRow(
                        value = viewModel._macronutrientTotalCarbohydrate,
                        onValueChange = viewModel::setMacronutrientTotalCarbohydrate,
                        macronutrientType = Macronutrient.TOTAL_CARBOHYDRATE,
                        fontSize = 20.sp
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        MacronutrientRow(
                            value = viewModel._macronutrientDietaryFibre,
                            onValueChange = viewModel::setMacronutrientDietaryFibre,
                            macronutrientType = Macronutrient.DIETARY_FIBRE,
                            fontSize = 16.sp
                        )
                        MacronutrientRow(
                            value = viewModel._macronutrientTotalSugars,
                            onValueChange = viewModel::setMacronutrientTotalSugars,
                            macronutrientType = Macronutrient.TOTAL_SUGARS,
                            fontSize = 16.sp
                        )
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            MacronutrientRow(
                                value = viewModel._macronutrientAddedSugars,
                                onValueChange = viewModel::setMacronutrientAddedSugars,
                                macronutrientType = Macronutrient.ADDED_SUGARS,
                                fontSize = 14.sp
                            )
                        }
                    }

                    MacronutrientRow(
                        value = viewModel._macronutrientProtein,
                        onValueChange = viewModel::setMacronutrientProtein,
                        macronutrientType = Macronutrient.PROTEIN,
                        fontSize = 20.sp
                    )

                    Divider(modifier = Modifier.padding(12.dp))

                    MacronutrientRow(
                        value = viewModel._macronutrientSodium,
                        onValueChange = viewModel::setMacronutrientSodium,
                        macronutrientType = Macronutrient.SODIUM
                    )
                    MacronutrientRow(
                        value = viewModel._macronutrientCholesterol,
                        onValueChange = viewModel::setMacronutrientCholesterol,
                        macronutrientType = Macronutrient.CHOLESTEROL
                    )
                }
                // endregion

                Divider()

                // region Micronutrient
                ItemSection(
                    name = stringResource(id = R.string.micronutrient),
                    color = moduleColor
                ) {
                    viewModel._micronutrientRowDataList.forEachIndexed { index, selectableNutrientRowData ->
                        SelectableNutrientRow(
                            selectableNutrientRowData = selectableNutrientRowData,
                            onUiStateChange = { uiState ->
                                viewModel.setMicronutrientUiState(index, uiState)
                            },
                            onNutrientNameDropdownValueChange = { nutrientName ->
                                viewModel.setMicronutrientSelectedNutrient(index, nutrientName)
                            },
                            onNutrientNameTextFieldValueChange = { text ->
                                viewModel.setMicronutrientNutrientNameTextField(index, text)
                            },
                            onAmountValueChange = { amount ->
                                viewModel.setMicronutrientAmount(index, amount)
                            },
                            onUnitSelected = { unit ->
                                viewModel.setMicronutrientUnit(index, unit!!)
                            },
                            onDeleteButtonClick = {
                                viewModel.removeMicronutrientRow(index)
                            },
                            nutrients = viewModel._micronutrientsData.filter { it.id !in viewModel._selectedMicronutrients },
                            units = EnumUnit.getWeightUnits(),
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
                                viewModel.addMicronutrientRow()
                            }
                        )
                    }
                }
                // endregion

                Divider()

                // region Bioactive compound
                ItemSection(
                    name = stringResource(id = R.string.bioactive_compound_title),
                    color = moduleColor
                ) {
                    viewModel._bioactiveCompoundRowDataList.forEachIndexed { index, selectableNutrientRowData ->
                        SelectableNutrientRow(
                            selectableNutrientRowData = selectableNutrientRowData,
                            onUiStateChange = { uiState ->
                                viewModel.setBioactiveCompoundUiState(index, uiState)
                            },
                            onNutrientNameDropdownValueChange = { nutrientName ->
                                viewModel.setBioactiveCompoundSelectedNutrient(index, nutrientName)
                            },
                            onNutrientNameTextFieldValueChange = { text ->
                                viewModel.setBioactiveCompoundNutrientNameTextField(index, text)
                            },
                            onAmountValueChange = { amount ->
                                viewModel.setBioactiveCompoundAmount(index, amount)
                            },
                            onUnitSelected = { unit ->
                                viewModel.setBioactiveCompoundUnit(index, unit!!)
                            },
                            onDeleteButtonClick = {
                                viewModel.removeBioactiveCompoundRow(index)
                            },
                            nutrients = viewModel._bioactiveCompoundsData.filter { it.id !in viewModel._selectedMicronutrients },
                            units = EnumUnit.getWeightUnits(),
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
                                viewModel.addBioactiveCompoundRow()
                            }
                        )
                    }
                }
                // endregion

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ActionButton(
                        text = stringResource(id = R.string.add),
                        fontSize = 17.sp,
                        style = ActionButtonStyle(
                            textColor = Color.White,
                            backgroundColor = moduleColor
                        ),
                        onClick = {
                            viewModel.addItemAndServing()
                            navController.navigateUp()
                        }
                    )
                    LaunchedEffect(viewModel._addItemSuccess) {
                        if (viewModel._addItemSuccess) {
                            Toast.makeText(
                                context,
                                R.string.beverage_create_success,
                                Toast.LENGTH_SHORT
                            ).show()
                            viewModel.setAddItemSuccess(false)
                        }
                    }
                }
            }
        }
    }
}