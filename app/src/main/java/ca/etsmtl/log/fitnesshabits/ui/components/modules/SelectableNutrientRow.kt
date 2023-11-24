package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Nutrient

@Composable
fun <T : Nutrient> SelectableNutrientRow(
    selectableNutrientRowData: SelectableNutrientRowData<T>,
    nutrients: List<T>,
    units: List<ca.etsmtl.log.fitnesshabits.data.enums.Unit>,

    onUiStateChange: (NutrientRowUiState) -> Unit,
    onNutrientNameDropdownValueChange: (T?) -> Unit,
    onNutrientNameTextFieldValueChange: (String) -> Unit,
    onAmountValueChange: (Int) -> Unit,
    onUnitSelected: (ca.etsmtl.log.fitnesshabits.data.enums.Unit?) -> Unit,
    onDeleteButtonClick: () -> Unit,

    fontSize: TextUnit = 18.sp,
    isBold: Boolean = false,
    selectedColor: Color
) {
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        DeleteNutrientButton(
            onClick = onDeleteButtonClick
        )
        Spacer(modifier = Modifier.width(16.dp))
        if (selectableNutrientRowData.uiState != NutrientRowUiState.NEW) {
            CustomDropdownMenu(
                items = nutrients,
                itemToString = { it.name },
                currentItemSelection = selectableNutrientRowData.selectedNutrient,
                onItemSelected = { selectedItem ->
                    if (selectedItem == null) {
                        onUiStateChange(NutrientRowUiState.NEW)
                    } else {
                        onUiStateChange(NutrientRowUiState.SELECTED)
                    }
                    onNutrientNameDropdownValueChange(selectedItem)
                },
                width = 150.dp,
                selectedColor = selectedColor,
                isAddNewEnabled = true
            )
            if (selectableNutrientRowData.uiState == NutrientRowUiState.UNSELECTED) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red)
                )
            } else if (selectableNutrientRowData.uiState == NutrientRowUiState.SELECTED) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    NumberTextField(
                        value = selectableNutrientRowData.amount,
                        onValueChange = onAmountValueChange,
                        fontSize = fontSize,
                        maxChar = 3,
                        maxWidth = 30.dp
                    )
                    Text(
                        text = " " + ca.etsmtl.log.fitnesshabits.data.enums.Unit.values()[selectableNutrientRowData.selectedNutrient!!.unitId].symbol,
                        fontSize = (fontSize.value - 2).sp,
                        fontWeight =
                        if (isBold) {
                            FontWeight.Black
                        } else {
                            FontWeight.Normal
                        },
                        color = Color.Black.copy(alpha = 0.6f),
                    )
                }
            }
        } else if (selectableNutrientRowData.uiState == NutrientRowUiState.NEW) {
            LetterTextField(
                value = selectableNutrientRowData.nutrientNameTextField,
                onValueChange = onNutrientNameTextFieldValueChange,
                fontSize = 18.sp,
                maxWidth = 100.dp,
                maxChar = 25,
                isCentered = true,
                withBackground = true
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NumberTextField(
                    value = selectableNutrientRowData.amount,
                    onValueChange = onAmountValueChange,
                    fontSize = 18.sp,
                    maxWidth = 50.dp,
                    maxChar = 3,
                    isCentered = true,
                    withBackground = true
                )
                CustomDropdownMenu(
                    items = units,
                    itemToString = { it.symbol },
                    currentItemSelection = selectableNutrientRowData.unit,
                    onItemSelected = onUnitSelected,
                    width = 65.dp,
                    selectedColor = selectedColor
                )
            }
        } else {
            Text(text = "ERROR")
        }
    }
}

data class SelectableNutrientRowData<T : Nutrient>(
    var uiState: NutrientRowUiState = NutrientRowUiState.UNSELECTED,
    var selectedNutrient: T? = null,
    var nutrientNameTextField: String = "",
    var amount: Int = 0,
    var unit: ca.etsmtl.log.fitnesshabits.data.enums.Unit? = null
)

enum class NutrientRowUiState {
    UNSELECTED,
    SELECTED,
    NEW;
}