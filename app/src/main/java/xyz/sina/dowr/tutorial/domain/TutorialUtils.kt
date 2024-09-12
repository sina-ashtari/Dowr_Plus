package xyz.sina.dowr.tutorial.domain

import android.content.Context

class TutorialUtils (private val context : Context) {
    fun isBoardingCompleted():Boolean {
        return context.getSharedPreferences("onBoardingCompleted", Context.MODE_PRIVATE).getBoolean("completed", false)
    }
    fun setOnboardingCompleted(){
        context.getSharedPreferences("onBoardingCompleted", Context.MODE_PRIVATE).edit().putBoolean("completed", true).apply()
    }
}