package xyz.sina.dowr.in_game_settings.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class InGameSettingsViewModel: ViewModel() {

    val textFields = mutableStateListOf<String>()
    var difficulty by mutableIntStateOf(1)
        private set
    var time by mutableIntStateOf(5)
        private set

    fun changeDifficulty(value : Int) = run { difficulty = value}
    fun changeTime(value : String) = run {val cleanedValue = value.replace(Regex("[^\\d]"), "") ; difficulty = cleanedValue.toInt()}

    fun addTextField() {
        textFields.add("")
        textFields.add("")
    }

    fun deleteTextFieldsPair(index: Int) {
        if (index in 0 until textFields.size && index + 1 < textFields.size) {
            textFields.removeAt(index + 1)
            textFields.removeAt(index)
        }
    }

    fun updateTextFields(index : Int , newText : String){
        if (index in textFields.indices){
            textFields[index] = newText
        }
    }

}