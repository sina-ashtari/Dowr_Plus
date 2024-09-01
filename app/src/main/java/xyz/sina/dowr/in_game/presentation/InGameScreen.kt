package xyz.sina.dowr.in_game.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun InGameScreen(
    navController: NavController
) {

    Scaffold  {innerPadding ->

        Column(modifier = Modifier.fillMaxWidth().padding(innerPadding)) {

        }

    }

}