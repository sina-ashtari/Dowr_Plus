package xyz.sina.dowr.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


private val colorScheme = darkColorScheme(
    primary = FireEngineRed,
    background = Vanilla,
    secondary = Orange,
    onBackground = PrussianBlue,
    surfaceBright = Xanthous
)

@Composable
fun DowrTheme(
    content: @Composable () -> Unit
) {
        MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}