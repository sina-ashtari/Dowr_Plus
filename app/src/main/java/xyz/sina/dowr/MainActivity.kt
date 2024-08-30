package xyz.sina.dowr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import xyz.sina.dowr.main_page.presentation.MainPage
import xyz.sina.dowr.tutorial.TutorialUtils
import xyz.sina.dowr.tutorial.presentation.TutorialScreen
import xyz.sina.dowr.ui.theme.DowrTheme

class MainActivity : ComponentActivity() {

    private val tutorialUtils by lazy { TutorialUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            DowrTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = MainPage){
                    composable<MainPage> { MainPage() }
                }
                if(tutorialUtils.isBoardingCompleted()){
                    // Navigation
                }else{
                    TutorialScreen{}
                }
            }
        }
    }
}


@Serializable
object MainPage