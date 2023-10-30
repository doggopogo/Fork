package ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.helper.Utilities.fitHabUiStateForPreview
import ca.etsmtl.log.fitnesshabits.ui.views.SocialButtonFacebook
import ca.etsmtl.log.fitnesshabits.ui.views.SocialButtonGoogle
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabUiState
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessAndroidTheme

@Composable
fun ProfileScreen(uiState : FitHabUiState) {
    PageItem(uiState)
}

@Composable
fun PageItem(uiState : FitHabUiState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Image(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .padding(horizontal = 10.dp),
            painter = painterResource(id = R.drawable.icon_picture),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                fontSize = 18.sp,
                text = "Profil",
                color = Color.Black
            )
            Spacer(modifier = Modifier
                .width(280.dp))
            ModalAlertProfile(uiState)
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray, thickness = 1.dp
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.LightGray, thickness = 1.dp
            )
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = uiState.userInfo.user?.let {  "${it.firstName} ${it.lastName}" } ?: "",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = uiState.userInfo.user?.email ?: "",
                maxLines = 1,
                color = Color.Black
            )

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp))
            {
                //TODO : Implémenter l'âge de l'utilisateur dans la BD User.
                Text(
                    modifier = Modifier,
                    text = "28 ans",
                    maxLines = 1,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    text = uiState.userInfo.user?.let { it.size.toString() + it.sizeType } ?: "",
                    maxLines = 1,
                    color = Color.Black
                )
            }
            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = "Poids",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                color = Color.Black
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp))
            {
                Text(
                    modifier = Modifier,
                    text = uiState.userInfo.user?.let { it.weight.toString() + it.weightType } ?: "",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = Color.Black
                )
                //TODO : Implémenter l'IMC de l'utilisateur dans la BD User.
                Text(
                    modifier = Modifier.padding(horizontal = 25.dp),
                    text = "IMC : 25,00kg",
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    color = Color.Black
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 0.dp))
            {
                Text(
                    text = "Poids initial entré : " + (uiState.userInfo.user?.let { it.weight.toString() + it.weightType } ?: ""),
                    maxLines = 1,
                    fontSize = 12.sp,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(horizontal = 22.dp),
                    text = "IMC initial entré : 35",
                    fontSize = 12.sp,
                    maxLines = 1,
                    color = Color.Black
                )
            }
            Text(
                modifier = Modifier.padding(vertical = 22.dp),
                text = "Information de connexion",
                maxLines = 1,
                color = Color.Black
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                color = Color.LightGray, thickness = 1.dp
            )
            Text(
                modifier = Modifier.padding(vertical = 22.dp),
                text = "Modifier le compte de connexion",
                maxLines = 1,
                color = Color.Black
            )
            SocialButtonGoogle {}
            SocialButtonFacebook {}
            Spacer(modifier = Modifier.size(20.dp))
            //TODO : Implémenter le bouton de modification du mot de passe.

            ModalPasswordChange(uiState)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemPreview() {
    FitnessAndroidTheme() {
        PageItem(fitHabUiStateForPreview())
    }
}