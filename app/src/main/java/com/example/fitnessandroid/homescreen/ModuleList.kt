package com.example.fitnessandroid.homescreen

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.fitnessandroid.R
import com.example.fitnessandroid.Utilities
import com.example.fitnessandroid.common.PreviewComposable
import com.example.fitnessandroid.fithabdata.FitHabUiState
import com.example.fitnessandroid.homescreen.alcohol.user.UserAlcoholModal
import com.example.fitnessandroid.homescreen.bloodSugar.user.UserBloodSugarModal
import com.example.fitnessandroid.homescreen.sleep.user.UserSleepModal
import com.example.fitnessandroid.homescreen.toilet.user.UserToiletModal
import com.example.fitnessandroid.homescreen.weight.user.UserWeightModal
import com.example.fitnessandroid.ui.theme.appGreen
import com.example.fitnessandroid.ui.theme.appText
import com.example.fitnessandroid.ui.theme.backgroundgray
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessandroid.ui.theme.FitnessAndroidTheme
import com.example.fitnessandroid.ui.theme.appDefaultGray
import java.util.*


@Composable
fun ModuleList(
    modifier: Modifier = Modifier,
    fitHabUiState: FitHabUiState,
    navController: NavHostController
) {

    val openDialog = remember { mutableStateOf(false)  }
    val currentModuleUiState = remember { mutableStateOf(fitHabUiState.moduleUiStates.first())  }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Row(
            modifier = Modifier
                //.fillMaxSize()
                .height(40.dp)
                .background(backgroundgray)
        ) {
            val currentDate = Calendar.getInstance()
            DateBand()
        }

        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            items(fitHabUiState.moduleUiStates) { moduleUiState ->
                ModuleRow(moduleUiState, navController) {
                    openDialog.value = true
                    currentModuleUiState.value = moduleUiState
                }
                Spacer(Modifier.padding(8.dp))
            }
        }
        if (openDialog.value) {
            currentModuleUiState.value.iconModal?.let {
                ModuleModal(
                    it,
                    currentModuleUiState.value.title,
                    currentModuleUiState.value.backgroundColorIcon,
                    { currentModuleUiState.value.ClickOnIcon() }
                    ) {
                    openDialog.value = false
                }
            }
        }

    }
}

@Composable
private fun ModuleRow(
    moduleUiState : ModuleUiState,
    navController: NavController,
    setShowModal : () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.White)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        ModuleItemIcon(moduleUiState = moduleUiState, setShowModal = setShowModal)
        ModuleItemContent(moduleUiState = moduleUiState, navController = navController)
    }
}

@Composable
private fun ModuleItemIcon(
    moduleUiState : ModuleUiState,
    setShowModal : () -> Unit
){
    Card(
        modifier = Modifier
            .width(125.dp)
            .height(120.dp)
            .clickable {
                setShowModal()
            },
        backgroundColor = colorResource(moduleUiState.backgroundColorIcon),
        shape = RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp, topEnd = 10.dp)
    ) {

        Row(
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .clip(CircleShape)
                    .background(Color.White),
            ){

            }
        }

        moduleUiState.icon?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = "Module logo",
                modifier = Modifier.padding(30.dp),
                tint = Color.White
            )
        }
    }

}

@Composable
private fun ModuleItemContent(
    moduleUiState : ModuleUiState,
    navController: NavController
){
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.White)
            .clickable {
                moduleUiState.clickOnContent(navController)
            }
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        val percent = if (moduleUiState.stats.isEmpty()) 1f else 0.7f
        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth(percent)
        ) {
            Text(
                text = moduleUiState.title,
                maxLines = 2,
                fontSize = 24.sp,
                color = appText,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            ShowStatsContent(statsList = moduleUiState.statsGrid)
        }
        if (moduleUiState.stats.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(Modifier.padding(top = 24.dp))
                moduleUiState.stats.forEach {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = it,
                        maxLines = 2,
                        fontSize = 18.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Right
                    )
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 2.dp))
        }
    }
}

