package xyz.sina.dowr.in_game_settings.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import xyz.sina.dowr.in_game_settings.data.DifficultySelectOption

class InGameSettingsViewModel: ViewModel() {

    val textFields = mutableStateListOf<String>()
    var difficulty by mutableIntStateOf(1)
        private set
    var time by mutableIntStateOf(5)
        private set

    fun changeTime(value : String) = run {val cleanedValue = value.replace(Regex("[^\\d]"), "") ; time = cleanedValue.toInt()}


    // ADU TextField For Teams
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

    // Options For Difficulty
    private val _difficultyOptions = listOf(
        DifficultySelectOption(1,"آسون" ,false),
        DifficultySelectOption(2,"متوسط", false),
        DifficultySelectOption(3,"سخت", false),
        DifficultySelectOption(4, "تصادفی", false)
    ).toMutableStateList()

    val difficultyOptions : List<DifficultySelectOption>
        get() = _difficultyOptions

    fun selectionOptionSelected(
        selectedOption : DifficultySelectOption
    ){
        _difficultyOptions.forEach { it.selected = false }
        _difficultyOptions.find { it.option == selectedOption.option }?.selected = true
        difficulty = selectedOption.id
    }

    // option for categories
    var selectedCategoryIndex by mutableStateOf(emptySet<Int>())
        private set

}