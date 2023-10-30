package ca.etsmtl.log.fitnesshabits.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
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
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.toDelete.common.PreviewComposable
import ca.etsmtl.log.fitnesshabits.ui.theme.appText

@Composable
private fun SocialButton(
    modifier : Modifier,
    buttonText: String,
    buttonImage: Int,
    buttonColors: ButtonColors,
    textColor:Color,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = modifier
                .width(150.dp)
                .padding(start = 5.dp, end = 5.dp),
            colors = buttonColors,
            shape = RoundedCornerShape(50.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(buttonImage),
                    contentDescription = "Social media icon",
                    modifier = Modifier
                        .size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = buttonText,
                    color = textColor,
                    textAlign = TextAlign.Center
                )

            }

           /* Box(
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
                    text = buttonText,
                    color = textColor,
                    textAlign = TextAlign.Center
                )
            }*/
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
        buttonText = buttonTextPrefix,
        buttonImage = R.drawable.logo_rseau_fb,
        onClick = onClick,
        buttonColors = ButtonDefaults.buttonColors(Color(0xFF1B74E4)),
        textColor = Color.White
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
        buttonText = buttonTextPrefix,
        buttonImage = R.drawable.google,
        onClick = onClick,
        buttonColors = ButtonDefaults.buttonColors(Color.White),
        textColor = appText
    )
}

@Preview(showBackground = true)
@Composable
private fun SocialButtonFacebookPreview() {
    PreviewComposable(backgroundColor = Color.White) {
        SocialButtonFacebook(it, buttonTextPrefix = "Facebook") {}
    }
}

@Preview(showBackground = true)
@Composable
private fun SocialButtonGooglePreview() {
    PreviewComposable(backgroundColor = Color.Black) {
        SocialButtonGoogle(it, buttonTextPrefix = "Google") {}
    }
}