@Composable
fun ShowStatsContent(statsList : List<Pair<String,String>>){
    Box(modifier = Modifier.fillMaxHeight()) {
        val numberOfGrid = if (statsList.size <=2) 1 else 2
        LazyVerticalGrid(
            modifier = Modifier.height(50.dp),
            columns = GridCells.Fixed(numberOfGrid)
        ) {
            items(statsList) { value ->
                Row(
                    Modifier.fillMaxHeight()
                ) {
                    if (value.first.isNotBlank()) {
                        Text(
                            text = value.first,
                            fontSize = 12.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                    }
                    Text(
                        text = value.second,
                        fontSize = 12.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ModuleListPreview() {
    val navController = rememberNavController()
    PreviewComposable(backgroundColor = Color.White) {
        val list = listOf(
            ModuleUiState(
                icon = R.drawable.icon_hydration_white,
                iconModal = R.drawable.icon_hydration,
                backgroundColorIcon = R.color.hydration,
                title = "Hydration",
                stats = listOf("0.000", "litre"),
                statsGrid = listOf(
                    Pair("", "Autres informations")
                )
            ),
            ModuleUiState(
                icon = R.drawable.icon_food_white,
                iconModal = R.drawable.icon_food,
                backgroundColorIcon = R.color.food,
                title = "Food",
                statsGrid =  listOf(
                    Pair("Protéines", "0.00g"),
                    Pair("Glucides", "100.00g"),
                    Pair("Fibres", "250.00g"),
                    Pair("Lipides", "0.00g")
                )
            ),
            ModuleUiState(
                icon = R.drawable.icon_bloodsugar_white,
                iconModal = R.drawable.icon_bloodsugar,
                backgroundColorIcon = R.color.bloodSugar,
                title = "Blood Sugar",
                stats = listOf("00.0", "mmol/L"),
                clickOnIconAction = { UserBloodSugarModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.BloodSugar.route)}
            ),
            ModuleUiState(
                icon = R.drawable.icon_alcohol_white,
                iconModal = R.drawable.icon_alcohol,
                backgroundColorIcon = R.color.alcohol,
                title = "Alcohol",
                clickOnIconAction = { UserAlcoholModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Alcohol.route)}
            ),
            ModuleUiState(
                icon = R.drawable.icon_weight_white,
                iconModal = R.drawable.icon_weight,
                backgroundColorIcon = R.color.weight,
                title = "Weight",
                clickOnIconAction = { UserWeightModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Weight.route)}
            ),
            ModuleUiState(
                icon = R.drawable.icon_sleep_white,
                iconModal = R.drawable.icon_sleep,
                backgroundColorIcon = R.color.sleep,
                title = "Sleep",
                clickOnIconAction = { UserSleepModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Sleep.route)}
            ),
            ModuleUiState(
                icon = R.drawable.icon_toilet_white,
                iconModal = R.drawable.icon_toilet,
                backgroundColorIcon = R.color.toilet,
                title = "Toilet",
                clickOnIconAction = { UserToiletModal() },
                clickOnContentAction = { navController: NavController ->  navController.navigate(HomeModuleItem.Toilet.route)}
            )
        )
        Column {
            ModuleList(
                modifier = it,
                fitHabUiState = FitHabUiState(userInfo = Utilities.userInfoForPreview(), moduleUiStates = list),
                navController
            )
        }
    }
}

@Composable
fun DateBand() {

    val context = LocalContext.current // Get the current context

    var selectedDate by remember { mutableStateOf(Calendar.getInstance()) }

    // Initialize selectedDate with the current date if it's not already set
    if (selectedDate.timeInMillis == Calendar.getInstance().timeInMillis) {
        selectedDate = Calendar.getInstance()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 3.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    previousDate(selectedDate)
                    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    println("La nouvelle date est : " + dateFormat.format(selectedDate.time))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.left_previous_vert_2x),
                    contentDescription = "Previous",
                    tint = appGreen,
                    modifier = Modifier
                        .size(50.dp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .clickable {

                        openDatePickerDialog(context, selectedDate) { newDate ->
                            selectedDate = newDate
                        }

                    }
            ) {

                Text(
                    text = formatDate(selectedDate),
                    style = MaterialTheme.typography.h1
                )

            }

            IconButton(onClick = {
                nextDate(selectedDate)
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                println("La nouvelle date est : " + dateFormat.format(selectedDate.time))
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.right_next_vert_2x),
                    contentDescription = "Next",
                    tint = appGreen,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }
}

@Composable
fun formatDate(calendar: Calendar): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return dateFormat.format(calendar.time)
}

fun nextDate(calendar: Calendar) {
    calendar.add(Calendar.DAY_OF_YEAR, 1)
}

fun previousDate(calendar: Calendar) {
    calendar.add(Calendar.DAY_OF_YEAR, -1)
}

private fun openDatePickerDialog(context: Context, selectedDate: Calendar, onDateSelected: (Calendar) -> Unit) {

    val listener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        selectedDate.set(Calendar.YEAR, year)
        selectedDate.set(Calendar.MONTH, monthOfYear)
        selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        onDateSelected(selectedDate)
    }

    val initialYear = selectedDate.get(Calendar.YEAR)
    val initialMonth = selectedDate.get(Calendar.MONTH)
    val initialDay = selectedDate.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        listener,
        initialYear,
        initialMonth,
        initialDay
    )

    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    println("La nouvelle date est : " + dateFormat.format(selectedDate.time))

    datePickerDialog.show()
}

@Preview(showBackground = true)
@Composable
fun DateBandPreview() {
    FitnessAndroidTheme() {
        DateBand()
    }
}



