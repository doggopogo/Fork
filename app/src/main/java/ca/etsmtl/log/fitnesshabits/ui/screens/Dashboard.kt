package ca.etsmtl.log.fitnesshabits.ui.screens

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import ca.etsmtl.log.fitnesshabits.ui.components.drawer.NavigationDrawer
import ca.etsmtl.log.fitnesshabits.ui.components.DashboardHeader
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.ui.components.ModuleCard
import kotlinx.coroutines.launch
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration.AddHydrationLog
import ca.etsmtl.log.fitnesshabits.ui.theme.appText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun Dashboard(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val showHydrationDialog = remember { mutableStateOf(false) }

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
                .padding(horizontal = 32.dp)
        ) {

            Row(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateBand()
            }

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            )
            {
                ModuleCard(
                    title = "Hydratation",
                    icon = R.drawable.icon_hydration_white,
                    backgroundColorIcon = R.color.hydration,
                    onIconClick = { navController.navigate("hydration") },
                    onInfoClick = { showHydrationDialog.value = true }
                )

                ModuleCard(
                    title = "Nourriture",
                    icon = R.drawable.icon_food_white,
                    backgroundColorIcon = R.color.food,
                    onIconClick = { navController.navigate("food") },
                    onInfoClick = { navController.navigate("food") }
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
    }
    if (showHydrationDialog.value) {
        AddHydrationLog(
            navController,
            onDismiss = {
                showHydrationDialog.value = false
            }
        )
    }
}

@Composable
fun DateBand() {
    val current = Calendar.getInstance().time
    Text(
        text = formatDate(current),
        fontSize = 20.sp,
        color = appText,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun formatDate(date: Date): String {
    val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale.CANADA_FRENCH)
    return dateFormatter.format(date)
}

@Preview(showSystemUi = true)
@Composable
fun DashboardPreview() {
    Dashboard(navController = rememberNavController())
}
