package ca.etsmtl.log.fitnesshabits.ui.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessAndroidTheme
import ca.etsmtl.log.fitnesshabits.ui.theme.appDefaultGray
import ca.etsmtl.log.fitnesshabits.ui.theme.appGreen
import ca.etsmtl.log.fitnesshabits.ui.theme.appName
import ca.etsmtl.log.fitnesshabits.ui.theme.appText

@Composable
fun PasswordForgetPopUpContent(onClose: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(20.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.logolegion_gris),
                contentDescription = "Image description",
            )

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = appName,
                style = MaterialTheme.typography.h1,
                color = appText
            )

        }

        Spacer(modifier = Modifier.size(20.dp))

        var recuperationMail by remember { mutableStateOf("") }
        TextField(
            value = recuperationMail,
            onValueChange = {recuperationMail = it},
            placeholder = {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Normal,
                    color = appText
                )
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.h1,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = appGreen,
                unfocusedIndicatorColor = appDefaultGray,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = appGreen,
                textColor = appText
            )
        )

        Spacer(modifier = Modifier.size(15.dp))

        Text(
            text = "Entrer votre mail de récupération. " +
                    "Nous vous enverrons un code afin que vous " +
                    "pussiez changer votre mot de passe.",
            style = MaterialTheme.typography.body1.copy(
                textAlign = TextAlign.Center
            )
        )

        var showAlert by remember { mutableStateOf(false) }
        ShowCustomAlert(
            title = "Email Invalide" ,
            message = "Veuillez saisir un email valide" ,
            showDialog = showAlert,
            onDismiss = { showAlert = false}
        )

        Spacer(modifier = Modifier.size(15.dp))

        Button(
            onClick = {

                if (!isValidEmail(recuperationMail))
                    showAlert = true
                else
                    onClose()
            },
            shape = RoundedCornerShape(50.dp),
            enabled = recuperationMail.isNotBlank()
        ) {
            Text(text = "Envoyer")
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)\$".toRegex()
    return email.matches(emailRegex)
}

@Preview(showBackground = false)
@Composable
fun PreviewPasswordForgetPopupContent() {

    FitnessAndroidTheme() {
        PasswordForgetPopUpContent {
            var isPopupVisible = true
        }
    }

}