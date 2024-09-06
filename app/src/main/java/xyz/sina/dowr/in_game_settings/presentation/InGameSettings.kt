package xyz.sina.dowr.in_game_settings.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import xyz.sina.dowr.core.navigation.InGame
import xyz.sina.dowr.core.persentation.ButtonUI

@Composable
fun InGameSettingsScreen(
    navController: NavController,
    inGameSettingViewModel: InGameSettingsViewModel = viewModel()
) {

    val colors = listOf(Color.Blue, Color.Red, Color.Green)

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        navController.navigate(
                            InGame(
                                numberOfPlayer = inGameSettingViewModel.textFields.size,
                                difficulty = inGameSettingViewModel.difficulty
                            )
                        )
                    }) {
                        Text(text = "شروع")
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            // for players to write their name and check if they want to randomise team or pick their teammates here based on their colors
            Row(modifier = Modifier.fillMaxWidth()) {
                ButtonUI(
                    text = "اضافه کردن بازیکن",
                    onClick = { inGameSettingViewModel.addTextField() })
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                inGameSettingViewModel.textFields.forEachIndexed { index, text ->

                    val colorIndex = (index / 2) % colors.size
                    val backgroundColor = colors[colorIndex]
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .background(backgroundColor)
                                .fillMaxWidth()
                                .padding(8.dp),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            textStyle = TextStyle(textDirection = TextDirection.Content),
                            value = text,
                            onValueChange = { newValue ->
                                inGameSettingViewModel.updateTextFields(
                                    index,
                                    newValue
                                )
                            })
                    }
                }
            }

            // for words difficulty and total time per team
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = inGameSettingViewModel.time.toString(),
                    onValueChange = inGameSettingViewModel::changeTime,
                    label = { Text(text = "زمان برای هر تیم") })
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { inGameSettingViewModel.changeDifficulty(1) }) {
                        Text(text = "آسون")
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { inGameSettingViewModel.changeDifficulty(2) }) {
                        Text(text = "متوسط")
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { inGameSettingViewModel.changeDifficulty(3) }) {
                        Text(text = "سخت")
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { inGameSettingViewModel.changeDifficulty(3) }) {
                        Text(text = "تصادفی")
                    }
                }
            }
            // categories of words and players word
            Column(modifier = Modifier.fillMaxWidth()) {

            }
        }
    }
}