package ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Log
import ca.etsmtl.log.fitnesshabits.data.enums.NutritionType
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemData
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemRepository
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.LogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HydrationHistoryViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val logRepository: LogRepository
) : ViewModel() {

    /// Get list of hydration items
    private val _hydrationItemsData = MutableLiveData<HashMap<Int, ItemData>>()
    val hydrationItemsData: LiveData<HashMap<Int, ItemData>> = _hydrationItemsData

    // Get list of logs items
    private val _hydrationLogsData = MutableLiveData<List<Log>>()
    val hydrationLogsData: LiveData<List<Log>> = _hydrationLogsData

    fun loadHydrationHistoryData(){
        viewModelScope.launch {
            val logs = logRepository.getLogsByItemTypeId(NutritionType.HYDRATION)
            _hydrationLogsData.value = logs

            val itemsDataMap = HashMap<Int, ItemData>()
            logs.forEach { log ->
                val itemData = itemRepository.getItemDataByItemId(log.itemId)
                itemsDataMap[log.itemId] = itemData
            }
            _hydrationItemsData.value = itemsDataMap
        }
    }
}