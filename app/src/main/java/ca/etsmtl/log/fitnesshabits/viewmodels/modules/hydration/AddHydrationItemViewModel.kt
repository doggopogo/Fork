package ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.BioactiveCompound
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Item
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Micronutrient
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Serving
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.BioactiveCompoundRepository
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemRepository
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.MicronutrientRepository
import ca.etsmtl.log.fitnesshabits.data.enums.HydrationIndex
import ca.etsmtl.log.fitnesshabits.data.enums.Macronutrient
import ca.etsmtl.log.fitnesshabits.data.enums.NutritionType
import ca.etsmtl.log.fitnesshabits.data.enums.Unit
import ca.etsmtl.log.fitnesshabits.ui.components.modules.NutrientRowUiState
import ca.etsmtl.log.fitnesshabits.ui.components.modules.SelectableNutrientRowData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddHydrationItemViewModel @Inject constructor(
    private val itemRepository: ItemRepository,
    private val micronutrientRepository: MicronutrientRepository,
    private val bioactiveCompoundRepository: BioactiveCompoundRepository
) : ViewModel() {

    // region Title, Description & Hydration Index
    var _itemName by mutableStateOf("New beverage")
        private set

    fun setItemName(itemName: String) {
        _itemName = itemName
    }

    var _itemDescription by mutableStateOf("Description")
        private set

    fun setItemDescription(itemDescription: String) {
        _itemDescription = itemDescription
    }

    var _itemHydrationIndex by mutableStateOf(HydrationIndex.NON_HYDRATING)
        private set

    fun onHydrationIndexSelected(itemHydrationIndex: HydrationIndex) {
        _itemHydrationIndex = itemHydrationIndex
    }
    // endregion

    // region Serving
    var _servingSize by mutableStateOf(1)
        private set

    fun setServingSize(servingSize: Int) {
        _servingSize = servingSize
    }

    var _servingName by mutableStateOf("Glass")
        private set

    fun setServingName(servingName: String) {
        _servingName = servingName
    }

    var _servingAmount by mutableStateOf(100)
        private set

    fun setServingAmount(servingAmount: Int) {
        _servingAmount = servingAmount
    }

    var _servingUnit by mutableStateOf<Unit?>(Unit.MILLILITRE)
        private set

    fun setServingUnit(servingUnit: Unit) {
        _servingUnit = servingUnit
    }

    // endregion

    // region Macronutrient
    var _macronutrientCalories by mutableStateOf(0)
        private set

    fun setMacronutrientCalories(macronutrientCalories: Int) {
        _macronutrientCalories = macronutrientCalories
    }

    var _macronutrientTotalFat by mutableStateOf(0)
        private set

    fun setMacronutrientTotalFat(macronutrientTotalFat: Int) {
        _macronutrientTotalFat = macronutrientTotalFat
    }

    var _macronutrientSaturatedFat by mutableStateOf(0)
        private set

    fun setMacronutrientSaturatedFat(macronutrientSaturatedFat: Int) {
        _macronutrientSaturatedFat = macronutrientSaturatedFat
    }

    var _macronutrientTransFat by mutableStateOf(0)
        private set

    fun setMacronutrientTransFat(macronutrientTransFat: Int) {
        _macronutrientTransFat = macronutrientTransFat
    }

    var _macronutrientPolyunsaturatedFat by mutableStateOf(0)
        private set

    fun setMacronutrientPolyunsaturatedFat(macronutrientPolyunsaturatedFat: Int) {
        _macronutrientPolyunsaturatedFat = macronutrientPolyunsaturatedFat
    }

    var _macronutrientMonounsaturatedFat by mutableStateOf(0)
        private set

    fun setMacronutrientMonounsaturatedFat(macronutrientMonounsaturatedFat: Int) {
        _macronutrientMonounsaturatedFat = macronutrientMonounsaturatedFat
    }

    var _macronutrientTotalCarbohydrate by mutableStateOf(0)
        private set

    fun setMacronutrientTotalCarbohydrate(macronutrientTotalCarbohydrate: Int) {
        _macronutrientTotalCarbohydrate = macronutrientTotalCarbohydrate
    }

    var _macronutrientDietaryFibre by mutableStateOf(0)
        private set

    fun setMacronutrientDietaryFibre(macronutrientDietaryFibre: Int) {
        _macronutrientDietaryFibre = macronutrientDietaryFibre
    }

    var _macronutrientTotalSugars by mutableStateOf(0)
        private set

    fun setMacronutrientTotalSugars(macronutrientTotalSugars: Int) {
        _macronutrientTotalSugars = macronutrientTotalSugars
    }

    var _macronutrientAddedSugars by mutableStateOf(0)
        private set

    fun setMacronutrientAddedSugars(macronutrientAddedSugars: Int) {
        _macronutrientAddedSugars = macronutrientAddedSugars
    }

    var _macronutrientProtein by mutableStateOf(0)
        private set

    fun setMacronutrientProtein(macronutrientProtein: Int) {
        _macronutrientProtein = macronutrientProtein
    }

    var _macronutrientSodium by mutableStateOf(0)
        private set

    fun setMacronutrientSodium(macronutrientSodium: Int) {
        _macronutrientSodium = macronutrientSodium
    }

    var _macronutrientCholesterol by mutableStateOf(0)
        private set

    fun setMacronutrientCholesterol(macronutrientCholesterol: Int) {
        _macronutrientCholesterol = macronutrientCholesterol
    }
    // endregion

    // region Micronutrient
    var _micronutrientsData: List<Micronutrient> = listOf()
        private set

    init {
        viewModelScope.launch {
            val micronutrients = micronutrientRepository.getAllMicronutrients().sortedBy { it.name }
            _micronutrientsData = micronutrients
        }
    }

    var _micronutrientRowDataList = mutableStateListOf<SelectableNutrientRowData<Micronutrient>>()

    fun addMicronutrientRow() {
        _micronutrientRowDataList.add(SelectableNutrientRowData())
    }

    fun removeMicronutrientRow(index: Int) {
        _micronutrientRowDataList[index].selectedNutrient?.let {
            _selectedMicronutrients.remove(it.id)
        }

        _micronutrientRowDataList.removeAt(index)
    }

    fun setMicronutrientUiState(index: Int, uiState: NutrientRowUiState) {
        _micronutrientRowDataList[index] = _micronutrientRowDataList[index].copy(uiState = uiState)
    }

    val _selectedMicronutrients = mutableSetOf<Int>()

    fun setMicronutrientSelectedNutrient(index: Int, selectedNutrient: Micronutrient?) {
        _micronutrientRowDataList[index].selectedNutrient?.let {
            _selectedMicronutrients.remove(it.id)
        }
        selectedNutrient?.let {
            _selectedMicronutrients.add(it.id)
        }

        _micronutrientRowDataList[index] =
            _micronutrientRowDataList[index].copy(selectedNutrient = selectedNutrient)
    }

    fun setMicronutrientNutrientNameTextField(index: Int, nutrientNameTextField: String) {
        _micronutrientRowDataList[index] =
            _micronutrientRowDataList[index].copy(nutrientNameTextField = nutrientNameTextField)
    }

    fun setMicronutrientAmount(index: Int, amount: Int) {
        _micronutrientRowDataList[index] = _micronutrientRowDataList[index].copy(amount = amount)
    }

    fun setMicronutrientUnit(index: Int, unit: Unit) {
        _micronutrientRowDataList[index] = _micronutrientRowDataList[index].copy(unit = unit)
    }

    // endregion

    // region Bioactive Compound
    var _bioactiveCompoundsData: List<BioactiveCompound> = listOf()
        private set

    init {
        viewModelScope.launch {
            val bioactiveCompounds =
                bioactiveCompoundRepository.getAllBioactiveCompounds().sortedBy { it.name }
            _bioactiveCompoundsData = bioactiveCompounds
        }
    }

    var _bioactiveCompoundRowDataList =
        mutableStateListOf<SelectableNutrientRowData<BioactiveCompound>>()

    fun addBioactiveCompoundRow() {
        _bioactiveCompoundRowDataList.add(SelectableNutrientRowData())
    }

    fun removeBioactiveCompoundRow(index: Int) {
        _bioactiveCompoundRowDataList[index].selectedNutrient?.let {
            _selectedBioactiveCompounds.remove(it.id)
        }

        _bioactiveCompoundRowDataList.removeAt(index)
    }

    fun setBioactiveCompoundUiState(index: Int, uiState: NutrientRowUiState) {
        _bioactiveCompoundRowDataList[index] =
            _bioactiveCompoundRowDataList[index].copy(uiState = uiState)
    }

    val _selectedBioactiveCompounds = mutableSetOf<Int>()

    fun setBioactiveCompoundSelectedNutrient(index: Int, selectedNutrient: BioactiveCompound?) {
        _bioactiveCompoundRowDataList[index].selectedNutrient?.let {
            _selectedBioactiveCompounds.remove(it.id)
        }
        selectedNutrient?.let {
            _selectedBioactiveCompounds.add(it.id)
        }

        _bioactiveCompoundRowDataList[index] =
            _bioactiveCompoundRowDataList[index].copy(selectedNutrient = selectedNutrient)
    }

    fun setBioactiveCompoundNutrientNameTextField(index: Int, nutrientNameTextField: String) {
        _bioactiveCompoundRowDataList[index] =
            _bioactiveCompoundRowDataList[index].copy(nutrientNameTextField = nutrientNameTextField)
    }

    fun setBioactiveCompoundAmount(index: Int, amount: Int) {
        _bioactiveCompoundRowDataList[index] =
            _bioactiveCompoundRowDataList[index].copy(amount = amount)
    }

    fun setBioactiveCompoundUnit(index: Int, unit: Unit) {
        _bioactiveCompoundRowDataList[index] =
            _bioactiveCompoundRowDataList[index].copy(unit = unit)
    }
    // endregion

    fun addItemAndServing() {
        viewModelScope.launch {
            itemRepository.insertNewItem(
                item = Item(
                    name = _itemName,
                    description = _itemDescription,
                    typeId = NutritionType.HYDRATION.ordinal,
                    hydrationIndexId = _itemHydrationIndex.ordinal
                ),
                serving = Serving(
                    size = _servingSize,
                    name = _servingName,
                    amount = _servingAmount,
                    unitId = _servingUnit!!.ordinal
                ),
                macronutrients = listOf(
                    Pair(Macronutrient.CALORIES, _macronutrientCalories),
                    Pair(Macronutrient.TOTAL_FAT, _macronutrientTotalFat),
                    Pair(Macronutrient.SATURATED_FAT, _macronutrientSaturatedFat),
                    Pair(Macronutrient.TRANS_FAT, _macronutrientTransFat),
                    Pair(Macronutrient.POLYUNSATURATED_FAT, _macronutrientPolyunsaturatedFat),
                    Pair(Macronutrient.MONOUNSATURATED_FAT, _macronutrientMonounsaturatedFat),
                    Pair(Macronutrient.TOTAL_CARBOHYDRATE, _macronutrientTotalCarbohydrate),
                    Pair(Macronutrient.DIETARY_FIBRE, _macronutrientDietaryFibre),
                    Pair(Macronutrient.TOTAL_SUGARS, _macronutrientTotalSugars),
                    Pair(Macronutrient.ADDED_SUGARS, _macronutrientAddedSugars),
                    Pair(Macronutrient.PROTEIN, _macronutrientProtein),
                    Pair(Macronutrient.SODIUM, _macronutrientSodium),
                    Pair(Macronutrient.CHOLESTEROL, _macronutrientCholesterol)
                ),
                newMicronutrients = _micronutrientRowDataList
                    .filter { it.nutrientNameTextField != "" && it.amount != 0 && it.unit != null }
                    .map {
                        Pair(
                            Micronutrient(
                                name = it.nutrientNameTextField,
                                unitId = it.unit!!.ordinal
                            ), it.amount
                        )
                    },

                micronutrients = _micronutrientRowDataList
                    .filter { it.selectedNutrient != null && it.amount != 0 }
                    .map { Pair(it.selectedNutrient!!, it.amount) },

                newBioactiveCompounds = _bioactiveCompoundRowDataList
                    .filter { it.nutrientNameTextField != "" && it.amount != 0 && it.unit != null }
                    .map {
                        Pair(
                            BioactiveCompound(
                                name = it.nutrientNameTextField,
                                unitId = it.unit!!.ordinal
                            ), it.amount
                        )
                    },

                bioactiveCompounds = _bioactiveCompoundRowDataList
                    .filter { it.selectedNutrient != null && it.amount != 0 }
                    .map { Pair(it.selectedNutrient!!, it.amount) },
            )
            _addItemSuccess = true
        }
    }

    var _addItemSuccess by mutableStateOf(false)
        private set

    fun setAddItemSuccess(addItemSuccess: Boolean) {
        _addItemSuccess = addItemSuccess
    }
}