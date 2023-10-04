package com.example.fitnessandroid.composable

import androidx.compose.material.ContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.fitnessandroid.common.PreviewComposable

@Composable
fun TextFieldComposable(
    modifier : Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    color: Color = Color.White,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        maxLines = 1,
        onValueChange = onValueChange,
        label = { Text(text = label.uppercase(), color = color) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = color,
            focusedBorderColor = color,
            unfocusedBorderColor = color,
            backgroundColor = Color.Transparent,
            cursorColor = Color.LightGray.copy(alpha = ContentAlpha.medium)
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Preview
@Composable
private fun TextFieldPasswordComposable() {
    PreviewComposable(backgroundColor = Color.Black) {
        var password by remember { mutableStateOf("") }
        TextFieldComposable(
            modifier = it,
            value = password,
            onValueChange = { password = it },
            label = "Mot de passe",
            isPassword = true
        )
    }
}

@Preview
@Composable
private fun TextFieldNotPasswordComposable() {
    PreviewComposable(backgroundColor = Color.Black) {
        var firstName by remember { mutableStateOf("") }
        TextFieldComposable(
            modifier = it,
            value = firstName,
            onValueChange = { firstName = it },
            label = "Prenom"
        )
    }
}