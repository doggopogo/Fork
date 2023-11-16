package ca.etsmtl.log.fitnesshabits.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

class DashboardViewModel : ViewModel() {
    private val _showHydrationDialog = MutableLiveData(false)
    val showHydrationDialog: LiveData<Boolean> = _showHydrationDialog

    fun setShowHydrationDialog(show: Boolean) {
        _showHydrationDialog.value = show
    }
}