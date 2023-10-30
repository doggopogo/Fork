package ca.etsmtl.log.fitnesshabits.ui.screens.registration

import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessAndroidTheme

class RegistrationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContext(this.application)
        setContent {
            FitnessAndroidTheme {
                RegistrationScreen(this@RegistrationActivity)
            }
        }
    }

    companion object {
        private lateinit var application: Application
        private fun setContext(app: Application) {
            application = app
        }

        fun showErrorMessage(error : String) {
            Handler(Looper.getMainLooper()).post {
                Toast.makeText(application.applicationContext, error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}