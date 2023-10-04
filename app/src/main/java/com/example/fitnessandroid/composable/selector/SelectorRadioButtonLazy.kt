package com.example.fitnessandroid.composable.selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.R
import com.example.fitnessandroid.common.PreviewComposable

@Composable
fun SelectorRadioButtonLazy(
    modifier: Modifier = Modifier,
    options : List<String>,
    optionSelected : String,
    setOptionSelected : (String) -> Unit,
    color: Color = Color.Black
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        columns = GridCells.Fixed(3)
    ) {
        items(options) {
            Row() {
                Text(
                    modifier = Modifier.padding(vertical = 15.dp),
                    text = it,
                    color = color)
                RadioButton(
                    selected = optionSelected == it,
                    onClick = { setOptionSelected(it) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0xFF51A489),
                        unselectedColor = Color.Black
                    )
                )
                Spacer(Modifier.width(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectorRadioButtonPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        val list = listOf("Reposé","Heureux","Fatigué","En colère")
        val (optionSelected, setOptionSelected) = remember { mutableStateOf(list.first()) }
        SelectorRadioButtonLazy(
            modifier = it,
            options  = listOf("test", "test1", "test2"),
            optionSelected = optionSelected,
            setOptionSelected = setOptionSelected
        )
    }
}