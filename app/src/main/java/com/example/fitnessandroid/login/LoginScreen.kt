package com.example.fitnessandroid.login

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.MainActivity
import com.example.fitnessandroid.R
import com.example.fitnessandroid.Utilities.isInvalidJSON
import com.example.fitnessandroid.api.restrequest.user.APIUser
import com.example.fitnessandroid.composable.SocialButtonFacebook
import com.example.fitnessandroid.composable.SocialButtonGoogle
import com.example.fitnessandroid.homescreen.DialogWithCustomContent
import com.example.fitnessandroid.homescreen.PasswordForgetPopUpContent
import com.example.fitnessandroid.registration.RegistrationActivity
import com.example.fitnessandroid.ui.theme.FitnessAndroidTheme
import com.example.fitnessandroid.ui.theme.appGreen
import com.example.fitnessandroid.ui.theme.appText


@Composable
fun LoginScreen(context : Context?) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF56C4AD), Color(0xFF1E6BB7))
                )
            )
    ) {
        Image(
            painter = painterResource(R.drawable.logo_legion_keh_2x),
            contentDescription = "Image description",
            modifier = Modifier.size(200.dp)
        )
        Text(
            "FitnessHabits",
            style = MaterialTheme.typography.subtitle1,
            color = Color.White,
        )

        Spacer(modifier = Modifier.size(10.dp))

        Text(
            "Se connecter",
            color = Color.White
        )

        Spacer(modifier = Modifier.size(20.dp))

        var username by remember { mutableStateOf("") }
        TextField(
            value = username,
            onValueChange = {username = it},
            placeholder = {
                Text(
                    text = "Nom d'utilisateur",
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.h1,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                textColor = Color.White
            )
        )
        Spacer(modifier = Modifier.size(10.dp))

        var password by remember { mutableStateOf("") }
        var passwordvisibility by remember { mutableStateOf(false) }
        var isTextFieldActive by remember { mutableStateOf(false) }

        val icon = if (passwordvisibility)
            painterResource(id = com.google.android.material.R.drawable.design_ic_visibility)
        else
            painterResource(id = com.google.android.material.R.drawable.design_ic_visibility_off)

        TextField(
            value = password,
            onValueChange = {
                password = it
            },

            placeholder = {
                Text(
                    text = "Mot de passe",
                    style = MaterialTheme.typography.h1,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.h1,
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White,
                unfocusedIndicatorColor = Color.White,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                trailingIconColor = Color.White,
                textColor = Color.White
            ),

            trailingIcon = {

                if (isTextFieldActive) {

                    IconButton(onClick = {
                        passwordvisibility = !passwordvisibility
                    }) {
                        androidx.compose.material.Icon(
                            painter = icon,
                            contentDescription = "visibility Icon"
                        )
                    }

                }

            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordvisibility)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
            modifier = Modifier.onFocusChanged { isTextFieldActive = it.isFocused }
        )
        Spacer(modifier = Modifier.size(15.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp)
        ){

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End,
            ) {

                var isDialogVisible by remember { mutableStateOf(false) }

                TextButton(onClick = { isDialogVisible = true }) {
                    Text(
                        text = "Mot de passe oublié ?",
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                    )
                }

                DialogWithCustomContent(
                    showDialog = isDialogVisible,
                    dismissOnClickOutside = true,
                    dismissOnBackPress = true,
                    onShowDialogChange = { isDialogVisible = it }
                ) {onClose ->
                    PasswordForgetPopUpContent (onClose)
                }
            }

        }

        Spacer(modifier = Modifier.size(15.dp))
        LoginOrRegisterButtons(context, username, password)
        Spacer(modifier = Modifier.size(8.dp))

        Spacer(modifier = Modifier.size(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){

            SocialButtonFacebook(buttonTextPrefix = "Facebook") {}
            SocialButtonGoogle(buttonTextPrefix = "Google") {}

        }


    }

}

@Composable
fun LoginOrRegisterButtons(context : Context?, username: String, password: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        val isButtonEnabled = username.isNotBlank() && password.isNotBlank()

        HomepageConnectionButton(
            text = "Se connecter",
            onClick = { loginOnClick(context, username, password) },
            enabled = isButtonEnabled
        )
        HomepageButton(
            text = "S'inscrire",
            onClick = { registerOnClick(context) },
            modifier = Modifier
                .padding(start = 5.dp, end = 20.dp),
            buttonColors = ButtonDefaults.buttonColors(Color.White),
            textColor = appText,
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
    textColor: Color,
    enabled :Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = buttonColors,
        enabled = enabled,
        shape = RoundedCornerShape(50.dp)
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}

@Composable
fun HomepageConnectionButton(
    text: String,
    onClick: () -> Unit,
    enabled :Boolean
) {

    if (!enabled) {

        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(start = 20.dp, end = 5.dp)
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(50.dp)
                )
                .height(35.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = Color.Transparent,
                disabledContentColor = Color.White,
            ),
            elevation = ButtonDefaults.elevation(0.dp,0.dp),
            enabled = false

        ) {
            Text(
                text = text
            )
        }

    } else {

        Button(
            onClick = onClick,
            modifier = Modifier
                .padding(start = 20.dp, end = 5.dp)
                .height(35.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = appGreen,
                contentColor = Color.White,
            ),
            enabled = true

        ) {
            Text(
                text = text
            )
        }

    }

}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    FitnessAndroidTheme() {
        LoginScreen(null)
    }
}