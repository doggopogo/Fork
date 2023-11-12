package ca.etsmtl.log.fitnesshabits.viewmodels.modules

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ca.etsmtl.log.fitnesshabits.data.database.entities.HydrationType
import ca.etsmtl.log.fitnesshabits.data.database.repositories.HydrationRepository
import kotlinx.coroutines.launch

class AddHydrationViewModel(private val repository: HydrationRepository) : AndroidViewModel(Application()) {

    // Function to insert a new hydration entry
    fun insertHydrationEntry(hydrationType: HydrationType) {
        viewModelScope.launch {
            repository.insertHydrationType(hydrationType)
        }
    }

    val hydrationTypes: LiveData<List<HydrationType>> = repository.getAllHydrationTypes()

    // Add other functions to interact with the database as needed
}