package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Log
import ca.etsmtl.log.fitnesshabits.data.enums.HydrationIndex
import ca.etsmtl.log.fitnesshabits.data.enums.Unit
import ca.etsmtl.log.fitnesshabits.data.repositories.nutrition.ItemData
import ca.etsmtl.log.fitnesshabits.ui.components.RoundButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class RowType {
    ITEM,
    LOG,
    ADD_LOG
}

@Composable
fun DataListDisplay(
    title: String,
    itemsData: HashMap<Int, ItemData>,
    logsData: List<Log>? = null,
    rowType: RowType,
    isHydration: Boolean = false,
    isAlcohol: Boolean = false,
    color: Color
) {
    Column {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ColoredDivider(color)

//        val sortedList = when {
//            // Drink list: sort by name
//            dataList.isNotEmpty() && dataList[0] is ListItem.DrinkItem -> {
//                dataList.sortedBy { (it as ListItem.DrinkItem).drink.name }
//            }
//            // HistoryEntry: sort by date
//            dataList.isNotEmpty() && dataList[0] is ListItem.HydrationEntryItem -> {
//                dataList.sortedByDescending { (it as ListItem.HydrationEntryItem).hydrationEntry.timestamp }
//            }
//            else -> dataList
//        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.80f),
        ) {
            if (rowType == RowType.ITEM) {
                items(itemsData.values.toList()) { itemData ->
                    RowItem(
                        itemData = itemData,
                        isHydration = isHydration,
                        isAlcohol = isAlcohol
                    )
                }
            } else if (rowType == RowType.LOG && logsData != null) {
                items(logsData) { logData ->
                    val itemData = itemsData[logData.itemId]
                    RowLog(
                        logData = logData,
                        itemData = itemData!!,
                        isHydration = isHydration,
                        isAlcohol = isAlcohol
                    )
                }
//            } else if (rowType == RowType.ADD_LOG && logsData != null) {
//                items(logsData) { logData ->
//                    val itemData = itemsData[logData.itemId]
//                    RowAddLog(logData = logData, itemData = itemData!!)
//                }
            } else {
                item {
                    Text(text = "ERROR")
                }
            }
        }
        ColoredDivider(color)
    }
}

/**
 * Formate les dates des consommations (Francais)
 */
fun formatUnixTimestamp(unixTimestamp: Long): String {
    val pattern = "'at' h:mma  MM/dd/yy"
    val date = Date(unixTimestamp * 1000)
    val dateFormatter = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormatter.format(date)
}

@Composable
fun RowItem(
    itemData: ItemData,
    isHydration: Boolean = false,
    isAlcohol: Boolean = false
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .animateContentSize(),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 24.dp)
                            .clickable {
                                isExpanded = !isExpanded
                            },
                        imageVector = if (isExpanded) {
                            Icons.Filled.KeyboardArrowUp
                        } else {
                            Icons.Filled.KeyboardArrowDown
                        },
                        contentDescription = "Expand/Collapse"
                    )

                    Column {
                        Text(
                            text = itemData.item.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.Black.copy(),
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.width(180.dp),
                            maxLines = 3
                        )
                        for (serving in itemData.servings) {
                            if (serving.second == 1.0f) {
                                Text(
                                    text = "${serving.first.name} (${serving.first.amount} ${Unit.values()[serving.first.unitId].symbol})",
                                    fontSize = 14.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.width(180.dp),
                                    maxLines = 2
                                )
                            }
                        }
                        if (isHydration && !isAlcohol) {
                            Text(
                                text = HydrationIndex.values()[itemData.item.hydrationIndexId!!].getDisplayName(),
                                fontSize = 13.sp,
                                color = Color.Black.copy(alpha = 0.4f),
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.width(180.dp),
                                maxLines = 2
                            )
                        }
                        else if (!isHydration && isAlcohol) {
                            Text(
                                text = "TODO",
                                fontSize = 13.sp,
                                color = Color.Black.copy(alpha = 0.4f)
                            )
                        }
                        if (!itemData.item.description.isNullOrBlank()){
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = itemData.item.description,
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Black.copy(alpha = 0.5f),
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.width(180.dp),
                                maxLines = 2
                            )
                        }
                    }
                }
                Row {
                    RoundButton(
                        image = Icons.Filled.Edit,
                        tintColor = Color.Black.copy(alpha = 0.6f),
                        onClick = {
                            // TODO
                        })
                    Spacer(modifier = Modifier.width(12.dp))
                    RoundButton(
                        image = Icons.Filled.Delete,
                        tintColor = Color.Red.copy(alpha = 0.6f),
                        onClick = {
                            // TODO
                        }
                    )
                }
            }
            AnimatedVisibility(
                modifier = Modifier.padding(bottom = 24.dp),
                visible = isExpanded
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //Text(text = Macronutrient.CALORIES.getDisplayName())
                        Text(text = "TODO")
                    }
                }
            }
        }
    }
    Divider()
}

