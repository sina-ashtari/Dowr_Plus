package xyz.sina.dowr.in_game_settings.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class DifficultySelectOption(
    val option: String,
    var initialSelectedValue : Boolean
){
    var selected by mutableStateOf(initialSelectedValue)
}