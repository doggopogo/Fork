package ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.helper.Utilities
import ca.etsmtl.log.fitnesshabits.ui.views.TextFieldComposable
import ca.etsmtl.log.fitnesshabits.ui.views.selector.DropdownSelector
import ca.etsmtl.log.fitnesshabits.ui.views.selector.SelectorRadioButton
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabUiState
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessAndroidTheme

@Composable
fun TopBarModalProfile() {
    TopAppBar(
        title = {
            Image(modifier = Modifier
                .padding(horizontal = 25.dp)
                .size(30.dp),
                painter = painterResource(id = R.drawable.icon_logo),
                contentDescription = null)
            Text(
                modifier = Modifier
                    .padding(vertical = 10.dp),
                text = stringResource(id = R.string.app_name_Habits),
            )
        },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )
}

@Composable
fun ModalAlertProfile(uiState : FitHabUiState) {
    FitnessAndroidTheme {
        Column {
            val openDialog = remember { mutableStateOf(false)  }

            IconButton(onClick = {
                openDialog.value = true
            }) {
                Icon(
                    Icons.Filled.Edit,""
                )
            }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    {
                        TopBarModalProfile()
                        Spacer(modifier = Modifier.size(20.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 30.dp, end = 30.dp),
                        )
                        {
                            var name by remember { mutableStateOf("") }
                            TextFieldComposable(
                                value = uiState.userInfo?.let { "${it.user?.lastName} ${it.user?.firstName}" } ?: "",
                                onValueChange = { name = it },
                                label = ""
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = Color.LightGray, thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            var pseudo by remember { mutableStateOf("") }
                            TextFieldComposable(
                                value = uiState.userInfo?.user?.pseudo ?: "",
                                onValueChange = { pseudo = it },
                                label = ""
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = Color.LightGray, thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Row() {
                                Text(
                                    text = uiState.userInfo.user?.birthday?.toString() ?: "",
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier
                                    .width(90.dp))
                                Image(
                                    modifier = Modifier
                                        .size(20.dp),
                                    painter = painterResource(id = R.drawable.icon_calendar),
                                    contentDescription = null
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = Color.LightGray, thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.size(25.dp))
                            Row(){
                                Text(
                                    modifier = Modifier
                                        .padding(vertical = 20.dp)
                                        .padding(start = 50.dp),
                                    text = uiState.userInfo.user?.size?.toString() ?: "",
                                    textAlign = TextAlign.Center
                                )
                                val (optionSelected, setOptionSelected) = remember { mutableStateOf("") }
                                DropdownSelector(
                                    modifier = Modifier,
                                    options = listOf("m", "pi.po"),
                                    setOptionSelected = setOptionSelected
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = Color.LightGray, thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            val (genderSelected, setGenderSelected) = remember { mutableStateOf(uiState.userInfo.user?.sex ?: "") }
                            SelectorRadioButton(
                                modifier = Modifier
                                    .background(Color.LightGray)
                                    .padding(start = 10.dp),
                                listOf("Male", "Female", "Non-binary"),
                                genderSelected,
                                setGenderSelected
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(
                                onClick = {
                                    openDialog.value = false
                                },
                                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.appGreen)),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, end = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Sauvegarder",
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    },
                    backgroundColor = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TopBarPreview() {
    TopBarModalProfile()
}

@Preview(showBackground = true)
@Composable
private fun ModalPreview() {
    ModalAlertProfile(Utilities.fitHabUiStateForPreview())
}
