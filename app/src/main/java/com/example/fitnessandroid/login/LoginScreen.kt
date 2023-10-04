package com.example.fitnessandroid.login

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessandroid.MainActivity
import com.example.fitnessandroid.R
import com.example.fitnessandroid.Utilities.isInvalidJSON
import com.example.fitnessandroid.api.restrequest.user.APIUser
import com.example.fitnessandroid.composable.SocialButtonFacebook
import com.example.fitnessandroid.composable.SocialButtonGoogle
import com.example.fitnessandroid.composable.TextFieldComposable
import com.example.fitnessandroid.registration.RegistrationActivity

@Composable
fun LoginScreen(context : Context?) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF47AAAF), Color(0xFF1E6BB7))
                )
            )
    ) {
        Image(
            painter = painterResource(R.drawable.legion),
            contentDescription = "Image description",
            modifier = Modifier.size(250.dp)
        )
        Text(
            "FitnessHabits",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(20.dp))

        var username by remember { mutableStateOf("") }
        TextFieldComposable(
            value = username,
            onValueChange = { username = it },
            label = "Nom d'utilisateur"
        )

        var password by remember { mutableStateOf("") }
        TextFieldComposable(
            value = password,
            onValueChange = { password = it },
            label = "Mot de passe",
            isPassword = true
        )

        TextButton(onClick = { }) {
            Text(
                text = "Mot de passe oublié?",
                fontSize = 15.sp,
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        LoginOrRegisterButtons(context, username, password)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            "Ou".uppercase(),
            fontSize = 15.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(8.dp))
        SocialButtonGoogle(buttonTextPrefix = "Se connecter avec ") {}
        SocialButtonFacebook(buttonTextPrefix = "Se connecter avec ") {}
    }
}

@Composable
fun LoginOrRegisterButtons(context : Context?, username: String, password: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomepageButton(
            text = "Se connecter",
            onClick = { loginOnClick(context, username, password) },
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp, end = 5.dp),
            buttonColors = ButtonDefaults.buttonColors(Color.White),
            textColor = Color.Black
        )
        HomepageButton(
            text = "S'inscrire",
            onClick = { registerOnClick(context) },
            modifier = Modifier
                .weight(1f)
                .padding(start = 5.dp, end = 20.dp),
            buttonColors = ButtonDefaults.buttonColors(Color.White),
            textColor = Color.Black
        )
    }
}

fun loginOnClick(context : Context?, username: String, password: String) {
    val json = hashMapOf(Pair("username", username), Pair("password", password))
    if(json.isInvalidJSON()) {
        MainActivity.showErrorMessage("Les champs sont vides")
    } else {
        context?.let {
            APIUser.login(it,json)
        }
    }
}

fun registerOnClick(context : Context?) {
    val navigate = Intent(context, RegistrationActivity::class.java)
    context?.startActivity(navigate)
}

@Composable
fun HomepageButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    textColor: Color = Color.Black,
    enabled :Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = buttonColors,
        enabled = enabled
    ) {
        Text(
            text = text.uppercase(),
            color = textColor
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(null)
}