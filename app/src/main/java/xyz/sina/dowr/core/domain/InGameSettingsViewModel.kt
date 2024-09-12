package xyz.sina.dowr.core.domain

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import xyz.sina.dowr.core.data.Team
import xyz.sina.dowr.in_game_settings.data.DifficultySelectOption

class InGameSettingsViewModel: ViewModel() {


    val textFields = mutableStateListOf<String>()
    var difficulty by mutableIntStateOf(1)
        private set


    var time by mutableIntStateOf(5)
        private set
    // replacing invalid character cause exception in app while we want the time variable to be int so maybe user write a string, then use regex.
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



    // #=============================#
    // In Game ViewModel Should start from here.
    // why i didn't create another class ?
    // base on this :
    // https://developer.android.com/guide/navigation/navigation-pass-data#supported_argument_types
    // <<Caution: Passing complex data structures over arguments is considered an anti-pattern.
    // Each destination should be responsible for loading UI data based on the minimum
    // necessary information, such as item IDs. This simplifies process recreation and avoids
    // potential data inconsistencies.>>
    // if i find a better way, why not ?
    // #=============================#


    //
    val team = listOf(
        Team(id = 1, playerPair = "" to "", teamTime = time)

    ).toMutableStateList()

//    private fun initialiseTeams(){
//        for (i in textFields.iterator()){
//            team.add(id = )
//        }
//    }
    // this is timer function that gonna used be in InGameScreen
    val timer = object : CountDownTimer( time.toLong(),1000){

        override fun onTick(millisUntilFinished : Long) {
            TODO( " ok this is gonna be tick tack sfx")
        }
        override fun onFinish() {
            TODO(" Check which team is lost; and show it in ui")

        }
    }


}