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
import ca.etsmtl.log.fitnesshabits.ui.SampleData
import ca.etsmtl.log.fitnesshabits.ui.components.RoundButton

@Composable
fun DataListDisplay(
    title: String,
    drinksList: List<Drink> = SampleData.drinks, // Default for now
    color: Int,
    onClick: () -> Unit
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
        RowContent(drinksList)
    }
}

@Composable
fun RowContent(drinksList: List<Drink>) {
    // Sort the list by the 'name' attribute
    val sortedDrinks = drinksList.sortedBy { it.name }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.80f),
    ) {
        items(sortedDrinks) { drink ->
            RowEntry(drink)
        }
    }
}

@Composable
fun RowEntry(drink: Drink) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HydrationRowContent(drink)
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

@Preview(showBackground = true)
@Composable
fun DataListDisplayPreview() {
    DataListDisplay(title = "Liste des brevages", color = R.color.hydration) {}
}