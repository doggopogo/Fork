package ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.helper.Utilities
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabUiState
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessAndroidTheme

@Composable
fun ModalPasswordChange(uiState: FitHabUiState) {
    FitnessAndroidTheme {
        Column {
            val openDialog = remember { mutableStateOf(false) }

            Button(
                onClick = {
                    openDialog.value = true
                }) {
                Text(
                    text = stringResource(id = R.string.modify_password)
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
                            var oldPassword by remember { mutableStateOf("") }
                            TextField(
                                value = oldPassword,
                                onValueChange = { oldPassword = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.old_password),
                                        style = MaterialTheme.typography.h1,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black
                                    )
                                },
                                singleLine = true,
                                textStyle = MaterialTheme.typography.h1,
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = Color.Black,
                                    unfocusedIndicatorColor = Color.Black,
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Black,
                                    textColor = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = Color.LightGray, thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            var newPassword by remember { mutableStateOf("") }
                            TextField(
                                value = newPassword,
                                onValueChange = { newPassword = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.new_password),
                                        style = MaterialTheme.typography.h1,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black
                                    )
                                },
                                singleLine = true,
                                textStyle = MaterialTheme.typography.h1,
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = Color.Black,
                                    unfocusedIndicatorColor = Color.Black,
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Black,
                                    textColor = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = Color.LightGray, thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            var confirmPassword by remember { mutableStateOf("") }
                            TextField(
                                value = confirmPassword,
                                onValueChange = { confirmPassword = it },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.confirm_password),
                                        style = MaterialTheme.typography.h1,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Black
                                    )
                                },
                                singleLine = true,
                                textStyle = MaterialTheme.typography.h1,
                                colors = TextFieldDefaults.textFieldColors(
                                    cursorColor = Color.Black,
                                    unfocusedIndicatorColor = Color.Black,
                                    backgroundColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Black,
                                    textColor = Color.Black
                                )
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = Color.LightGray, thickness = 1.dp
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            Button(
                                onClick = {
                                    openDialog.value = false
                                    //TODO add communication with server to apply change
                                },
                                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                                shape = RoundedCornerShape(50.dp),
                                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.appGreen)),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp, end = 10.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.save),
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
    ModalPasswordChange(Utilities.fitHabUiStateForPreview())
}
