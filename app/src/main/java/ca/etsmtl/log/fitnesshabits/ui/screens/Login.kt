package ca.etsmtl.log.fitnesshabits.ui.screens

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ca.etsmtl.log.fitnesshabits.R
import java.time.format.TextStyle


@Composable
fun Login(navController: NavController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF56C4AD), Color(0xFF1E6BB7))
                )
            )
    ) {
        Image(
            painter = painterResource(R.drawable.logo_legion_keh_2x),
            contentDescription = "Image description",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
        )

        Spacer(modifier = Modifier.size(12.dp))

        val localContext = LocalContext.current
        val biometricManager: BiometricManager = remember { BiometricManager.from(localContext) }

        LoginWithBiometry(
            navController = navController,
            context = localContext,
            biometricManager = biometricManager
        )
    }
}

@Composable
fun LoginWithBiometry(
    navController: NavController,
    context: Context?,
    biometricManager: BiometricManager
) {

    val isBiometryAvailable = remember {
        biometricManager.canAuthenticate(BIOMETRIC_WEAK or DEVICE_CREDENTIAL)
    }

    when (isBiometryAvailable) {
        BiometricManager.BIOMETRIC_SUCCESS -> {
            CreateBiometric(navController)
        }

        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
            //User doesn't have security on is device

            Button(
                modifier = Modifier.padding(4.dp),
                onClick = { navController.navigate("dashboard") }) {
                Text(
                    text = stringResource(id = R.string.open_app),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        else -> Toast.makeText(
            context,
            stringResource(id = R.string.biometric_not_available),
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
fun CreateBiometric(navController: NavController) {
    val context = LocalContext.current

    val executor = remember { ContextCompat.getMainExecutor(context) }
    val biometricPrompt = BiometricPrompt(
        context as FragmentActivity,
        executor,
        object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                // handle authentication error here and if user cancel to enter creds
                Toast.makeText(context, R.string.cancel_login, Toast.LENGTH_SHORT).show()
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                // handle authentication success here
                navController.navigate("dashboard")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                // handle authentication failure here
                Toast.makeText(context, R.string.failed_login, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    )

    val promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setAllowedAuthenticators(BIOMETRIC_WEAK or DEVICE_CREDENTIAL)
        .setTitle(stringResource(id = R.string.biometric_title))
        .setSubtitle(stringResource(id = R.string.biometric_description))
        .build()

    Button(
        modifier = Modifier.padding(4.dp),
        onClick = { biometricPrompt.authenticate(promptInfo) }) {
        Text(
            text = stringResource(id = R.string.connect_with_biometric),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginPreview() {
    Login(navController = rememberNavController())
}
