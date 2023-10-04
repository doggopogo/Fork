package com.example.fitnessandroid.registration

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessandroid.Utilities.isInvalidJSON
import com.example.fitnessandroid.api.restrequest.user.APIUser
import com.example.fitnessandroid.composable.*
import com.example.fitnessandroid.composable.selector.SelectorButton
import com.example.fitnessandroid.fithabdata.FitHabData
import java.text.SimpleDateFormat
import kotlin.collections.HashMap

@Composable
fun RegistrationScreen(context : Context?) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            //.verticalScroll(rememberScrollState())
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF47AAAF), Color(0xFF1E6BB7))
                )
            )
    ) {
        Spacer(modifier = Modifier.size(16.dp))

        var firstName by remember { mutableStateOf("") }
        TextFieldComposable(
            value = firstName,
            onValueChange = { firstName = it },
            label = "Prenom"
        )

        var lastName by remember { mutableStateOf("") }
        TextFieldComposable(
            value = lastName,
            onValueChange = { lastName = it },
            label = "Nom"
        )

        var email by remember { mutableStateOf("") }
        TextFieldComposable(
            value = email,
            onValueChange = { email = it },
            label = "Courriel"
        )

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

        var confirmPassword by remember { mutableStateOf("") }
        TextFieldComposable(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Confirmer mot de passe",
            isPassword = true
        )
        
        Spacer(modifier = Modifier.size(8.dp))
        val genderOptions = FitHabData.fitHabConst.SexEnum
        var gender by remember { mutableStateOf(genderOptions.first()) }
        SelectorButton(options = genderOptions) { selectedGender ->
            gender = selectedGender
        }

        Spacer(modifier = Modifier.size(8.dp))
        val selectedBirthday = remember { mutableStateOf("") }
        DateComposable(labelName = "Birthday", context = LocalContext.current) { date ->
            selectedBirthday.value = date
        }

        val (weight, setWeight) = remember { mutableStateOf("") }
        val weightOptions = FitHabData.fitHabConst.WeightEnum
        val (optionWeightSelected, setOptionWeightSelected) = remember { mutableStateOf(weightOptions.first()) }
        EntryWithSelector(
            modifier = Modifier,
            textFieldLabel = "Weight",
            textValue = weight,
            onTextValueChange = setWeight,
            options = weightOptions,
            optionSelected = optionWeightSelected,
            setOptionSelected = setOptionWeightSelected
        )

        val (height, setHeight) = remember { mutableStateOf("") }
        val heightOptions = FitHabData.fitHabConst.HeightEnum
        val (optionHeightSelected, setOptionHeightSelected) = remember { mutableStateOf(heightOptions.first()) }
        EntryWithSelector(
            modifier = Modifier,
            textFieldLabel = "Height",
            textValue = height,
            onTextValueChange = setHeight,
            options = heightOptions,
            optionSelected = optionHeightSelected,
            setOptionSelected = setOptionHeightSelected
        )

        val liquidOptions = FitHabData.fitHabConst.LiquidEnum
        var liquid by remember { mutableStateOf(liquidOptions.first()) }
        SelectorButton(options = liquidOptions) { selectedLiquid ->
            liquid = selectedLiquid
        }

        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = {
                val json = hashMapOf(
                    Pair("email",email),
                    Pair("firstName", firstName),
                    Pair("lastName", lastName),
                    Pair("username", username),
                    Pair("password", password),
                    Pair("sex", gender),
                    Pair("birthday", SimpleDateFormat("dd/MM/yyyy").parse(selectedBirthday.value).toString()),
                    Pair("size", height),
                    Pair("sizeType", optionHeightSelected),
                    Pair("weight", weight),
                    Pair("weightType", optionWeightSelected),
                    Pair("liquidType", liquid)
                )
                if(!json.isInvalidJSON() && password == confirmPassword) {
                    registerOnClick(context, json)
                } else {
                    RegistrationActivity.showErrorMessage("Veuillez remplir les champs correctement")
                }
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF51A489)
            )
        ) {
            Text("Register", color =  Color.White)
        }
    }
}



fun registerOnClick(context : Context?, json : HashMap<String,*>) {
    context?.let { APIUser.register(it,json) }
}

@Preview(showSystemUi = true)
@Composable
private fun RegistrationScreenPreview() {
    RegistrationScreen(null)
}



