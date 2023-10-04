package com.example.fitnessandroid.settingsdrawer.drawer

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
import com.example.fitnessandroid.MainActivity
import com.example.fitnessandroid.fithabdata.FitHabData
import com.example.fitnessandroid.fithabdata.isUserAuth
import com.example.fitnessandroid.homescreen.user.User
import com.example.fitnessandroid.ui.theme.FitnessAndroidTheme
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
                    val navigate = Intent(this@DrawerActivity, MainActivity::class.java)
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