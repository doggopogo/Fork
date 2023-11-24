package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.theme.appGreen

@Composable
fun BottomButtons(
    color: Color = appGreen,
    addButtonEnabled : Boolean = false,
    onAddClick :() -> Unit,
    onShowAllClick :() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (addButtonEnabled) {
            // Add button
            ActionButton(
                text = stringResource(id = R.string.add),
                onClick = onAddClick,
                style = ActionButtonStyle(
                    textColor = Color.White,
                    backgroundColor = color
                )
            )
        }
        // Show all button
        ActionButton(
            text = stringResource(id = R.string.show_all),
            onClick = onShowAllClick
        )
    }
}