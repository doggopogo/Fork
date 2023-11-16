package ca.etsmtl.log.fitnesshabits.ui.components.modules

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
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.Drink
import ca.etsmtl.log.fitnesshabits.ui.HydrationEntry
import ca.etsmtl.log.fitnesshabits.ui.SampleData.hydrationEntries
import ca.etsmtl.log.fitnesshabits.ui.components.RoundButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// Sealed class to represent different types of items in the data list
sealed class ListItem {
    data class DrinkItem(val drink: Drink) : ListItem()
    data class HydrationEntryItem(val hydrationEntry: HydrationEntry) : ListItem()
}
@Composable
fun <T : ListItem> DataListDisplay(
    title: String,
    dataList: List<T> = emptyList(),
    color: Int
) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp)
    )
    {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))

        ColoredDivider(color)

        Spacer(modifier = Modifier.height(8.dp))
        RowContent(dataList)
    }
}

/**
 * Sort the data list based on the type of list item
 */
@Composable
fun <T : ListItem>  RowContent(dataList: List<T>) {
    // Sort the list based on the type
    val sortedList = when {
        // Drink list: sort by name
        dataList.isNotEmpty() && dataList[0] is ListItem.DrinkItem -> {
            dataList.sortedBy { (it as ListItem.DrinkItem).drink.name }
        }
        // HistoryEntry: sort by date
        dataList.isNotEmpty() && dataList[0] is ListItem.HydrationEntryItem -> {
            dataList.sortedByDescending { (it as ListItem.HydrationEntryItem).hydrationEntry.timestamp }
        }
        else -> dataList
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.80f),
    ) {
        items(sortedList) { data ->
            RowEntry(data)
        }
    }
}

/**
 * Display the row content based on the type of list item
 */
@Composable
fun RowEntry(item: ListItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        when (item) {
            is ListItem.DrinkItem -> {
                HydrationRowContent(item.drink)
            }
            is ListItem.HydrationEntryItem -> {
                HydrationHistoryRowContent(item.hydrationEntry)
            }
        }
        Row {
            RoundButton(R.drawable.icon_edit_pencil, onClick = { /* Handle edit click */ })
            Spacer(modifier = Modifier.width(8.dp))
            RoundButton(
                R.drawable.icon_delete_trashbin,
                tintColor = Color(0xFFC70000),
                onClick = { /* Handle delete click */ }
            )
        }
    }
    Divider()
}

/**
 * Un breuvage
 */
@Composable
fun HydrationRowContent(drink: Drink) {
    Column {
        Text(
            text = drink.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = drink.format + ", ${drink.quantity} ml",
        )
        Text(
            text = "Prot: ${drink.protein} " +
                    "Glu: ${drink.carbohydrates} " +
                    "Fibr: ${drink.fiber} " +
                    "Gras: ${drink.fat} "
        )
    }
}

/**
 * Une transaction (consommation): breuvage + date
 */
@Composable
fun HydrationHistoryRowContent(drinkEntry: HydrationEntry) {
    Column {
        HydrationRowContent(drinkEntry.drink)
        Text(text = formatDateForHydrationEntry(drinkEntry.timestamp))

    }
}

/**
 * Formate les dates des consommations (Francais)
 */
fun formatDateForHydrationEntry(date: Date) : String {
    val pattern = "dd / MM / yyyy 'à' HH'h'00"
    val dateFormatter = SimpleDateFormat(pattern, Locale.getDefault())
    return dateFormatter.format(date)
}


@Preview(showBackground = true)
@Composable
fun DataListDisplayPreview() {
    DataListDisplay(
        title = "Historique de consommation",
        dataList = hydrationEntries.map { ListItem.HydrationEntryItem(it) },
        color = R.color.hydration
    )
}