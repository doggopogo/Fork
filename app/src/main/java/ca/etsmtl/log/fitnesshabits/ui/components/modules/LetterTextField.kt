package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LetterTextField(
    value: String,
    onValueChange: (String) -> Unit,
    fontSize: TextUnit,
    isBold: Boolean = false,
    maxChar: Int = 4,
    maxWidth: Dp = 60.dp,
    withBackground: Boolean = false,
    isCentered: Boolean = false
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= maxChar) {
                onValueChange(newValue)
            }
        },
        textStyle = if (isFocused) {
            TextStyle(
                fontSize = fontSize,
                color = Color.Black,
                textAlign = if (isCentered) {
                    TextAlign.Center
                } else {
                    TextAlign.Right
                },
                fontWeight =
                if (isBold) {
                    FontWeight.Black
                } else {
                    FontWeight.Normal
                }
            )
        } else {
            TextStyle(
                fontSize = (fontSize.value - 2).sp,
                color = Color.Black.copy(alpha = 0.6f),
                textAlign = if (isCentered) {
                    TextAlign.Center
                } else {
                    TextAlign.Right
                },
                fontWeight =
                if (isBold) {
                    FontWeight.Black
                } else {
                    FontWeight.Normal
                }
            )
        },
        singleLine = true,
        modifier = if (withBackground) {
            Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .widthIn(30.dp, maxWidth)
                .clip(RoundedCornerShape(15.dp))
                .background(Color.Black.copy(0.03f))
                .padding(6.dp)
        } else {
            Modifier
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .widthIn(30.dp, maxWidth)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            capitalization = KeyboardCapitalization.Sentences
        )
//        keyboardActions = KeyboardActions(
//            onDone = {
//                isEditing = false
//                // Optionally update the amount value here if needed
//            }
//        ),
    )
}