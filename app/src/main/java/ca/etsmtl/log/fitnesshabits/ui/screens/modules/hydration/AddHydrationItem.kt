package ca.etsmtl.log.fitnesshabits.ui.screens.modules.hydration

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButton
import ca.etsmtl.log.fitnesshabits.ui.components.modules.ActionButtonStyle
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.hydration.AddHydrationItemViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.filled.Check

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import ca.etsmtl.log.fitnesshabits.data.database.entities.nutrition.Type

@Composable
fun AddHydrationItem(navController: NavController) {
    val addHydrationItemViewModel: AddHydrationItemViewModel = hiltViewModel()

    val moduleColor = R.color.hydration

    var name by remember { mutableStateOf("Hot Chocolate") }
    var description by remember { mutableStateOf("Sweet sweet chocolate goodness") }

    var servingSize by remember { mutableStateOf("1") }
    var servingAmount by remember { mutableStateOf("300") }

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier.clickable(
            onClick = { focusManager.clearFocus() },
            indication = null,
            interactionSource = interactionSource
        )
    ) {
        Header(
            title = "Ajouter un breuvage",
            R.color.hydration,
            navigateBack = { navController.navigateUp() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // region Item
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 32.dp, bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderTextField(
                    value = name,
                    onValueChange = { name = it },
                    fontSize = 36.sp,
                    isBold = true
                )
                HeaderTextField(
                    value = description,
                    onValueChange = { description = it },
                    fontSize = 22.sp,
                )
                CustomDropdownImageMenu(moduleColor)
            }
            // endregion

            Divider()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // region Serving
                ItemSection(name = "Serving", color = moduleColor, isExpandedOnLoad = true) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        NumberTextField(
                            value = servingSize,
                            onValueChange = { servingSize = it },
                            fontSize = 18.sp,
                            maxWidth = 40.dp,
                            maxChar = 2,
                            isCentered = true,
                            withBackground = true
                        )
                        CustomDropdownMenu(selectedColor = moduleColor)
                        Text(
                            text = " : ",
                            fontSize = 18.sp
                        )
                        NumberTextField(
                            value = servingAmount,
                            onValueChange = { servingAmount = it },
                            fontSize = 18.sp,
                            maxWidth = 50.dp,
                            maxChar = 3,
                            isCentered = true,
                            withBackground = true
                        )
                        CustomDropdownMenu(
                            width = 60.dp,
                            maxChar = 3,
                            selectedColor = moduleColor,
                            defaultSelection = 0
                        )
                    }
                }
                // endregion

                Divider()

                // region Macronutrient
                ItemSection(name = "Macronutrient", color = moduleColor) {
                    NutrientRow(
                        nutrientName = "Calories",
                        amount = 320,
                        fontSize = 24.sp,
                        isBold = true
                    )
                    NutrientRow(
                        nutrientName = "Total fat",
                        amount = 9,
                        unit = "g",
                        fontSize = 20.sp
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        NutrientRow(
                            nutrientName = "Saturated fat",
                            amount = 1,
                            unit = "g",
                            fontSize = 16.sp
                        )
                        NutrientRow(
                            nutrientName = "Trans fat",
                            amount = 1,
                            unit = "g",
                            fontSize = 16.sp
                        )
                        NutrientRow(
                            nutrientName = "Polyunsaturated fat",
                            amount = 0,
                            unit = "g",
                            fontSize = 16.sp
                        )
                        NutrientRow(
                            nutrientName = "Monounsaturated fat",
                            amount = 0,
                            unit = "g",
                            fontSize = 16.sp
                        )
                    }

                    NutrientRow(
                        nutrientName = "Total carbohydrate",
                        amount = 35,
                        unit = "g",
                        fontSize = 20.sp
                    )
                    Column(modifier = Modifier.padding(start = 16.dp)) {
                        NutrientRow(
                            nutrientName = "Dietary fibre",
                            amount = 1,
                            unit = "g",
                            fontSize = 16.sp
                        )
                        NutrientRow(
                            nutrientName = "Total sugars",
                            amount = 1,
                            unit = "g",
                            fontSize = 16.sp
                        )
                        Column(modifier = Modifier.padding(start = 16.dp)) {
                            NutrientRow(
                                nutrientName = "Added sugars",
                                amount = 0,
                                unit = "g",
                                fontSize = 14.sp
                            )
                        }
                    }

                    NutrientRow(
                        nutrientName = "Protein",
                        amount = 30,
                        unit = "g",
                        fontSize = 20.sp
                    )

                    Divider(modifier = Modifier.padding(12.dp))

                    NutrientRow(nutrientName = "Sodium", amount = 0, unit = "mg")
                    NutrientRow(nutrientName = "Cholesterol", amount = 400, unit = "mg")
                }
                // endregion

                Divider()

                // region Micronutrient
                ItemSection(name = "Micronutrient", color = moduleColor) {
                    NutrientRow(nutrientName = "Vitamin A", amount = 220, unit = "mcg")
                    NutrientRow(nutrientName = "Calcium", amount = 20, unit = "mcg")
                    NutrientRow(nutrientName = "Potassium", amount = 5, unit = "mg")
                    SelectableNutrientRow(
                        nutrientName = "Vitamin A",
                        amount = 220,
                        selectedColor = moduleColor
                    )
                    AddNewNutrientButton()
                }
                // endregion

                Divider()

                // region Bioactive compound
                ItemSection(name = "Bioactive Compound", color = moduleColor) {
                    NutrientRow(nutrientName = "Caffeine", amount = 200, unit = "mcg")
                    NutrientRow(nutrientName = "Taurine", amount = 25, unit = "mcg")
                    NutrientRow(nutrientName = "Cetirizine", amount = 10, unit = "mg")
                    SelectableNutrientRow(
                        nutrientName = "Caffeine",
                        amount = 200,
                        selectedColor = moduleColor
                    )
                    AddNewNutrientButton()
                }
                // endregion

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ActionButton(
                        text = "Ajouter",
                        fontSize = 17.sp,
                        style = ActionButtonStyle(
                            textColor = Color.White,
                            backgroundColor = colorResource(moduleColor)
                        ),
                        onClick = {
                                        val nutritionType1 = Type(
                                            id = 1,
                                            name = "hydration"
                                        )
                                        val nutritionType2 = Type(
                                            id = 2,
                                            name = "food"
                                        )
                                        val nutritionType3 = Type(
                                            id = 3,
                                            name = "medicationSupplement"
                                        )
                                        val nutritionType4 = Type(
                                            id = 4,
                                            name = "alcohol"
                                        )

                                        val types: List<Type> = listOf(
                                            nutritionType1,
                                            nutritionType2,
                                            nutritionType3,
                                            nutritionType4
                                        )
                            addHydrationItemViewModel.addAllNutritionItems(types)
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
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
    fontSize: TextUnit = 24.sp,
    isItalic: Boolean = false
) {
    Text(
        modifier = Modifier.padding(4.dp),
        text = text,
        fontSize = fontSize,
        fontStyle = if (isItalic) {
            FontStyle.Italic
        } else {
            FontStyle.Normal
        },
        color = colorResource(id = R.color.hydration)
    )
}

@Composable
fun ItemSection(
    name: String,
    color: Int,
    isExpandedOnLoad: Boolean = false,
    content: @Composable () -> Unit
) {
    var isExpanded by remember { mutableStateOf(isExpandedOnLoad) }

    Card(
        modifier = Modifier
            .animateContentSize(),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .clickable {
                        isExpanded = !isExpanded
                    }
            ) {
                CustomTitle(text = name)

                // Icon to indicate expand/collapse state
                Icon(
                    imageVector = if (isExpanded) {
                        Icons.Filled.KeyboardArrowUp
                    } else {
                        Icons.Filled.KeyboardArrowDown
                    },
                    contentDescription = "Expand/Collapse",
                    tint = colorResource(id = color)
                )
            }

            AnimatedVisibility(
                modifier = Modifier.padding(bottom = 24.dp),
                visible = isExpanded
            ) {
                Column {
                    content()
                }
            }
        }
    }
}

