package com.example.fitnessandroid.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessandroid.R
import com.example.fitnessandroid.common.PreviewComposable

data class SwitchItem(val imageId: Int, val text: String, val toggleValue:String, var isChecked: Boolean)

@Composable
fun Switches(
    modifier : Modifier = Modifier,
    items : List<SwitchItem>,
    onToggle : (List<SwitchItem>) -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        items.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = item.imageId),
                    contentDescription = item.text,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.text,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 20.sp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Switch(
                    checked = item.isChecked,
                    onCheckedChange = {
                        item.isChecked = it
                        onToggle(items)
                    },
                    modifier = Modifier.width(64.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SwitchesPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        Switches(items = listOf(
                SwitchItem(
                    imageId = R.drawable.icon_food,
                    text = "Food",
                    toggleValue = "food",
                    isChecked = true
                ),
                SwitchItem(
                    imageId = R.drawable.icon_sleep,
                    text = "Sleep",
                    toggleValue = "sleep",
                    isChecked = false
                )
            )
        ) {

        }
    }
}