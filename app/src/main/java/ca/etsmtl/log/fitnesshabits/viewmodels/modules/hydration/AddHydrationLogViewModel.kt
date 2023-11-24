package ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Item
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Log
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Serving
import ca.etsmtl.log.fitnesshabits.data.enums.NutritionType
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemData
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemRepository
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.LogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddHydrationLogViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val logRepository: LogRepository
) : ViewModel() {

    var _selectedDateAndTime by mutableStateOf(System.currentTimeMillis())
    private set

    fun setSelectedDateAndTime(timestamp: Long){
        _selectedDateAndTime = timestamp
    }

    /// Get list of hydration item data
    private val _hydrationItemsData = MutableLiveData<HashMap<Int, ItemData>>()

    var _hydrationLogRow = mutableStateListOf(SelectableHydrationLogRowData())

    fun addHydrationLogRow() {
        _hydrationLogRow.add(SelectableHydrationLogRowData())
    }

    fun removeHydrationLogRow(index: Int) {
        _hydrationLogRow.removeAt(index)
    }

    /// Get list of hydration items
    var _hydrationItems = mutableListOf<Item>()

    var _hydrationServings = mutableMapOf<Int, List<Pair<Serving, Float>>>()

    fun setHydrationLogRowUiState(index: Int, uiState: HydrationLogRowUiState) {
        _hydrationLogRow[index] = _hydrationLogRow[index].copy(uiState = uiState)
    }

    fun setHydrationLogRowSelectedItem(index: Int, selectedItem: Item) {
        _hydrationLogRow[index] =
            _hydrationLogRow[index].copy(selectedItem = selectedItem)
        _hydrationLogRow[index] =
            _hydrationLogRow[index].copy(servings = _hydrationServings[selectedItem.id])
        _hydrationLogRow[index] = _hydrationLogRow[index].copy(selectedServing = null)
        _hydrationLogRow[index] = _hydrationLogRow[index].copy(amount = 0)
    }

    fun setHydrationLogRowSelectedServing(index: Int, serving: Pair<Serving, Float>) {
        _hydrationLogRow[index] = _hydrationLogRow[index].copy(selectedServing = serving)
        _hydrationLogRow[index] = _hydrationLogRow[index].copy(amount = serving.first.amount)
    }

    fun setHydrationLogRowAmount(index: Int, amount: Int) {
        _hydrationLogRow[index] = _hydrationLogRow[index].copy(amount = amount)
    }

    fun loadHydrationAddLogData(){
        viewModelScope.launch {
            val itemsData = itemRepository.getItemsDataOrderedByUsage(NutritionType.HYDRATION)
            _hydrationItemsData.value = itemsData

            for (itemData in itemsData.values.toList()) {
                _hydrationItems.add(itemData.item)
                _hydrationServings[itemData.item.id] = itemData.servings
            }
        }
    }

    init{
        loadHydrationAddLogData()
    }

    fun insertLogs(){
        viewModelScope.launch {
            val logsToAdd = mutableListOf<Log>()

            _hydrationLogRow.forEach { selectableHydrationLogRowData ->
                if (selectableHydrationLogRowData.selectedItem != null && selectableHydrationLogRowData.amount != 0) {
                    var baseAmount = 0f;
                    for (pair in selectableHydrationLogRowData.servings!!) {
                        if (pair.second == 1.0f) {
                            baseAmount = pair.first.amount.toFloat()
                        }
                    }
                    logsToAdd.add(
                        Log(
                            itemId = selectableHydrationLogRowData.selectedItem!!.id,
                            timestamp = _selectedDateAndTime / 1000,
                            servingMultiplier = selectableHydrationLogRowData.amount.toFloat() / baseAmount
                        )
                    )
                }
            }
            logRepository.insertAllLogs(logsToAdd)
        }
    }
}

data class SelectableHydrationLogRowData(
    var uiState: HydrationLogRowUiState = HydrationLogRowUiState.UNSELECTED,
    var selectedItem: Item? = null,
    var servings : List<Pair<Serving, Float>>? = null,
    var selectedServing : Pair<Serving, Float>? = null,
    var amount: Int = 0,
)

enum class HydrationLogRowUiState {
    UNSELECTED,
    SELECTED
}