package xyz.sina.dowr.tutorial.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.sina.dowr.tutorial.data.TutorialModel
import xyz.sina.dowr.ui.theme.DowrTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialScreen() {

    val pages = listOf(
        TutorialModel.FirstPage,
        TutorialModel.SecondPage,
        TutorialModel.ThirdPage,
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button
                PagerIndicator(pageSize = pages.size , currentPage = pagerState.currentPage)
            }
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OnBoardScreenPewView() {
    DowrTheme {
        TutorialScreen()
    }
}