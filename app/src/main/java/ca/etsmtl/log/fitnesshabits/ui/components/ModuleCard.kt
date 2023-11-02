package ca.etsmtl.log.fitnesshabits.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.ui.theme.appText

@Composable
fun ModuleCard(
    title: String,
    icon: Int,
    backgroundColorIcon: Int,
    onIconClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(20.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 3.dp,
                    RoundedCornerShape(18.dp)
                )
                .clip(RoundedCornerShape(18.dp))
                .background(Color.White)
                .clickable(onClick = onInfoClick)
        ) {
            Card( //Icon du module
                modifier = Modifier
                    .width(105.dp)
                    .height(100.dp)
                    .clickable(onClick = onIconClick),
                backgroundColor = colorResource(id = backgroundColorIcon)
            ) {
                Image( //image du module
                    painter = painterResource(id = icon),
                    contentDescription = "module image",
                    modifier = Modifier.requiredSize(40.dp)
                )

                Row( //Cercle blanc dans l'icon du module
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(13.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                    ) {

                    }
                }

            }
            Row( //section d'information du module
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            ) {
                val percent = 0.7f
                Column(
                    Modifier
                        .fillMaxHeight()
                    //.fillMaxWidth(percent)
                ) {
                    Text(
                        text = title,
                        maxLines = 2,
                        fontSize = 20.sp,
                        color = appText,
                        fontWeight = FontWeight.Bold
                    )
                    //ShowStatsContent(statsList = moduleUiState.statsGrid)
                }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "",
                        maxLines = 2,
                        fontSize = 18.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Right
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}
