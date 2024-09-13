package xyz.sina.dowr.core.data

data class Team(
    val id: Int,
    val playerPair: Pair<String, String>,
    val initialTime: Long,
    var remainingTime: Long,
    var isTimerRunning: Boolean = false
)