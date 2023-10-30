package ca.etsmtl.log.fitnesshabits.ui.screens.settingsdrawer.drawer

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.FitHabData
import ca.etsmtl.log.fitnesshabits.toDelete.fithabdata.isUserAuth
import ca.etsmtl.log.fitnesshabits.ui.screens.homescreen.user.User
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessAndroidTheme
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch

class DrawerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContext(this.application)

        val localUser = intent.extras?.get("User") as String
        val idUser = intent.extras?.get("idUser") as String
        Log.d("nemo user", localUser)
        val gson = GsonBuilder().setLenient().create()
        val convertedLocalUser = gson.fromJson(localUser, User::class.java)

        FitHabData.setUser(idUser, convertedLocalUser)

        lifecycleScope.launch {
            FitHabData.uiState.collect { fitHabUiState ->
                if (fitHabUiState.isUserAuth()) {
                    setContent {
                        FitnessAndroidTheme {
                            DrawerScreen(this@DrawerActivity, fitHabUiState)
                        }
                    }
                } else {
                    val navigate = Intent(this@DrawerActivity, ca.etsmtl.log.fitnesshabits.MainActivity::class.java)
                    this@DrawerActivity.startActivity(navigate)
                }
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