package ca.etsmtl.log.fitnesshabits.ui.components.drawer
//https://github.com/philipplackner/NavigationDrawerCompose/
//https://m3.material.io/components/navigation-drawer/specs

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    Box(modifier = Modifier
        .padding(16.dp)
        .padding(top = 24.dp)) {
        Column {
            DrawerHeader(
                userName = "John Doe",
                navController = navController,
                scaffoldState = scaffoldState
            )
            DrawerBody(navController = navController, scaffoldState = scaffoldState)
        }
    }
}

@Composable
fun DrawerHeader(
    userName: String,
    navController: NavController,
    scaffoldState: ScaffoldState
) {
    val scope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navController.navigate("userProfile")
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_picture),
            contentDescription = "User Profile",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape) // makes the image circular
        )
        Spacer(modifier = Modifier.width(24.dp))
        Text(
            text = userName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DrawerBody(
    navController: NavController,
    scaffoldState: ScaffoldState,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
) {
    val scope = rememberCoroutineScope()
    val drawerItems = listOf(
        DrawerItem(
            id = "targets",
            title = "Targets",
            contentDescription = "Go to targets screen",
            icon = R.drawable.icon_target
        ),
        DrawerItem(
            id = "settings",
            title = "Settings",
            contentDescription = "Go to settings screen",
            icon = R.drawable.icon_preference
        ),
        DrawerItem(
            id = "export",
            title = "Export",
            contentDescription = "Export data",
            icon = R.drawable.icon_export
        )
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(drawerItems) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(item.id)
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.contentDescription,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}