@Composable
fun NutrientRow(
    nutrientName: String,
    amount: Int,
    unit: String? = null,
    fontSize: TextUnit = 18.sp,
    isBold: Boolean = false
) {
    var editableAmount by remember { mutableStateOf(amount.toString()) }

    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = nutrientName,
            fontSize = fontSize,
            fontWeight =
            if (isBold) {
                FontWeight.Black
            } else {
                FontWeight.Normal
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberTextField(
                value = editableAmount,
                onValueChange = { editableAmount = it },
                fontSize = fontSize,
                isBold = isBold
            )
            if (unit != null) {
                Text(
                    text = " $unit",
                    fontSize = (fontSize.value - 2).sp,
                    fontWeight =
                    if (isBold) {
                        FontWeight.Black
                    } else {
                        FontWeight.Normal
                    },
                    color = Color.Black.copy(alpha = 0.6f),
                )
            }
        }
    }
}


@Composable
fun SelectableNutrientRow(
    nutrientName: String,
    amount: Int,
    unit: String? = null,
    fontSize: TextUnit = 18.sp,
    selectedColor: Int
) {
    var editableAmount by remember { mutableStateOf(amount.toString()) }

    Row(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomDropdownMenu(
            fontSize = fontSize,
            selectedColor = selectedColor
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberTextField(
                value = editableAmount,
                onValueChange = { editableAmount = it },
                fontSize = fontSize,
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomDropdownMenu(
                width = 60.dp,
                maxChar = 3,
                fontSize = (fontSize.value - 2).sp,
                selectedColor = selectedColor,
                defaultSelection = 0
            )
            /*
            if (unit != null) {
                Text(
                    text = " $unit",
                    fontSize = (fontSize.value - 2).sp,
                    fontWeight =
                    if (isBold) {
                        FontWeight.Black
                    } else {
                        FontWeight.Normal
                    },
                    color = Color.Black.copy(alpha = 0.6f),
                )
            }
             */
        }
    }
}

@Composable
fun NumberTextField(
    value: String,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit,
    isBold: Boolean = false,
    maxChar: Int = 4,
    maxWidth: Dp = 60.dp,
    withBackground: Boolean = false,
    isCentered: Boolean = false
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= maxChar) {
                onValueChange(newValue)
            }
        },
        textStyle = if (isFocused) {
            TextStyle(
                fontSize = fontSize,
                color = Color.Black,
                textAlign = if (isCentered) {
                    TextAlign.Center
                } else {
                    TextAlign.Right
                },
                fontWeight =
                if (isBold) {
                    FontWeight.Black
                } else {
                    FontWeight.Normal
                }
            )
        } else {
            TextStyle(
                fontSize = (fontSize.value - 2).sp,
                color = Color.Black.copy(alpha = 0.6f),
                textAlign = if (isCentered) {
                    TextAlign.Center
                } else {
                    TextAlign.Right
                },
                fontWeight =
                if (isBold) {
                    FontWeight.Black
                } else {
                    FontWeight.Normal
                }
            )
        },
        singleLine = true,
        modifier = if (withBackground) {
            Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .widthIn(30.dp, maxWidth)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.Black.copy(0.03f))
                .padding(6.dp)
        } else {
            Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .widthIn(30.dp, maxWidth)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
//        keyboardActions = KeyboardActions(
//            onDone = {
//                isEditing = false
//                // Optionally update the amount value here if needed
//            }
//        ),
    )
}

