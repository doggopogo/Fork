package ca.etsmtl.log.fitnesshabits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import ca.etsmtl.log.fitnesshabits.ui.navigation.NavigationGraph
import androidx.lifecycle.viewmodel.compose.viewModel
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.AddHydrationViewModel
import ca.etsmtl.log.fitnesshabits.viewmodels.modules.AddHydrationViewModelFactory

/*
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authenticateUser()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun authenticateUser() {
        Log.d("BiometricAuth", "Attempting to authenticate user")
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.d(
                    "BiometricAuth",
                    "Biometric hardware available and user has enrolled biometrics"
                )
                showBiometricPrompt()
            }

            else -> {
                Log.d(
                    "BiometricAuth",
                    "Biometric authentication not available or not enrolled. Error code: ${
                        biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                    }"
                )
                //loadApp() // You might want to load the app even if biometric is not available
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun showBiometricPrompt() {
        Log.d("BiometricAuth", "Showing biometric prompt")
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Log.d("BiometricAuth", "Authentication error: $errString (Code: $errorCode)")
                    loadApp() // Decide how you want to handle authentication errors
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    Log.d("BiometricAuth", "Authentication succeeded")
                    loadApp()
                }

                override fun onAuthenticationFailed() {
                    Log.d("BiometricAuth", "Authentication failed")
                    // Decide how you want to handle authentication failures
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for Fitness Habits")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun loadApp() {
        Log.d("BiometricAuth", "Loading app")
        setContent {
            NavigationGraph()
        }
    }
}*/
/*
class MainActivity : AppCompatActivity() {

    private val keyguardManager by lazy { getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager }

    private val authenticationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            // Authentication succeeded
            loadApp()
        } else {
            // Authentication failed
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authenticateUser()
    }

    private fun authenticateUser() {
        if (keyguardManager.isDeviceSecure) {
            val intent = keyguardManager.createConfirmDeviceCredentialIntent("Authentication required", "Please enter your device PIN to continue")
            if (intent != null) {
                authenticationLauncher.launch(intent)
            } else {
                // Device is not secure, handle according to your app's requirements
                loadApp()
            }
        } else {
            // Device is not secure, handle according to your app's requirements
            loadApp()
        }
    }

    private fun loadApp() {
        setContent {
            NavigationGraph()
        }
    }
}
*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val hydrationRepository = (application as App).hydrationRepository
            val viewModel: AddHydrationViewModel = viewModel(
                factory = AddHydrationViewModelFactory(hydrationRepository)
            )
            NavigationGraph(viewModel)
        }
    }
}