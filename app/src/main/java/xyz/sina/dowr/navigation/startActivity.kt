package xyz.sina.dowr.navigation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.sina.dowr.Main
import xyz.sina.dowr.R
import xyz.sina.dowr.inGame.InGameScreen
import xyz.sina.dowr.tutorial.Tutorial
import xyz.sina.dowr.tutorial.changeScreen

class StartActivity : AppCompatActivity() {

    // todo : you should change startScreen based on check box in tutorial screen then if it is checked then change this getSharedPrefrence
    // todo : need just a boolean variable for checking it is checked or not.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

    }

    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("sharedPreference", MODE_PRIVATE)
        val screenFetcher = sharedPreferences.getBoolean("firstTime", true)

        if (screenFetcher) {

        }

    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = getSharedPreferences("sharedPreference", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("firstTime", changeScreen)
        editor.apply()
    }


}

