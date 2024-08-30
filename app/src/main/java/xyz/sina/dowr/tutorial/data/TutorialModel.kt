package xyz.sina.dowr.tutorial.data

import androidx.annotation.DrawableRes
import xyz.sina.dowr.R

sealed class TutorialModel(
    @DrawableRes val image : Int,
    val title : String,
    val description : String
) {
    data object FirstPage : TutorialModel(
        image = R.drawable.about_game,
        title = "دور",
        description = "دور پلاس یک بازی سرگرم کننده جمعی و باحاله که حسابی خوشحالتون میکنه.\n" +
                "توی این بازی هرتیمی که خلاقیت بیشتر و حافظه بهتری رو برای گفتن کلمات داشته باشه برنده میشه.\n" +
                "بریم با هم آموزش بازی رو یادبگیریم."
    )
    data object SecondPage : TutorialModel(
        image = R.drawable.in_game_law,
        title = "آمورش بازی",
        description = "این بازی برخلاف پانتومیم که نباید صحبت کنید و کلمه مورد نظر رو به هم تیمی خودتون برسونید، باید با صحبت کردن کلمه مورد نظر رو بگید؛ البته همین صحبت کردن هم نیازمند قوانینی هست که براتون میگم:\n" +
                "1. نمی\u200Cتونید خود کلمه رو بگید\n" +
                "2. از ترجمه به زبان های دیگه نمی\u200Cتونید استفاده کنید\n" +
                "3. اشاره به چیزی که مستقیما به کلمه مرتبط باشه ممنوعه، مثلا اگه کلمه \"تلفن\" باشه نمی\u200Cتونید مستقیما نشونش بدید"
    )
    data object ThirdPage : TutorialModel(
        // punishment image please
        image = R.drawable.in_game_law,
        title = "مجازات",
        description = "در آخر هم برای این که به بازی\u200Cتون هیجان بیشتری بدید می\u200Cتونید یه مجازات تعیین کنید و گروه بازنده رو خوشحال کنید."
    )

}