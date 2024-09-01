package xyz.sina.dowr.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object MainPage

@Serializable
object Tutorial

@Serializable
object InGameSettings

@Serializable
data class InGame(
    val numberOfPlayer : Int,
    // val listOfTeams : List,
    //val time: Double, // idk
    //val categories : List,
    val difficulty : Int
)

@Serializable
data class GameResult(
    val numberOfPlayer : Int,
    //val time: Double, // idk
    //val categories : List
    val difficulty : Int
)
