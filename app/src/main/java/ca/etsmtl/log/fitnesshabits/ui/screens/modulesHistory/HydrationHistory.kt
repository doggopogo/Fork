package ca.etsmtl.log.fitnesshabits.ui.screens.modulesHistory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.Drink
import ca.etsmtl.log.fitnesshabits.ui.SampleData
import ca.etsmtl.log.fitnesshabits.ui.SampleData.hydrationEntries
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButtonStyle
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DataListDisplay
import ca.etsmtl.log.fitnesshabits.ui.components.modules.DateFilterButtons
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ListItem
import ca.etsmtl.log.fitnesshabits.ui.components.modules.RowContent
import ca.etsmtl.log.fitnesshabits.ui.components.modules.RowEntry
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun HydrationHistory(navController: NavController){
    var graphExpanded by remember { mutableStateOf(false) }
    var graphButtonText = if (graphExpanded) "Cacher le graphique" else "Afficher le graphique"

    var buttonStyle = ActionButtonStyle(
        textColor = Color.White,
        backgroundColor = colorResource(id = R.color.hydration)
    )

    Column {
        Header(
            title = "Hydration",
            R.color.hydration,
            navigateBack = { navController.navigateUp() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            //button for graph
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                ActionButton(
                    text = graphButtonText,
                    onClick = { graphExpanded = !graphExpanded },
                    style = buttonStyle
                )
            }
            //graph collapsible
            if (graphExpanded) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Graph goes here
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.3f)
                            .background(Color.DarkGray)
                    )
                    DateFilterButtons(buttonStyle.backgroundColor)
                }
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            //liste de l'historique
            Column() {
                DataListDisplay(
                    title = "Historique de consommation",
                    dataList = hydrationEntries.map { ListItem.HydrationEntryItem(it) },
                    color = R.color.hydration
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SimpleComposablePreview() {
    HydrationHistory(navController = rememberNavController())
}