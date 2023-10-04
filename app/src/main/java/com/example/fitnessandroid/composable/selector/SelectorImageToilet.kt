package com.example.fitnessandroid.composable.selector

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessandroid.R
import com.example.fitnessandroid.common.PreviewComposable

@Composable
fun SelectorImageToilet(
    modifier: Modifier = Modifier,
    title : List<String> = emptyList(),
    options : List<Pair<String, Int?>>,
    optionSelected : String,
    setOptionSelected : (String) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth(),
            columns = GridCells.Fixed(title.size),
            userScrollEnabled = false
        ) {
            items(title) {
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = it,
                    color = Color.Black,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
            items(options) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = it.first,
                        color = Color.Black
                    )
                    if (it.first.isNotBlank()) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(120.dp)
                                .background(if (it.first == optionSelected) Color.LightGray else Color.White)
                                .clickable {
                                    setOptionSelected(it.first)
                                }
                        ) {
                            it.second?.let { image ->
                                Image(
                                    modifier = Modifier
                                        .size(120.dp),
                                    painter = painterResource(id = image),
                                    contentDescription = null
                                )
                            }
                        }
                    } else {
                        Spacer(Modifier.size(120.dp).background(Color.White))
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SelectorButtonPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        val title = listOf(
            "Constipation",
            "Selles idéales",
            "Vers la diarrhée"
        )
        val list = listOf(
            Pair("Type 1", R.drawable.bristol1),
            Pair("Type 3", R.drawable.bristol3),
            Pair("Type 5", R.drawable.bristol5),
            Pair("Type 2", R.drawable.bristol2),
            Pair("Type 4", R.drawable.bristol4),
            Pair("Type 6", R.drawable.bristol6),
            Pair("", null),
            Pair("Aucun", null),
            Pair("Type 7", R.drawable.bristol7))
        val (optionSelected, setOptionSelected) = remember { mutableStateOf("Aucun") }
        SelectorImageToilet(
            modifier = it,
            title = title,
            options  = list,
            optionSelected = optionSelected,
            setOptionSelected = setOptionSelected
        )
    }
}