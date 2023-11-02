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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.RoundButton
import java.util.Date

@Composable
fun DataListDisplay(title: String, onClick: () -> Unit) {
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

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Prot: 0.0g")
            Text(text = "Glu: 0.0g")
            Text(text = "Fibr: 0.0g")
            Text(text = "Gras: 0.0g")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Divider()

        RowContent()

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF1F1F1))
            ) {
                Text(
                    text = "Tout afficher",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
fun RowContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
    ) {
        item {
            RowEntry()
            RowEntry()
            RowEntry()
        }
    }
}

@Composable
fun RowEntry() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        HydrationRowContent("Perrier", 250, Date(23, 11, 1))
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
fun HydrationRowContent(type: String, volume: Int, date: Date) {
    Column {
        Text(
            text = type,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Quantité bue : $volume ml")
        Text(text = date.toString())
    }
}
