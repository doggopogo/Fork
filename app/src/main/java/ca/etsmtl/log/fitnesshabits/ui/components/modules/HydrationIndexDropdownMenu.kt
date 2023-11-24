package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.data.enums.HydrationIndex

@Composable
fun HydrationIndexDropdownMenu(
    onHydrationIndexSelected: (HydrationIndex) -> Unit,
    selectedColor: Color,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(HydrationIndex.NON_HYDRATING) }

    val items = HydrationIndex.values()

    Box {
        TextButton(
            onClick = { isExpanded = true },
            contentPadding = PaddingValues(16.dp)
        ) {
            onHydrationIndexSelected(selectedIndex)
            Row(
                modifier = Modifier.clickable { isExpanded = true }
            ) {
                Image(
                    painter = selectedIndex.getImage(),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        selectedIndex = item
                    }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (item == selectedIndex) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                modifier = Modifier.size(20.dp),
                                tint = selectedColor
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(
                            text = item.getDisplayName(),
                            color = if (item == selectedIndex) {
                                selectedColor
                            } else {
                                Color.Black
                            },
                            fontWeight = if (item == selectedIndex) {
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                    }
                }
            }
        }
    }
}