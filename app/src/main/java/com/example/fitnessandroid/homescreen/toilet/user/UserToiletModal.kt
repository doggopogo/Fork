package com.example.fitnessandroid.homescreen.toilet.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitnessandroid.R
import com.example.fitnessandroid.Utilities
import com.example.fitnessandroid.Utilities.toStringYMDTHM
import com.example.fitnessandroid.common.PreviewComposable
import com.example.fitnessandroid.composable.DatePickerComposable
import com.example.fitnessandroid.composable.TextFieldNumberComposable
import com.example.fitnessandroid.composable.TimePicker
import com.example.fitnessandroid.composable.selector.SelectorImageToilet
import com.example.fitnessandroid.fithabdata.FitHabData
import com.example.fitnessandroid.login.HomepageButton
import java.util.*
import kotlin.collections.HashMap

@Composable
fun UserToiletModal(
    modifier : Modifier = Modifier,
    userToilet: UserToilet? = null
) {
    val numberOfUrine = remember { mutableStateOf(userToilet?.numberOfUrine?.toString() ?: "") }
    val calendar = Calendar.getInstance()
    val dateText = remember { mutableStateOf(userToilet?.timestamp ?: calendar.time) }
    val timeText = remember { mutableStateOf(userToilet?.timestamp ?: calendar.time) }
    val scrollState = rememberScrollState()

    val title = listOf(
        "Constipation",
        "Selles idéales",
        "Vers la diarrhée"
    )
    val list = listOf(
        Pair("Type 1", R.drawable.bristol1),
        Pair("Type 3", R.drawable.bristol3),
        Pair("Type 5", R.drawable.bristol5),
        Pair("Type 2", R.drawable.bristol2),
        Pair("Type 4", R.drawable.bristol4),
        Pair("Type 6", R.drawable.bristol6),
        Pair("", null),
        Pair("Aucun", null),
        Pair("Type 7", R.drawable.bristol7)
    )

    val (fecesType, setFecesType) = remember {
        mutableStateOf(
            if(userToilet?.fecesType != null)
                if(userToilet.fecesType.toInt().toString() == "0")
                    "Aucun"
                else
                    "Type ${userToilet.fecesType.toInt()}"
            else
                list[7].first
        )
    }

    Column(
        modifier = modifier.padding(horizontal = 16.dp).height(LocalConfiguration.current.screenHeightDp.dp).verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldNumberComposable(
            value = numberOfUrine.value,
            onValueChange = {
                numberOfUrine.value = it
            },
            label = "Nombre d'urine",
            color = Color.Black
        )
        Spacer(modifier = Modifier.padding(5.dp))

        SelectorImageToilet(
            modifier = Modifier.align(Alignment.CenterHorizontally).height(((LocalConfiguration.current.screenHeightDp.dp/4).value *2.5).dp),
            title = title,
            options  = list,
            optionSelected = fecesType,
            setOptionSelected = setFecesType
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Sources :",
                fontSize = 18.sp
            )
            val annotatedString = buildAnnotatedString {
                pushStringAnnotation(
                    tag = "pharmacie",
                    annotation = "https://www.pharmacie-principale.ch/themes-sante/divers/la-forme-et-la-texture-de-nos-selles-nous-parlent-de-notre-sante"
                )
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("Pharmacie Principale")
                }
                pop()
            }
            val uriHandler = LocalUriHandler.current
            ClickableText(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                text = annotatedString,
                onClick = {
                    annotatedString
                        .getStringAnnotations("pharmacie", it, it)
                        .firstOrNull()?.let { stringAnnotation ->
                            uriHandler.openUri(stringAnnotation.item)
                        }
                }
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 20.dp)
                .align(Alignment.CenterHorizontally)
        ){
            DatePickerComposable(
                modifier = Modifier.size(100.dp),
                defaultDate = userToilet?.timestamp,
                color = colorResource(id = R.color.toilet),
                textColor = Color.White
            ) {
                dateText.value = it
            }
            TimePicker(
                modifier = Modifier
                    .size(100.dp)
                    .padding(start = 20.dp),
                defaultDate = userToilet?.timestamp,
                color = colorResource(id = R.color.toilet),
                textColor = Color.White
            ) {
                timeText.value = it
            }
        }

        HomepageButton(
            text = userToilet?.let { "Modifier".uppercase() } ?: "Ajouter".uppercase(),
            onClick = {
                val json = hashMapOf<String,String>()
                json["numberOfUrine"] = numberOfUrine.value
                json["fecesType"] = if(fecesType == "Aucun") "0" else fecesType.last().toString()
                json["timestamp"] = Utilities.dateAndTimeCalendar(dateText.value, timeText.value).toStringYMDTHM()

                if(userToilet == null) {
                    addUserToilet(json)
                } else {
                    userToilet.idUserToilet.let { json.put("idUserToilet", it) }
                    editUserToilet(json)
                }
            },
            modifier = Modifier
                .padding(start = 5.dp, end = 20.dp)
                .align(Alignment.CenterHorizontally),
            buttonColors = ButtonDefaults.buttonColors(colorResource(R.color.toilet)),
            textColor = Color.White,
            enabled = (numberOfUrine.value.isNotEmpty())
        )
    }
}

private fun editUserToilet(json : HashMap<String,String>) {
    FitHabData.updateUserToilet(json)
}

private fun addUserToilet(json : HashMap<String,String>) {
    FitHabData.createUserToilet(json)
}

@Preview(showSystemUi = true)
@Composable
fun UserToiletModalCreatePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        UserToiletModal(it)
    }
}

@Preview(showSystemUi = true)
@Composable
fun UserToiletModalUpdatePreview() {
    PreviewComposable(backgroundColor = Color.White) {
        UserToiletModal(it, Utilities.userInfoForPreview().userToilet.value.first())
    }
}