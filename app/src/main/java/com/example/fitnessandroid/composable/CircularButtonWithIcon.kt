package com.example.fitnessandroid.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CircularButtonWithIcon(
    action: () -> Unit,
    resource : Int,
    contentDescription: String
) {
    OutlinedButton(
        modifier = Modifier.size(50.dp),
        onClick = action,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(Color.White)
    ) {
        Image(
            painter = painterResource(resource),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
    }
}