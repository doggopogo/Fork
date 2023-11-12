package ca.etsmtl.log.fitnesshabits.ui.screens.modules.Hydration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.data.database.entities.HydrationType
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButton
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.AddHydrationViewModel
import kotlin.random.Random

@Composable
fun AddHydration(navController: NavController, viewModel: AddHydrationViewModel) {
    // Define state variables for each input field
    var name by remember { mutableStateOf("") }
    var shortName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var portionType by remember { mutableStateOf("") }
    var portionSize by remember { mutableStateOf("") }
    var usualPortionSize by remember { mutableStateOf("") }
    var protein by remember { mutableStateOf("") }
    var carb by remember { mutableStateOf("") }
    var fiber by remember { mutableStateOf("") }
    var fat by remember { mutableStateOf("") }

    var moduleColor = R.color.hydration

    Column {
        Header(
            title = "Ajouter un breuvage",
            R.color.hydration,
            navigateBack = { navController.navigateUp() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {

            CustomTextField(
                text = name,
                onTextChange = { name = it },
                label = "Nom du breuvage",
                color = moduleColor,
                isTitle = true
            )
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                CustomTextField(
                    text = shortName,
                    onTextChange = { shortName = it },
                    label = "Diminutif",
                    color = moduleColor,
                    optional = true
                )
                CustomTextField(
                    text = description,
                    onTextChange = { description = it },
                    label = "Description",
                    color = moduleColor,
                    optional = true
                )

                Column(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    CustomTitle(text = "Portion")

                    CustomTextField(
                        text = portionType,
                        onTextChange = { portionType = it },
                        label = "Type de portion",
                        color = moduleColor
                    )

                    CustomTextField(
                        text = portionSize,
                        onTextChange = { portionSize = it },
                        label = "Quantité de la portion",
                        color = moduleColor,
                        numbersOnly = true
                    )

                    CustomTextField(
                        text = usualPortionSize,
                        onTextChange = { usualPortionSize = it },
                        label = "Quantité normale de portion ",
                        color = moduleColor,
                        numbersOnly = true
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CustomTitle(text = "Macronutriments")
                        CustomTitle(text = "(optionel)", isItalic = true, fontSize = 14.sp)
                    }
                    CustomTextField(
                        text = fat,
                        onTextChange = { fat = it },
                        label = "Lipides",
                        numbersOnly = true,
                        color = moduleColor
                    )
                    CustomTextField(
                        text = carb,
                        onTextChange = { carb = it },
                        label = "Glucides",
                        numbersOnly = true,
                        color = moduleColor
                    )
                    CustomTextField(
                        text = fiber,
                        onTextChange = { fiber = it },
                        label = "Fibres",
                        numbersOnly = true,
                        color = moduleColor
                    )
                    CustomTextField(
                        text = protein,
                        onTextChange = { protein = it },
                        label = "Protéines",
                        numbersOnly = true,
                        color = moduleColor
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ActionButton(
                        text = "Ajouter",
                        onClick = {
                            // Create a HydrationType object with the collected data
                            // Assuming that you have some logic to generate a unique ID for each entry
                            val hydrationType = HydrationType(
                                id = generateUniqueId(),
                                name = name,
                                shortName = if (shortName.isNotBlank()) shortName else null,
                                description = if (description.isNotBlank()) description else null,
                                portionType = portionType,
                                portionSize = portionSize.toIntOrNull() ?: 0,
                                usualPortionSize = usualPortionSize.toIntOrNull() ?: 0,
                                protein = protein.toFloatOrNull(),
                                carb = carb.toFloatOrNull(),
                                fiber = fiber.toFloatOrNull(),
                                fat = fat.toFloatOrNull()
                            )

                            // Use the viewModel to insert the new hydration entry
                            viewModel.insertHydrationEntry(hydrationType)

                            // navigate back
                            navController.navigateUp()
                        },
                    )
                }
            }
        }
    }
}

// Helper function to generate a unique ID for each hydration entry
// This is a placeholder, you should implement this according to your app's requirements
fun generateUniqueId(): Int {
    return Random.nextInt(0, Int.MAX_VALUE)
}

@Composable
fun CustomTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String? = null,
    label: String,
    isTitle: Boolean = false,
    color: Int,
    numbersOnly: Boolean = false,
    optional: Boolean = false
) {
    TextField(
        modifier = Modifier
            .then(
                if (isTitle) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier
                }
            ),
        value = text,
        onValueChange = onTextChange,
        textStyle = TextStyle(
            fontSize =
            if (isTitle) {
                20.sp
            } else {
                14.sp
            }
        ),
        label = {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(label, fontSize = 14.sp)
                if (optional) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("(optionel)", fontSize = 12.sp, fontStyle = FontStyle.Italic)
                }
            }
        },
        placeholder = {
            if (placeholder != null) {
                Text(placeholder, fontSize = 14.sp)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = colorResource(color),
            focusedIndicatorColor = colorResource(color),
            unfocusedIndicatorColor = colorResource(color).copy(alpha = 0.5f),
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black.copy(alpha = 0.5f)
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType =
            if (numbersOnly) {
                KeyboardType.Number
            } else {
                KeyboardType.Text
            }
        ),
        maxLines = 1
    )
}

@Composable
fun CustomTitle(
    text: String,
    fontSize: TextUnit = 16.sp,
    isItalic: Boolean = false
) {
    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp, start = 4.dp, end = 4.dp),
        text = text,
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        fontStyle = if (isItalic) {
            FontStyle.Italic
        } else {
            FontStyle.Normal
        },
        color = Color.Black.copy(alpha = 0.6f)
    )
}