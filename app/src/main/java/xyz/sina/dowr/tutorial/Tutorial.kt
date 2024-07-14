package xyz.sina.dowr.tutorial

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import xyz.sina.dowr.R
import xyz.sina.dowr.navigation.Screens
import xyz.sina.dowr.ui.theme.Caramel
import xyz.sina.dowr.ui.theme.Orange
import xyz.sina.dowr.utils.Title
import xyz.sina.dowr.utils.Txts

var changeScreen : Boolean = true

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tutorial(navController: NavHostController){



    val texts = listOf(Txts.firstPageTutorial,
        Txts.secondPageTutorial,
        Txts.thirdPageTutorial)

    val title = listOf(
        Title.firstPageTitle,
        Title.secondPageTitle,
        Title.thirdPageTitle
    )
    // TODO: please add the game icon and punishment icon
    val icon = listOf(R.drawable.ic_rule ,R.drawable.ic_rule, R.drawable.ic_rule)
    
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Caramel)){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(24.dp) ){

            val pagerState = rememberPagerState (pageCount = {3})

            HorizontalPager(state = pagerState, beyondBoundsPageCount = pagerState.pageCount, modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .background(color = Orange) , verticalAlignment = Alignment.CenterVertically) {
                Column (Modifier.padding(16.dp)){

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.End), horizontalArrangement = Arrangement.Absolute.Right){
                        Text(title[it], style = TextStyle(textDirection = TextDirection.Rtl ), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Icon(painter = painterResource(id = icon[it]), contentDescription = null)
                    }
                    Text(texts[it], style = TextStyle(textDirection = TextDirection.Rtl), fontSize = 16.sp)

                }
            }
            Column(Modifier.align(Alignment.BottomCenter)) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .alpha(if (pagerState.currentPage == pagerState.pageCount - 1) 1f else 0f)
                ){
                    var checked by remember{ mutableStateOf(false) }

                    Text(modifier = Modifier.absolutePadding(right = 20.dp),text = Txts.changeStartUpScreen, style = TextStyle(textDirection = TextDirection.Rtl))
                    Checkbox(checked = checked, onCheckedChange = {

                        checked = it
                        changeScreen = it

                    })

                }
                Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){
                    Column {
                        Row(
                            Modifier
                                .padding(bottom = 8.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center){
                            repeat(pagerState.pageCount){

                                val color = if(pagerState.currentPage == it ) Color.Black else Color.White
                                Box(modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(16.dp))

                            }
                        }
                        IconButton(modifier = Modifier.alpha(if(pagerState.currentPage != pagerState.pageCount -1) 0f else 1f ), onClick = {navController.navigate(Screens.ScreenMain.route)}){
                            Icon(Icons.Default.CheckCircle, contentDescription = null)
                        }
                    }
                }
            }
        }
    }

}

