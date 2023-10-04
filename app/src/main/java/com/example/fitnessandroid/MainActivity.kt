package com.example.fitnessandroid

import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fitnessandroid.fithabdata.FitHabData
import com.example.fitnessandroid.login.LoginScreen
import com.example.fitnessandroid.ui.theme.FitnessAndroidTheme

class MainActivity : ComponentActivity() {
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContext(this.application)
        FitHabData.setUp()
        setContent {
            FitnessAndroidTheme {
                LoginScreen(this@MainActivity)
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



