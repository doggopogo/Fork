package ca.etsmtl.log.fitnesshabits.ui.screens

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.DashboardHeader
import ca.etsmtl.log.fitnesshabits.ui.components.ModuleCard
import ca.etsmtl.log.fitnesshabits.ui.components.drawer.NavigationDrawer
import ca.etsmtl.log.fitnesshabits.ui.theme.activity
import ca.etsmtl.log.fitnesshabits.ui.theme.alcohol
import ca.etsmtl.log.fitnesshabits.ui.theme.appText
import ca.etsmtl.log.fitnesshabits.ui.theme.bloodSugar
import ca.etsmtl.log.fitnesshabits.ui.theme.food
import ca.etsmtl.log.fitnesshabits.ui.theme.hydration
import ca.etsmtl.log.fitnesshabits.ui.theme.sleep
import ca.etsmtl.log.fitnesshabits.ui.theme.supplements
import ca.etsmtl.log.fitnesshabits.ui.theme.toilet
import ca.etsmtl.log.fitnesshabits.ui.theme.weight
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun Dashboard(navController: NavController) {
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
                .padding(horizontal = 32.dp)
        ) {

            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DateBand()
            }
            Divider()

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            )
            {
                ModuleCard(
                    title = stringResource(id = R.string.hydration),
                    icon = R.drawable.icon_hydration_white,
                    backgroundColorIcon = hydration,
                    onIconClick = { navController.navigate("hydration") },
                    onInfoClick = { navController.navigate("addHydrationLog") }
                )

                ModuleCard(
                    title = stringResource(id = R.string.food),
                    icon = R.drawable.icon_food_white,
                    backgroundColorIcon = food,
                    onIconClick = { navController.navigate("food") },
                    onInfoClick = { navController.navigate("food") }
                )


                ModuleCard(
                    title = stringResource(id = R.string.medication_supplement),
                    icon = R.drawable.icon_supplements_white,
                    backgroundColorIcon = supplements,
                    onIconClick = { navController.navigate("medicationSupplement") },
                    onInfoClick = { navController.navigate("medicationSupplement") }
                )

                ModuleCard(
                    title = stringResource(id = R.string.sleep),
                    icon = R.drawable.icon_sleep_white,
                    backgroundColorIcon = sleep,
                    onIconClick = { navController.navigate("sleep") },
                    onInfoClick = { navController.navigate("sleep") }
                )

                ModuleCard(
                    title = stringResource(id = R.string.biobreak),
                    icon = R.drawable.icon_toilet_white,
                    backgroundColorIcon = toilet,
                    onIconClick = { navController.navigate("bioBreak") },
                    onInfoClick = { navController.navigate("bioBreak") }
                )

                ModuleCard(
                    title = stringResource(id = R.string.physical_activity),
                    icon = R.drawable.icon_activity_white,
                    backgroundColorIcon = activity,
                    onIconClick = { navController.navigate("physicalActivity") },
                    onInfoClick = { navController.navigate("physicalActivity") }
                )

                ModuleCard(
                    title = stringResource(id = R.string.weight),
                    icon = R.drawable.icon_weight_white,
                    backgroundColorIcon = weight,
                    onIconClick = { navController.navigate("weight") },
                    onInfoClick = { navController.navigate("weight") }
                )

                ModuleCard(
                    title = stringResource(id = R.string.alcohol),
                    icon = R.drawable.icon_alcohol_white,
                    backgroundColorIcon = alcohol,
                    onIconClick = { navController.navigate("alcohol") },
                    onInfoClick = { navController.navigate("alcohol") }
                )

                ModuleCard(
                    title = stringResource(id = R.string.diabetes),
                    icon = R.drawable.icon_bloodsugar_white,
                    backgroundColorIcon = bloodSugar,
                    onIconClick = { navController.navigate("diabetes") },
                    onInfoClick = { navController.navigate("diabetes") }
                )
                Spacer(modifier = Modifier.padding(vertical = 32.dp))
            }
        }
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
    val dateFormatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    //val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale.CANADA_FRENCH)
    return dateFormatter.format(date)
}

@Composable
fun formatTime(date: Date): String {
    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormatter.format(date)
}

fun unixFormatDate(unixTimestamp: Long): String {
    val date = Date(unixTimestamp)
    val dateFormatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return dateFormatter.format(date)
}

@Composable
fun unixFormatTime(unixTimestamp: Long): String {
    val date = Date(unixTimestamp)
    val timeFormatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormatter.format(date)
}

@Preview(showSystemUi = true)
@Composable
fun DashboardPreview() {
    Dashboard(navController = rememberNavController())
}
