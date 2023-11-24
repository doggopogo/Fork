package ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.etsmtl.log.fitnesshabits.data.enums.NutritionType
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemData
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HydrationViewModel @Inject constructor(
    private val itemRepository: ItemRepository
) : ViewModel() {

    // TODO: Changer plus tard pour get la valeur de BD
    var _hydrationTarget by mutableStateOf(3.0f)
        private set

    fun setHydrationTarget(hydrationTarget: Float) {
        _hydrationTarget = hydrationTarget
    }

    // Get list of hydration items
    private val _hydrationItemsData = MutableLiveData<HashMap<Int, ItemData>>()
    val hydrationItemsData: LiveData<HashMap<Int, ItemData>> = _hydrationItemsData

    fun loadHydrationItemsData(){
        viewModelScope.launch {
            val itemsData = itemRepository.getItemsDataByTypeId(NutritionType.HYDRATION)
            _hydrationItemsData.value = itemsData
        }
    }
}