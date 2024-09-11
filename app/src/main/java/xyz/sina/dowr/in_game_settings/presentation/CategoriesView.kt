package xyz.sina.dowr.in_game_settings.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.sina.dowr.in_game_settings.data.WordCategories

@Composable
fun CategoriesView(wordCategories: WordCategories, position : Int ,onItemClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(vertical = 15.dp, horizontal = 5.dp)
            .clickable(enabled = true,
                onClickLabel = null,
                role = null,
                onClick = { wordCategories.toggleSelection(); onItemClick(position) })
    ) {
        Image(
            modifier = Modifier
                .aspectRatio(1f)
                .clip(CircleShape)
                .size(100.dp)
                .border(
                    width = if (wordCategories.isSelected.value) 3.dp else 0.dp,
                    color = if (wordCategories.isSelected.value) Color.Red else Color.White,
                    shape = CircleShape
                ),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = wordCategories.image),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = wordCategories.name,
            color = if (wordCategories.isSelected.value) Color.Red else Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}