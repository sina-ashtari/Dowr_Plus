package xyz.sina.dowr.in_game_settings.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun InGameSettingsScreen(
    navController: NavController
){
    Scaffold() { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
            // for players to write their name and check if they want to randomise team or pick their teammates here based on their colors
            Row(modifier = Modifier.fillMaxWidth()) {

            }
            // for words difficulty and total time per team
            Row(modifier = Modifier.fillMaxWidth()) {

            }
            // categories of words and players word
            Row(modifier = Modifier.fillMaxWidth()) {

            }
        }
    }
}