package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

class ModuleUiState(
    val icon: Int? = null,
    val iconModal: Int? = null,
    val backgroundColorIcon: Int,
    val title: String,
    var stats: List<String> = emptyList(),
    var statsGrid: List<Pair<String,String>> = emptyList(),
    private val clickOnIconAction: @Composable () -> Unit = { Log.d("CLICK", "ON ICON")},
    private val clickOnContentAction : (NavController) -> Unit = { _: NavController -> Log.d("CLICK", "ON CONTENT")}
) {
    @Composable
    fun ClickOnIcon() {
        return clickOnIconAction()
    }

    fun clickOnContent(navController: NavController) {
        clickOnContentAction(navController)
    }
}