package xyz.sina.dowr.in_game_settings.data

import androidx.annotation.DrawableRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import xyz.sina.dowr.R

@Stable
data class WordCategories(
    @DrawableRes val image : Int,
    val name : String,
    var isSelected : MutableState<Boolean> = mutableStateOf(false)
){
    fun toggleSelection (){
        isSelected.value = !isSelected.value
    }
}

val Categories = listOf(
    WordCategories(R.drawable.ic_furniture, "اشیاء منزل"), //
    WordCategories(R.drawable.ic_food, "خوراکی"),//
    WordCategories(R.drawable.ic_animals, "جانوران"),//
    WordCategories(R.drawable.ic_briefcase, "مشاغل"),//
    WordCategories(R.drawable.ic_sports, "ورزشی"),//
    //WordCategories(R.drawable., "طبیعت"),
    WordCategories(R.drawable.ic_smartphone, "دیجیتال"),//
    WordCategories(R.drawable.ic_car, "خودرو"),//
    WordCategories(R.drawable.ic_music, "موسیقی"),//
    WordCategories(R.drawable.ic_education, "مدرسه"),//
    WordCategories(R.drawable.ic_makeup, "ظاهر شخصی"), //
    //WordCategories(R.drawable., "انسان"),
    //WordCategories(R.drawable., "شهر و کشور"), //earth
    //WordCategories(R.drawable., "اصطلاحات"),
    WordCategories(R.drawable.ic_magazine, "مشاهیر"), //
    WordCategories(R.drawable.ic_soccer, "فوتبال"), //
    //WordCategories(R.drawable., "کلمات من")
)


