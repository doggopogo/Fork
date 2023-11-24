package ca.etsmtl.log.fitnesshabits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import ca.etsmtl.log.fitnesshabits.ui.navigation.NavigationGraph
import ca.etsmtl.log.fitnesshabits.ui.theme.FitnessHabitsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessHabitsTheme {
                NavigationGraph()
            }
        }
    }
}