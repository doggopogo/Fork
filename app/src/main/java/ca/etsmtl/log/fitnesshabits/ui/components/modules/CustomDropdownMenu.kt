package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> CustomDropdownMenu(
    items: List<T>,
    itemToString: (T) -> String,
    currentItemSelection: T? = null,
    onItemSelected: (T?) -> Unit,
    fontSize: TextUnit = 16.sp,
    width: Dp = 100.dp,
    selectedColor: Color,
    isAddNewEnabled: Boolean = false,
    textColor : Color = Color.Black.copy(0.6f)
) {
    var isExpanded by remember { mutableStateOf(false) }

    Box {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Color.Black.copy(0.03f))
                .clickable { isExpanded = true }
                .padding(6.dp)
                .width(width)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (currentItemSelection == null) {
                        "Select"
                    } else {
                        itemToString(currentItemSelection)
//                            .let {
//                            if (it.length > maxChar) it.substring(0, maxChar) + ".." else it
//                        }
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = if (currentItemSelection == null) {
                        Color.Black.copy(0.20f)
                    } else {
                        textColor
                    },
                    fontSize = fontSize,
                    modifier = Modifier.padding(start = 8.dp).weight(1f)
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    tint = Color.Black.copy(alpha = 0.5f),
                    contentDescription = null
                )
            }
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }
        ) {
            if (isAddNewEnabled) {
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        onItemSelected(null)
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add icon",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "New",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    isExpanded = false
                    onItemSelected(item)
                }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (item == currentItemSelection) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                modifier = Modifier.size(20.dp),
                                tint = selectedColor
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(
                            text = itemToString(item),
                            color = if (item == currentItemSelection) {
                                selectedColor
                            } else {
                                Color.Black
                            },
                            fontWeight = if (item == currentItemSelection) {
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