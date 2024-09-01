package xyz.sina.dowr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import xyz.sina.dowr.core.navigation.InGame
import xyz.sina.dowr.core.navigation.InGameSettings
import xyz.sina.dowr.core.navigation.MainPage
import xyz.sina.dowr.core.navigation.Tutorial
import xyz.sina.dowr.in_game.presentation.InGameScreen
import xyz.sina.dowr.in_game_settings.presentation.InGameSettingsScreen
import xyz.sina.dowr.main_page.presentation.MainPage
import xyz.sina.dowr.tutorial.TutorialUtils
import xyz.sina.dowr.tutorial.presentation.TutorialScreen
import xyz.sina.dowr.ui.theme.DowrTheme

class MainActivity : ComponentActivity() {

    private val tutorialUtils by lazy { TutorialUtils(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen()
        setContent {
            DowrTheme {
                val navController = rememberNavController()


                NavHost(
                    navController = navController,
                    startDestination = if (tutorialUtils.isBoardingCompleted()) MainPage else Tutorial
                ) {

                    composable<Tutorial> {
                        TutorialScreen {
                            tutorialUtils.setOnboardingCompleted()
                            navController.navigate(MainPage) {
                                popUpTo(Tutorial) { inclusive = true }
                            }
                        }
                    }
                    composable<MainPage> { MainPage(navController = navController) }
                    composable<InGameSettings> { InGameScreen(navController = navController) }
                    composable<InGame> { InGameSettingsScreen(navController = navController) }

                }
                
                
            }
        }
    }
}