@Composable
fun RowLog(
    logData: Log,
    itemData: ItemData,
    isHydration: Boolean = false,
    isAlcohol: Boolean = false
    ) {
        var isExpanded by remember { mutableStateOf(false) }

        Card(
            modifier = Modifier
                .animateContentSize(),
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 24.dp)
                                .clickable {
                                    isExpanded = !isExpanded
                                },
                            imageVector = if (isExpanded) {
                                Icons.Filled.KeyboardArrowUp
                            } else {
                                Icons.Filled.KeyboardArrowDown
                            },
                            contentDescription = "Expand/Collapse"
                        )

                        Column {
                            Text(
                                text = itemData.item.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.Black.copy(),
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.width(180.dp),
                                maxLines = 3
                            )
                            for (serving in itemData.servings) {
                                if (serving.second == 1.0f) {
                                    Text(
                                        text =
                                        "${(serving.first.amount * logData.servingMultiplier).toInt()} " +
                                                "${Unit.values()[serving.first.unitId].symbol}",
                                        fontSize = 14.sp,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.width(180.dp),
                                        maxLines = 2
                                    )
                                }
                            }
                            Text(
                                text = "${formatUnixTimestamp(logData.timestamp)}",
                                fontSize = 14.sp,
                                color = Color.Black.copy(alpha = 0.6f),
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.width(180.dp),
                                maxLines = 2
                            )
                            if (isHydration && !isAlcohol) {
                                Text(
                                    text = HydrationIndex.values()[itemData.item.hydrationIndexId!!].getDisplayName(),
                                    fontSize = 13.sp,
                                    color = Color.Black.copy(alpha = 0.3f),
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier.width(180.dp),
                                    maxLines = 2
                                )
                            }
                            else if (!isHydration && isAlcohol) {
                                Text(
                                    text = "TODO",
                                    fontSize = 13.sp,
                                    color = Color.Black.copy(alpha = 0.3f)
                                )
                            }
                        }
                    }
                    Row {
                        RoundButton(
                            image = Icons.Filled.Edit,
                            tintColor = Color.Black.copy(alpha = 0.6f),
                            onClick = {
                                // TODO
                            })
                        Spacer(modifier = Modifier.width(12.dp))
                        RoundButton(
                            image = Icons.Filled.Delete,
                            tintColor = Color.Red.copy(alpha = 0.6f),
                            onClick = {
                                // TODO
                            }
                        )
                    }
                }
                AnimatedVisibility(
                    modifier = Modifier.padding(bottom = 24.dp),
                    visible = isExpanded
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // TODO
                            //Text(text = Macronutrient.CALORIES.getDisplayName())
                            Text(text = "TODO")
                        }
                    }
                }
            }
        }
        Divider()
}
//
//@Composable
//fun RowAddLog(logData: Log, itemData: ItemData) {
//
//}