package ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Type
import ca.etsmtl.log.fitnesshabits.data.database.repositories.nutrition.TypeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHydrationItemViewModel @Inject constructor(private val typeRepository: TypeRepository) : ViewModel() {
    // to clean
    private val _hydrationTarget = mutableStateOf(1.0f) // TODO: Changer plustard pour get la valeur de BD
    val hydrationTarget: State<Float> get() = _hydrationTarget
    // Function to update the hydrationTarget
    fun updateHydrationTarget(newValue: Float) { // TODO: a voir si ca reste dans cette ViewModel
        _hydrationTarget.value = newValue
    }


    fun addNutritionItem(type: Type) {
        viewModelScope.launch {
            typeRepository.insertType(type)
        }
    }

    fun addAllNutritionItems(types: List<Type>) {
        viewModelScope.launch {
            typeRepository.insertAllTypes(types)
        }
    }
}

    //    fun addHydrationItem(item: Item) {
//        viewModelScope.launch {
//            itemRepository.insertItem(item)
//        }
//    }
