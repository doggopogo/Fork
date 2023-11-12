package ca.etsmtl.log.fitnesshabits.ui.screens.modulesDialogWindows

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.data.database.entities.HydrationType
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HydrationDialogWindow(hydrationTypes: List<HydrationType>, onDismiss: () -> Unit) {
    var totalDrank by remember { mutableStateOf(0.0) }
    var protein by remember { mutableStateOf(0.0) }
    var carb by remember { mutableStateOf(0.0) }
    var fiber by remember { mutableStateOf(0.0) }
    var fat by remember { mutableStateOf(0.0) }
    var selectedDate by remember { mutableStateOf("jj / mm / aaaa") }
    var selectedTime by remember { mutableStateOf("00:00") }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(32.dp)
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.85f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = "Quantité totale bue : $totalDrank L",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF609AC6) //0xFF4383A8
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Prot: ${protein}")
                    Text(text = "Glu: ${carb}")
                    Text(text = "Fibr: ${fiber}")
                    Text(text = "Gras: ${fat}")
                }

                Divider()
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(fontSize = 15.sp, text = selectedDate)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = null,
                            modifier = Modifier
                                .size(45.dp)
                                .clickable { /* Show date picker */ }
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(fontSize = 15.sp, text = selectedTime)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.clock),
                            contentDescription = null,
                            modifier = Modifier
                                .size(45.dp)
                                .clickable { /* Show time picker */ }
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                ) {
                    Text(
                        text = "Nom",
                        fontSize = 18.sp,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "Quantité",
                        fontSize = 18.sp,
                        modifier = Modifier.weight(3f)
                    )
                }
                Divider()
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    items(hydrationTypes) { hydrationType ->
                        RowContent(hydrationTypes = hydrationTypes, hydrationType = hydrationType)
                    }
                }
            }
            Divider()
            Spacer(modifier = Modifier.height(16.dp))
            ActionButton(
                text = "Sauvegarder",
                onClick = {}
            )
        }
    }
}

@Composable
private fun RowContent(hydrationTypes: List<HydrationType>, hydrationType: HydrationType) {
    // Separate state variables for each dropdown menu
    var expandedType by remember { mutableStateOf(false) }
    var expandedContainer by remember { mutableStateOf(false) }
    val containers = arrayOf("Verre", "Bouteille", "Tasse", "Canette")
    var selectedContainer by remember { mutableStateOf(containers[0]) }
    var selectedHydrationType by remember { mutableStateOf(hydrationType) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Dropdown menu for selecting HydrationType
        Box(
            modifier = Modifier
                .weight(1f)
                .border(BorderStroke(1.dp, Color.Gray))
        ) {
            DropdownMenu(
                expanded = expandedType,
                onDismissRequest = { expandedType = false },
            ) {
                hydrationTypes.forEach { type ->
                    DropdownMenuItem(onClick = {
                        selectedHydrationType = type
                        expandedType = false
                    }) {
                        Text(
                            type.name,
                            modifier = Modifier.padding(vertical = 4.dp) // Adjust vertical padding as needed
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .clickable { expandedType = true }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically // Align the icon and text vertically
            ) {
                Text(
                    text = selectedHydrationType.name,
                    modifier = Modifier.weight(1f), // Text takes up the majority of the space
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp) // Ensure the icon has a fixed size
                )
            }
        }

        // Nested Row for the "Quantité" column
        Row(
            modifier = Modifier.weight(3f), // This weight should be the sum of the weights of the nested items
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Dropdown menu for selecting Container
            Box(
                modifier = Modifier
                    .weight(1f) // Adjust the weight for the dropdown menu
                    .border(BorderStroke(1.dp, Color.Gray))
            ) {
                DropdownMenu(
                    expanded = expandedContainer,
                    onDismissRequest = { expandedContainer = false },
                ) {
                    containers.forEach { container ->
                        DropdownMenuItem(onClick = {
                            selectedContainer = container
                            expandedContainer = false
                        }) {
                            Text(container)
                        }
                    }
                }
                Row(modifier = Modifier.clickable { expandedContainer = true }) {
                    Text(
                        text = "$selectedContainer",
                        modifier = Modifier.padding(8.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            // Minus button
            TextButton(
                modifier = Modifier
                    .weight(0.5f), // Adjust the weight for the minus button
                onClick = { /*TODO*/ },
                // Button styling
            ) {
                Text(text="-", fontSize = 20.sp, color = colorResource(R.color.hydration))
            }

            // Total liquid amount
            Text(
                text = "300 ml",
                modifier = Modifier
                    .weight(1f),
                textAlign = TextAlign.Center
            )

            // Plus button
            TextButton(
                modifier = Modifier
                    .weight(0.5f), // Adjust the weight for the plus button
                onClick = { /*TODO*/ },
                // Button styling
            ) {
                Text(text="+", fontSize = 20.sp, color = colorResource(R.color.hydration))
            }
        }
    }
    Divider()
}