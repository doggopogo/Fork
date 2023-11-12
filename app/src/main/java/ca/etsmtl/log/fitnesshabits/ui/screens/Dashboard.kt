package ca.etsmtl.log.fitnesshabits.ui.screens

import ca.etsmtl.log.fitnesshabits.ui.components.drawer.NavigationDrawer
import ca.etsmtl.log.fitnesshabits.ui.components.DashboardHeader
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.etsmtl.log.fitnesshabits.ui.components.ModuleCard
import kotlinx.coroutines.launch
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.data.database.repositories.HydrationRepository
import ca.etsmtl.log.fitnesshabits.ui.screens.modulesDialogWindows.HydrationDialogWindow
import ca.etsmtl.log.fitnesshabits.viewmodels.DashboardViewModel
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.AddHydrationViewModel
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.AddHydrationViewModelFactory

@Composable
fun Dashboard(
    navController: NavController,
    addHydrationRepository: HydrationRepository,
    dashboardViewModel: DashboardViewModel = viewModel(),
) {
    // Create the ViewModelFactory with the repository
    val addHydrationViewModelFactory = AddHydrationViewModelFactory(addHydrationRepository)

    // Obtain the ViewModel instance using the factory
    val addHydrationViewModel: AddHydrationViewModel = viewModel(factory = addHydrationViewModelFactory)
    val showHydrationDialog = dashboardViewModel.showHydrationDialog.observeAsState()
    val hydrationTypes by addHydrationViewModel.hydrationTypes.observeAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DashboardHeader(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            NavigationDrawer(navController = navController, scaffoldState = scaffoldState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState())
        )
        {
            ModuleCard(
                title = "Hydratation",
                icon = R.drawable.icon_hydration_white,
                backgroundColorIcon = R.color.hydration,
                onIconClick = { navController.navigate("hydration") },
                onInfoClick = { dashboardViewModel.setShowHydrationDialog(true) }
            )

            ModuleCard(
                title = "Nourriture",
                icon = R.drawable.icon_food_white,
                backgroundColorIcon = R.color.food,
                onIconClick = { navController.navigate("nutrition") },
                onInfoClick = { navController.navigate("nutrition") }
            )


            ModuleCard(
                title = "Suppléments",
                icon = R.drawable.icon_supplements_white,
                backgroundColorIcon = R.color.supplements,
                onIconClick = { navController.navigate("medicationSupplement") },
                onInfoClick = { navController.navigate("medicationSupplement") }
            )

            ModuleCard(
                title = "Sommeil",
                icon = R.drawable.icon_sleep_white,
                backgroundColorIcon = R.color.sleep,
                onIconClick = { navController.navigate("sleep") },
                onInfoClick = { navController.navigate("sleep") }
            )

            ModuleCard(
                title = "Toilette",
                icon = R.drawable.icon_toilet_white,
                backgroundColorIcon = R.color.toilet,
                onIconClick = { navController.navigate("bioBreak") },
                onInfoClick = { navController.navigate("bioBreak") }
            )

            ModuleCard(
                title = "Activités",
                icon = R.drawable.icon_activity_white,
                backgroundColorIcon = R.color.activity,
                onIconClick = { navController.navigate("physicalActivity") },
                onInfoClick = { navController.navigate("physicalActivity") }
            )

            ModuleCard(
                title = "Poids",
                icon = R.drawable.icon_weight_white,
                backgroundColorIcon = R.color.weight,
                onIconClick = { navController.navigate("weight") },
                onInfoClick = { navController.navigate("weight") }
            )

            ModuleCard(
                title = "Alcohol",
                icon = R.drawable.icon_alcohol_white,
                backgroundColorIcon = R.color.alcohol,
                onIconClick = { navController.navigate("alcohol") },
                onInfoClick = { navController.navigate("alcohol") }
            )

            ModuleCard(
                title = "Glycémie",
                icon = R.drawable.icon_bloodsugar_white,
                backgroundColorIcon = R.color.bloodSugar,
                onIconClick = { navController.navigate("diabetes") },
                onInfoClick = { navController.navigate("diabetes") }
            )
        }
    }
    if (showHydrationDialog.value == true) {
        HydrationDialogWindow(hydrationTypes = hydrationTypes, onDismiss = { dashboardViewModel.setShowHydrationDialog(false) })
    }
}


