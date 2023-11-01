package ca.etsmtl.log.fitnesshabits.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.DrawerState
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.R

@Composable
fun AppDrawer(drawerState: DrawerState, content: @Composable () -> Unit) {
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
                        //TODO
        },
        content = content
    )
}

data class DrawerItem(val imageRes: Int, val title: String)