@Composable
fun HeaderTextField(
    value: String,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit,
    isBold: Boolean = false
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle =
        if (isFocused) {
            TextStyle(
                fontSize = fontSize,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight =
                if (isBold) {
                    FontWeight.ExtraBold
                } else {
                    FontWeight.Normal
                }
            )
        } else {
            TextStyle(
                fontSize = (fontSize.value - 2).sp,
                color = Color.Black.copy(alpha = 0.6f),
                textAlign = TextAlign.Center,
                fontWeight =
                if (isBold) {
                    FontWeight.ExtraBold
                } else {
                    FontWeight.Normal
                }
            )
        },
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
//        keyboardActions = KeyboardActions(
//            onDone = {
//                isEditing = false
//                // Optionally update the amount value here if needed
//            }
//        ),
    )
}

@Composable
fun CustomDropdownMenu(
    fontSize: TextUnit = 16.sp,
    width: Dp = 100.dp,
    maxChar: Int = 6,
    selectedColor: Int,
    defaultSelection: Int = -1
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(defaultSelection) }

    val items = listOf(
        "Option 1",
        "Option 2",
        "Option 3",
        "LALALALALAALAA",
        "Coffee mug",
        "Glass",
        "Can",
        "L",
        "mL",
        "g",
        "mg",
        "1234567890",
        "1234567890123"
    )
    var selectedItem: String
    var color: Color

    Box {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Color.Black.copy(0.03f))
                .clickable { isExpanded = true }
                .padding(6.dp)
                .width(width)
        ) {
            if (selectedIndex < 0) {
                selectedItem = "Select"
                color = Color.Black.copy(0.20f)
            } else {
                selectedItem = items[selectedIndex]
                if (selectedItem.length > maxChar) {
                    selectedItem = selectedItem.substring(0, maxChar) + ".."
                }
                color = Color.Black.copy(0.6f)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = selectedItem,
                    color = color,
                    fontSize = fontSize,
                    modifier = Modifier.padding(start = 8.dp)
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
            items.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    isExpanded = false
                    selectedIndex = index
                }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (index == selectedIndex) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                modifier = Modifier.size(20.dp),
                                tint = colorResource(id = selectedColor)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(
                            text = item,
                            color = if (index == selectedIndex) {
                                colorResource(id = selectedColor)
                            } else {
                                Color.Black
                            },
                            fontWeight = if (index == selectedIndex) {
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

@Composable
fun AddNewNutrientButton() {
    Button(
        onClick = {
            // TODO
        },
        modifier = Modifier.size(40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Black.copy(0.03f)
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            focusedElevation = 0.dp,
            hoveredElevation = 0.dp
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = "+",
            fontSize = 20.sp,
            color = Color.Black.copy(0.6f)
        )
    }
}

@Composable
fun CustomDropdownImageMenu(
    selectedColor: Int,
) {
    var isExpanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(1) }

    val items = listOf(
        "Non-hydrating",
        "Slightly hydrating",
        "Moderately hydrating",
        "Hydrating",
        "Fully hydrating"
    )
    var selectedItem: String
    var selectedImageResId: Int

    Box {
        TextButton(
            onClick = { isExpanded = true },
            contentPadding = PaddingValues(16.dp)
        ) {
            selectedItem = items[selectedIndex]
            selectedImageResId = when (selectedItem) {
                "Non-hydrating" -> R.drawable.icon_non_hydrating
                "Slightly hydrating" -> R.drawable.icon_slightly_hydrating
                "Moderately hydrating" -> R.drawable.icon_hydrating
                "Hydrating" -> R.drawable.icon_moderately_hydrating
                "Fully hydrating" -> R.drawable.icon_fully_hydrating
                else -> R.drawable.icon_non_hydrating
            }
            Row(
                modifier = Modifier.clickable { isExpanded = true }
            ) {
                Image(
                    painter = painterResource(id = selectedImageResId),
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
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded = false
                        selectedIndex = index
                    }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (index == selectedIndex) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Selected",
                                modifier = Modifier.size(20.dp),
                                tint = colorResource(id = selectedColor)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Text(
                            text = item,
                            color = if (index == selectedIndex) {
                                colorResource(id = selectedColor)
                            } else {
                                Color.Black
                            },
                            fontWeight = if (index == selectedIndex) {
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