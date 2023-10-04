package com.example.fitnessandroid.homescreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize()
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
            .border(BorderStroke(1.dp, Color.LightGray), RoundedCornerShape(10.dp))
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
            .width(100.dp)
            .height(120.dp)
            .clickable {
                setShowModal()
            },
        backgroundColor = colorResource(moduleUiState.backgroundColorIcon),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)
    ) {
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
            .clickable {
                moduleUiState.clickOnContent(navController)
            }
            .padding(8.dp)
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
                color = Color.Black,
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
