package ca.etsmtl.log.fitnesshabits.ui.screens.modules.weight

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.ui.components.modules.BottomButtons
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditNotificationButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.EditTargetButton
import ca.etsmtl.log.fitnesshabits.ui.theme.weight

@Composable
fun Weight(navController: NavController) {
    Column (modifier = Modifier.background(Color.White)){
        Header(
            title = stringResource(id = R.string.weight),
            weight,
            navigateBack = { navController.navigateUp() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                EditNotificationButton {}
                Spacer(modifier = Modifier.width(8.dp))
                EditTargetButton("10L", weight) {}
            }
//            DataListDisplay(
//                title = "Quantité totale bues : 600 mL",
//                data = SampleData.drinks.map { ListItem.DrinkItem(it) }, // to change with actual dataList and item type
//                color = R.color.weight
//            )
            BottomButtons(
                onAddClick = { },
                onShowAllClick = {}
            )
        }
    }
}