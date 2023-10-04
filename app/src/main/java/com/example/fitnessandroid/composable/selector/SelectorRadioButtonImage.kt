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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessandroid.R
import com.example.fitnessandroid.common.PreviewComposable

@Composable
fun SelectorRadioButtonImage(
    modifier: Modifier = Modifier,
    options : List<Pair<String, Int?>>,
    optionSelected : String,
    setOptionSelected : (String) -> Unit,
    color: Color = Color.Black
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
      items(options) {
          Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center
          ) {
              it.second?.let {image ->
                  Image(
                      modifier = Modifier
                          .size(75.dp)
                          .clip(CircleShape)
                          .padding(horizontal = 10.dp),
                      painter =  painterResource(id = image) ,
                      contentDescription = null
                  )
              } ?: Spacer(Modifier.padding(start = 60.dp))
              Text(
                  modifier = Modifier.padding(horizontal = 2.dp),
                  text = it.first,
                  color = color,
                  fontSize = 12.sp
              )
              RadioButton(
                  selected = optionSelected == it.first,
                  onClick = { setOptionSelected(it.first) },
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
        val list = listOf(
            Pair("Reposé", R.drawable.emoticone_happy),
            Pair("Heureux", R.drawable.emoticone_very_happy),
            Pair("Fatigué", R.drawable.emoticone_disappointed),
            Pair("En colère", R.drawable.emoticone_angry))
        val (optionSelected, setOptionSelected) = remember { mutableStateOf(list.first().first) }
        SelectorRadioButtonImage(
            modifier = it,
            options  = list,
            optionSelected = optionSelected,
            setOptionSelected = setOptionSelected
        )
    }
}