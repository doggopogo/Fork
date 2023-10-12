package com.example.fitnessandroid

import android.app.Application
import android.content.Context
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
        setContent {
            FitnessAndroidTheme {
                LoginScreen(this@MainActivity)
            }
        }
        FitHabData.setUp()
    }
    /*companion object {
        private lateinit var application : Application
        private fun setContext(app : Application) {
            application = app
        }

        fun showErrorMessage(error : String) {
            Handler(Looper.getMainLooper()).post {
                //Toast.makeText(application.applicationContext, error, Toast.LENGTH_SHORT).show()
                Toast.makeText(application, error, Toast.LENGTH_SHORT).show()
                println("Problème d'accès au serveur. Error : " + error.toString())
            }
        }
    }*/

    companion object {

        private lateinit var application : Application
        private fun setContext(app : Application) {
            application = app
        }

        fun showErrorMessage(error: String) {

            if (!::application.isInitialized) {
                //throw IllegalStateException("Application context not initialized. Call setContext() first.")
                println("Problème d'accès au serveur. Error : " + error.toString())
            }

            Handler(Looper.getMainLooper()).post {
                //Toast.makeText(this@Companion.application.applicationContext, error, Toast.LENGTH_SHORT).show()
                println("Problème d'accès au serveur. Error : " + error.toString())
            }
        }
    }

}



