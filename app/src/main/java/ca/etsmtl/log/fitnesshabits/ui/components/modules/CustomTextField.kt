package ca.etsmtl.log.fitnesshabits.ui.components.modules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    text: String,
    onTextChange: (String) -> Unit,
    placeholder: String? = null,
    label: String,
    isTitle: Boolean = false,
    color: Int,
    numbersOnly: Boolean = false,
    optional: Boolean = false
) {
    TextField(
        modifier = Modifier
            .then(
                if (isTitle) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier
                }
            ),
        value = text,
        onValueChange = onTextChange,
        textStyle = TextStyle(
            fontSize =
            if (isTitle) {
                20.sp
            } else {
                14.sp
            }
        ),
        label = {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(label, fontSize = 14.sp)
                if (optional) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("(optionel)", fontSize = 12.sp, fontStyle = FontStyle.Italic)
                }
            }
        },
        placeholder = {
            if (placeholder != null) {
                Text(placeholder, fontSize = 14.sp)
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = colorResource(color),
            focusedIndicatorColor = colorResource(color),
            unfocusedIndicatorColor = colorResource(color).copy(alpha = 0.5f),
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black.copy(alpha = 0.5f)
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType =
            if (numbersOnly) {
                KeyboardType.Number
            } else {
                KeyboardType.Text
            }
        ),
        maxLines = 1
    )
}