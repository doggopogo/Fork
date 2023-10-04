package com.example.fitnessandroid.settingsdrawer.drawer

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitnessandroid.R
import com.example.fitnessandroid.Utilities
import com.example.fitnessandroid.api.restrequest.user.APIUser
import com.example.fitnessandroid.fithabdata.FitHabData
import com.example.fitnessandroid.fithabdata.FitHabUiState
import com.example.fitnessandroid.homescreen.HomeScreen
import com.example.fitnessandroid.settingsdrawer.NavDrawerItem
import com.example.fitnessandroid.settingsdrawer.export.ExportScreen
import com.example.fitnessandroid.settingsdrawer.language.LanguageScreen
import com.example.fitnessandroid.settingsdrawer.preference.PreferenceScreen
import com.example.fitnessandroid.settingsdrawer.profile.ProfileScreen
import com.example.fitnessandroid.settingsdrawer.target.TargetScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerScreen(
    context : Context?,
    fitHabUiState : FitHabUiState
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
        drawerBackgroundColor = colorResource(id = R.color.white),
        drawerContent = {
            fitHabUiState.userInfo.user?.let { DrawerHeader(firstName = it.firstName, lastName = it.lastName) }
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        },
        backgroundColor = colorResource(id = R.color.white)
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            DrawerNavigation(context = context, navController = navController, fitHabUiState = fitHabUiState)
        }
    }
}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
    val items = listOf(
        NavDrawerItem.Home,
        NavDrawerItem.Profile,
        NavDrawerItem.Export,
        NavDrawerItem.Target,
        NavDrawerItem.Preference,
        NavDrawerItem.Language,
        NavDrawerItem.Logout
    )
    Column {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            DrawerItem(item = item, selected = currentRoute == item.route, onItemClick = {
                navController.navigate(item.route) {
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            })
        }
    }
}

@Composable
fun DrawerHeader(firstName : String, lastName : String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = 50.dp, horizontal = 20.dp)
    ) {
        Image(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.icon_picture),
            contentDescription = null
        )
        Text(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 30.dp),
            text = "$firstName $lastName",
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6,
            color = Color(0xFF24A788)
        )
    }
}

@Composable
fun DrawerItem(item: NavDrawerItem, selected: Boolean, onItemClick: (NavDrawerItem) -> Unit) {
    val background = if (selected) R.color.white else android.R.color.transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(item) })
            .height(60.dp)
            .background(colorResource(id = background))
            .padding(start = 45.dp)
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(35.dp)
                .width(35.dp)
        )
        Spacer(modifier = Modifier
            .width(20.dp))
        Text(modifier = Modifier
            .padding(horizontal = 20.dp),
            text = item.title,
            fontSize = 18.sp,
            color = Color.Black
        )
    }
}

@Composable
private fun DrawerNavigation(context : Context?, navController: NavHostController, fitHabUiState : FitHabUiState) {
    NavHost(navController, startDestination = NavDrawerItem.Home.route) {
        this.
        composable(NavDrawerItem.Home.route) {
            FitHabData.updateAllModules()
            HomeScreen(fitHabUiState)
        }
        composable(NavDrawerItem.Profile.route) {
            ProfileScreen(fitHabUiState)
        }
        composable(NavDrawerItem.Export.route) {
            ExportScreen(fitHabUiState)
        }
        composable(NavDrawerItem.Target.route) {
            TargetScreen(fitHabUiState)
        }
        composable(NavDrawerItem.Preference.route) {
            PreferenceScreen(fitHabUiState)
        }
        composable(NavDrawerItem.Language.route) {
            LanguageScreen(fitHabUiState)
        }
        composable(NavDrawerItem.Logout.route) {
            APIUser.logout(context)
        }
    }
}

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = {
            Image(modifier = Modifier
                .padding(horizontal = 20.dp)
                .size(30.dp),
                painter = painterResource(id = R.drawable.icon_logo),
                contentDescription = null)
            Text(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                text = stringResource(id = R.string.app_name))
            Image(modifier = Modifier
                .padding(horizontal = 50.dp)
                .size(55.dp),
                painter = painterResource(id = R.drawable.icon_notification),
                contentDescription = null)
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },
        backgroundColor = Color(0xFF24a788),
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
private fun DrawerHeaderPreview() {
    DrawerHeader(firstName = "Allaglo", lastName = "Amivi")
}

@Preview(showBackground = true)
@Composable
private fun DrawerPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
}

@Preview(showSystemUi = true)
@Composable
private fun DrawerScreenPreview() {
    DrawerScreen(null, FitHabUiState(Utilities.userInfoForPreview(), moduleUiStates = emptyList()))
}