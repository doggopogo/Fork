package com.example.fitnessandroid.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.R
import com.example.fitnessandroid.common.PreviewComposable

@Composable
private fun SocialButton(
    modifier : Modifier,
    buttonText: String,
    buttonImage: Int,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
                .weight(1f)
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(buttonImage),
                    contentDescription = "Social media icon",
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.CenterStart)
                )
                Text(
                    text = buttonText.uppercase(),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun SocialButtonFacebook(
    modifier : Modifier = Modifier,
    buttonTextPrefix: String = "",
    onClick: () -> Unit
){
    SocialButton(
        modifier = modifier,
        buttonText = buttonTextPrefix + "Facebook",
        buttonImage = R.drawable.facebook,
        onClick = onClick
    )
}

@Composable
fun SocialButtonGoogle(
    modifier : Modifier = Modifier,
    buttonTextPrefix: String = "",
    onClick: () -> Unit
){
    SocialButton(
        modifier = modifier,
        buttonText = buttonTextPrefix + "Google",
        buttonImage = R.drawable.google,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
private fun SocialButtonFacebookPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        SocialButtonFacebook(it, buttonTextPrefix = "Se connecter avec ") {}
    }
}

@Preview(showBackground = true)
@Composable
private fun SocialButtonGooglePreview() {
    PreviewComposable(backgroundColor = Color.Black) {
        SocialButtonGoogle(it) {}
    }
}