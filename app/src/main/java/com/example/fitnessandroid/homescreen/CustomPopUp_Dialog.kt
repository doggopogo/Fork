package com.example.fitnessandroid.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.fitnessandroid.ui.theme.appDefaultGray
import com.example.fitnessandroid.ui.theme.appGreen
import com.example.fitnessandroid.ui.theme.appText
import com.example.fitnessandroid.ui.theme.white
import java.util.Properties

@Composable
fun CustomPopup(
    title: String,
    message: String,
    showPopup: Boolean,
    onClose: () -> Unit
) {
    if (showPopup) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(white)
                    .border(1.dp, Color.Gray, shape = RectangleShape)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = onClose,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.h1,
                        color = appGreen
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.body1,
                        color = appText
                    )
                }
            }
        }
    }
}


@Composable
fun DialogWithCustomContent(
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit,
    dismissOnClickOutside: Boolean,
    dismissOnBackPress: Boolean,
    content: @Composable (onClose: () -> Unit) -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = { onShowDialogChange(false) },
            properties = DialogProperties(
                dismissOnClickOutside = dismissOnClickOutside,
                dismissOnBackPress = dismissOnBackPress
            ),
            content = {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        shape = RoundedCornerShape(0.dp)
                    ) {
                        content { onShowDialogChange(false) }
                    }
                }

            }
        )
    }
}

@Composable
fun CustomAlert(
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        ),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                color = appGreen,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.body2,
                    color = appText,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        buttons = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(text = "OK", color = appGreen)
                }
            }
        },
        shape = RectangleShape,
        backgroundColor = white,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ShowCustomAlert(
    title: String,
    message: String,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        CustomAlert(title = title, message = message, onDismiss = onDismiss)
    }
}






