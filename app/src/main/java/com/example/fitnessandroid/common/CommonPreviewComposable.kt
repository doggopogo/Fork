package com.example.fitnessandroid.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.ui.theme.FitnessAndroidTheme

@Composable
fun PreviewComposable(
    backgroundColor: Int,
    content: @Composable (Modifier) -> Unit
) {
    BasePreviewComposable(backgroundColor = colorResource(id = backgroundColor), content)
}


@Composable
fun PreviewComposable(
    backgroundColor: Color,
    content: @Composable (Modifier) -> Unit
) {
    BasePreviewComposable(backgroundColor, content)
}

@Composable
private fun BasePreviewComposable(
    backgroundColor: Color,
    content: @Composable (Modifier) -> Unit
) {
     FitnessAndroidTheme {
        Surface(
            color = backgroundColor
        ) {
            content(Modifier.padding(16.dp))
        }
    }
}