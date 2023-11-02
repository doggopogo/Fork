package ca.etsmtl.log.fitnesshabits.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ca.etsmtl.log.fitnesshabits.ui.components.Header
import ca.etsmtl.log.fitnesshabits.R

@Composable
fun UserProfile(navController: NavController) {
    var isEditable = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Header(title = "User Profile", navigateBack = { navController.navigateUp() })

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(220.dp) // Canvas size
                .padding(top = 32.dp, start = 32.dp, end = 32.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_account_circle_gris),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { isEditable.value = !isEditable.value },
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_edit_pencil),
                    contentDescription = "Edit",
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
        Text(
            text = "John Doe",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "DOB: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "01/01/1990 (Age: 33)",
                    fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Height: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "180 cm",
                    fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Weight: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "75 kg",
                    fontSize = 18.sp
                )
            }
            Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "BMI: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "23.1",
                    fontSize = 18.sp
                )
            }
        }

        // Conditional rendering based on `isEditable`
        if (isEditable.value) {
            // Show editable fields
        } else {
            // Show non-editable fields (your existing code)
        }
    }
}
