package ca.etsmtl.log.fitnesshabits.viewmodels.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ca.etsmtl.log.fitnesshabits.data.database.repositories.HydrationRepository

class AddHydrationViewModelFactory(private val repository: HydrationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddHydrationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddHydrationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
