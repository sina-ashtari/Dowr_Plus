package xyz.sina.dowr.core.domain

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import xyz.sina.dowr.core.data.Team
import xyz.sina.dowr.in_game_settings.data.DifficultySelectOption

class InGameSettingsViewModel : ViewModel() {


    val textFields = mutableStateListOf<String>()
    var difficulty by mutableIntStateOf(1)
        private set


    var time by mutableLongStateOf(5)
        private set

    // replacing invalid character cause exception in app while we want the time variable to be int so maybe user write a string, then use regex.
    fun changeTime(value: String) =
        run { val cleanedValue = value.replace(Regex("[^\\d]"), ""); time = cleanedValue.toLong() }


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

    fun updateTextFields(index: Int, newText: String) {
        if (index in textFields.indices) {
            textFields[index] = newText
        }
    }

    // Options For Difficulty
    private val _difficultyOptions = listOf(
        DifficultySelectOption(1, "آسون", false),
        DifficultySelectOption(2, "متوسط", false),
        DifficultySelectOption(3, "سخت", false),
        DifficultySelectOption(4, "تصادفی", false)
    ).toMutableStateList()

    val difficultyOptions: List<DifficultySelectOption>
        get() = _difficultyOptions

    fun selectionOptionSelected(
        selectedOption: DifficultySelectOption
    ) {
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



    val teams = mutableStateListOf<Team>()

    var currentTeamIndex by mutableStateOf(0)
    var currentPlayerIndex by mutableStateOf(0)
    var currentRemainingTime by mutableStateOf(time)
    var isTimerRunning by mutableStateOf(false)

    var isStarted by mutableStateOf(false)
        private set
    fun changeStartedState() = run {isStarted = !isStarted }

    fun createTeams() {
        teams.clear()
        val playerIterator = textFields.iterator()
        var teamID = 1
        while (playerIterator.hasNext()) {
            val tempP1 = playerIterator.next()
            if (playerIterator.hasNext()) {
                val tempP2 = playerIterator.next()
                teams.add(Team(id = teamID++, playerPair = tempP1 to tempP2, initialTime = time, remainingTime = time ))
            }
        }
    }

    fun startTurnInGame(){
        if (isTimerRunning) return
        isTimerRunning = true

        val currentTeam = teams[currentTeamIndex]
        object : CountDownTimer(time, 1000){
            override fun onTick(p0: Long) {
               currentRemainingTime = p0
            }

            override fun onFinish() {
                isTimerRunning = false
                currentRemainingTime = 0
                endTurnInGame()
            }

        }.start()
    }

    fun endTurnInGame(){
        val currentTeam = teams[currentTeamIndex]
        currentTeam.remainingTime = currentRemainingTime

        if (currentPlayerIndex == 0){
            currentPlayerIndex = 1
        }else{
            currentPlayerIndex = 0
            currentTeamIndex = (currentTeamIndex + 1) % teams.size
        }
        currentRemainingTime = teams[currentTeamIndex].remainingTime

    }

    fun getCurrentPlayerName(): String {
        val currentTeam = teams[currentTeamIndex]
        return if (currentPlayerIndex == 0) {
            currentTeam.playerPair.first
        } else {
            currentTeam.playerPair.second
        }
    }


}