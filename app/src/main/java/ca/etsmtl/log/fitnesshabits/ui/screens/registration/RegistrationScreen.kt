package ca.etsmtl.log.fitnesshabits.ui.screens.registration

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.helper.Utilities.isInvalidJSON
import ca.etsmtl.log.fitnesshabits.toDelete.api.restrequest.user.APIUser
import ca.etsmtl.log.fitnesshabits.ui.views.selector.SelectorButton
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.ShowCustomAlert
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.isValidEmail
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessAndroidTheme
import ca.etsmtl.log.fitnesshabits.ui.theme.white
import ca.etsmtl.log.fitnesshabits.ui.views.DateComposable
import ca.etsmtl.log.fitnesshabits.ui.views.EntryWithSelector
import com.google.android.material.R
import java.text.SimpleDateFormat
import kotlin.collections.HashMap

@Composable
fun RegistrationScreen(context : Context?) {

    //val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF47AAAF), Color(0xFF1E6BB7))
                )
            )
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                //.padding(16.dp)
                //.verticalScroll(scrollState)
        ) {

            Spacer(modifier = Modifier.size(8.dp))

            var firstName by remember { mutableStateOf("") }
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                placeholder = {
                    Text(
                        text = "Prénom",
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Normal,
                        color = white
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

            var lastName by remember { mutableStateOf("") }
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                placeholder = {
                    Text(
                        text = "Nom",
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Normal,
                        color = white
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

            var email by remember { mutableStateOf("") }
            TextField(
                value = email,
                onValueChange = { email = it },
                placeholder = {
                    Text(
                        text = "Email",
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Normal,
                        color = white
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

            var username by remember { mutableStateOf("") }
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(
                        text = "Nom d'utilisateur",
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Normal,
                        color = white
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

            var password by remember { mutableStateOf("") }

            var passwordvisibility by remember { mutableStateOf(false) }
            var isTextFieldActive by remember { mutableStateOf(false) }

            val icon = if (passwordvisibility)
                painterResource(id = R.drawable.design_ic_visibility)
            else
                painterResource(id = R.drawable.design_ic_visibility_off)

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
                        color = white
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
                            Icon(
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

            var confirmPassword by remember { mutableStateOf("") }

            var passwordconfirmvisibility by remember { mutableStateOf(false) }
            var isConfirmTextFieldActive by remember { mutableStateOf(false) }

            val iconConfirm = if (passwordconfirmvisibility)
                painterResource(id = R.drawable.design_ic_visibility)
            else
                painterResource(id = R.drawable.design_ic_visibility_off)

            TextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                },

                placeholder = {
                    Text(
                        text = "Confirmez mot de passe",
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Normal,
                        color = white
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

                    if (isConfirmTextFieldActive) {

                        IconButton(onClick = {
                            passwordconfirmvisibility = !passwordconfirmvisibility
                        }) {
                            Icon(
                                painter = icon,
                                contentDescription = "visibility Icon"
                            )
                        }

                    }

                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwordconfirmvisibility)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
                modifier = Modifier.onFocusChanged { isConfirmTextFieldActive = it.isFocused }
            )

            Spacer(modifier = Modifier.size(15.dp))

            val genderOptions = FitHabData.fitHabConst.SexEnum
            var gender by remember { mutableStateOf(genderOptions.first()) }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                SelectorButton(options = genderOptions, cells = 3) { selectedGender ->
                    gender = selectedGender
                }
            }

            Spacer(modifier = Modifier.size(15.dp))

            val selectedBirthday = remember { mutableStateOf("") }
            DateComposable(labelName = "Anniversaire", context = LocalContext.current) { date ->
                selectedBirthday.value = date
            }

            Spacer(modifier = Modifier.size(25.dp))

            val (weight, setWeight) = remember { mutableStateOf("") }
            val weightOptions = FitHabData.fitHabConst.WeightEnum
            val (optionWeightSelected, setOptionWeightSelected) = remember {
                mutableStateOf(
                    weightOptions.first()
                )
            }

            val (height, setHeight) = remember { mutableStateOf("") }
            val heightOptions = FitHabData.fitHabConst.HeightEnum
            val (optionHeightSelected, setOptionHeightSelected) = remember {
                mutableStateOf(
                    heightOptions.first()
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 50.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                ) {

                    EntryWithSelector(
                        modifier = Modifier,
                        textFieldLabel = "Poids",
                        textValue = weight,
                        onTextValueChange = setWeight,
                        options = weightOptions,
                        optionSelected = optionWeightSelected,
                        setOptionSelected = setOptionWeightSelected
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    EntryWithSelector(
                        modifier = Modifier,
                        textFieldLabel = "Taille",
                        textValue = height,
                        onTextValueChange = setHeight,
                        options = heightOptions,
                        optionSelected = optionHeightSelected,
                        setOptionSelected = setOptionHeightSelected
                    )

                }

            }

            Spacer(modifier = Modifier.size(15.dp))

            val liquidOptions = FitHabData.fitHabConst.LiquidEnum
            var liquid by remember { mutableStateOf(liquidOptions.first()) }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                Text(
                    text = "Liquides en :",
                    style = MaterialTheme.typography.h1,
                    color = white,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.size(3.dp))
                SelectorButton(options = liquidOptions, cells = 2) { selectedLiquid ->
                    liquid = selectedLiquid
                }
            }

            Spacer(modifier = Modifier.size(15.dp))

            var isButtonEnabled by remember { mutableStateOf(false) }

            SideEffect {
                isButtonEnabled = firstName.isNotBlank() &&
                        lastName.isNotBlank() &&
                        email.isNotBlank() &&
                        username.isNotBlank() &&
                        password.isNotBlank() &&
                        confirmPassword.isNotBlank() &&
                        gender.isNotBlank() &&
                        selectedBirthday.value.isNotBlank() &&
                        height.isNotBlank() &&
                        weight.isNotBlank()
            }

            var showAlert1 by remember { mutableStateOf(false) }
            var showAlert2 by remember { mutableStateOf(false) }
            var showAlert3 by remember { mutableStateOf(false) }

            ShowCustomAlert(
                title = "Mot de passe non sécurisé" ,
                message = "Votre mot de passe doit contenir : \n\n- huit (08) caractères au minimum," +
                        "\n- un (01) caractère spéciale, \n- une (01) lettre majuscule," +
                        "\n- une (01) lettre minuscule,\n- un (01) chiffre" ,
                showDialog = showAlert1,
                onDismiss = { showAlert1 = false}
            )

            ShowCustomAlert(
                title = "Mot de passe non conforme" ,
                message = "Veuillez confirmer à nouveau votre mot de passe" ,
                showDialog = showAlert2,
                onDismiss = { showAlert2 = false}
            )

            ShowCustomAlert(
                title = "Email Invalide" ,
                message = "Veuillez saisir un mail valide" ,
                showDialog = showAlert3,
                onDismiss = { showAlert3 = false}
            )

            Button(
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier.width(150.dp),
                enabled = isButtonEnabled,
                onClick = {

                    if(!isValidEmail(email)){

                        showAlert3 = true

                    }else{

                        if(!isSecurePassword(password)){
                            showAlert1 = true
                        }else{
                            if (password != confirmPassword){
                                showAlert2 = true
                            }else{

                                val json = hashMapOf(
                                    Pair("email", email),
                                    Pair("firstName", firstName),
                                    Pair("lastName", lastName),
                                    Pair("username", username),
                                    Pair("password", password),
                                    Pair("sex", gender),
                                    Pair(
                                        "birthday",
                                        SimpleDateFormat("dd/MM/yyyy").parse(selectedBirthday.value).toString()
                                    ),
                                    Pair("size", height),
                                    Pair("sizeType", optionHeightSelected),
                                    Pair("weight", weight),
                                    Pair("weightType", optionWeightSelected),
                                    Pair("liquidType", liquid)
                                )

                                if (!json.isInvalidJSON() && password == confirmPassword) {
                                    registerOnClick(context, json)
                                } else {
                                    RegistrationActivity.showErrorMessage("Veuillez remplir les champs correctement")
                                }
                            }
                        }
                    }



                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF51A489)
                )
            ) {
                Text(
                    "S'inscrire",
                    color = Color.White,
                )
            }

            Spacer(modifier = Modifier.size(25.dp))

        }

    }
}

fun registerOnClick(context : Context?, json : HashMap<String,*>) {
    context?.let { APIUser.register(it,json) }
}

@Preview(showSystemUi = true)
@Composable
private fun RegistrationScreenPreview() {
    FitnessAndroidTheme() {
        RegistrationScreen(null)
    }
}

fun isSecurePassword(password: String): Boolean {
    val minLength = 8
    val containsUpperCase = password.any { it.isUpperCase() }
    val containsLowerCase = password.any { it.isLowerCase() }
    val containsDigit = password.any { it.isDigit() }
    val containsSpecialChar = password.any { !it.isLetterOrDigit() }

    return password.length >= minLength &&
            containsUpperCase &&
            containsLowerCase &&
            containsDigit &&
            containsSpecialChar
}



