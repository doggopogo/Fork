package ca.etsmtl.log.fitnesshabits.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.etsmtl.log.fitnesshabits.R
import ca.etsmtl.log.fitnesshabits.ui.theme.appGreen
import ca.etsmtl.log.fitnesshabits.ui.theme.white

@Composable
fun DashboardHeader(
    onNavigationIconClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = appGreen)
            .height(76.dp)
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onNavigationIconClick) {
            Icon(
                imageVector = Icons.Default.Menu,
                modifier = Modifier.size(32.dp),
                contentDescription = "Toggle drawer",
                tint = white
            )
        }
        Spacer(modifier = Modifier.width(32.dp))
        Image(
            painter = painterResource(id = R.drawable.logo_legion_keh_2x),
            contentDescription = "Logo",
            modifier = Modifier
                .size(64.dp)
                .padding(horizontal = 12.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth(),
            color = white
        )
    }
}
