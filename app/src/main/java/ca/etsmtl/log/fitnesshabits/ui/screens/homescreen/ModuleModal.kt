package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessAndroidTheme

@Composable
fun ModuleModal(
    icon : Int,
    title : String,
    backgroundColorIcon : Int,
    ClickOnIcon : @Composable () -> Unit,
    setShowModal : () -> Unit
) {
    FitnessAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
        ) {
            AlertDialog(
                onDismissRequest = {
                    setShowModal()
                },
                {
                    TopBarModalModule(icon,title,backgroundColorIcon)
                    ClickOnIcon()
                },
                backgroundColor = Color.White
            )
        }
    }
}

@Composable
private fun TopBarModalModule(
    icon : Int,
    title : String,
    backgroundColorIcon : Int
) {
    TopAppBar(
        title = {
            Image(
                modifier = Modifier
                    .padding(horizontal = 25.dp)
                    .size(30.dp),
                painter = painterResource(id = icon),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = title,
            )
        },
        backgroundColor = Color.White,
        contentColor = colorResource(backgroundColorIcon)
    )
}