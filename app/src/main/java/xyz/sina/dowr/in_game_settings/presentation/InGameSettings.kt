package xyz.sina.dowr.in_game_settings.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import xyz.sina.dowr.in_game_settings.data.Categories
import xyz.sina.dowr.in_game_settings.data.DifficultySelectOption
import xyz.sina.dowr.core.domain.InGameSettingsViewModel

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
                                difficulty = inGameSettingViewModel.difficulty,
                                time = inGameSettingViewModel.time
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
                .padding(innerPadding)
        ) {
            // for players to write their name and check if they want to randomise team or pick their teammates here based on their colors
            Row(modifier = Modifier.fillMaxWidth()) {
                ButtonUI(
                    text = "اضافه کردن بازیکن",
                    onClick = { inGameSettingViewModel.addTextField() })
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .verticalScroll(rememberScrollState())
                ) {
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
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    value = inGameSettingViewModel.time.toString(),
                    onValueChange = inGameSettingViewModel::changeTime,
                    label = { Text(text = "زمان برای هر تیم") })
                DifficultySingleSelectionList(
                    options = inGameSettingViewModel.difficultyOptions,
                    onOptionClicked = inGameSettingViewModel::selectionOptionSelected
                )
            }
            // categories of words and players word
            PickWordCategories()
        }
    }
}

@Composable
fun DifficultySingleSelectionList(
    options: List<DifficultySelectOption>,
    onOptionClicked: (DifficultySelectOption) -> Unit
) {
    LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        items(options) { option ->
            SingleSelection(option, onOptionClicked)
        }
    }
}

@Composable
fun SingleSelection(
    selectionOption: DifficultySelectOption,
    onOptionClicked: (DifficultySelectOption) -> Unit
) {
    ButtonUI(
        text = selectionOption.option,
        onClick = { onOptionClicked(selectionOption) },
        backgroundColor = if (!selectionOption.selected) Color.White else Color.Gray
    )
}

@Composable
fun PickWordCategories(

) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        state = rememberLazyGridState(),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(3.dp),
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        itemsIndexed(Categories){ index: Int, category ->
            CategoriesView(wordCategories = category, position = index, onItemClick = { position ->

            })
        }
    }
}
// should change the code wtf is that dude