package xyz.sina.dowr.tutorial.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import xyz.sina.dowr.tutorial.data.TutorialModel
import xyz.sina.dowr.ui.theme.DowrTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialScreen(
    onFinished: () -> Unit
) {

    val pages = listOf(
        TutorialModel.FirstPage,
        TutorialModel.SecondPage,
        TutorialModel.ThirdPage,
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })

    val buttonState by remember {
        derivedStateOf {
            when(pagerState.currentPage){
                0 -> listOf("", "بعدی")
                1 -> listOf("قبلی", "بعدی")
                2 -> listOf("قبلی", "بزن بریم")
                else -> listOf("", "")
            }
        }
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart) { if (buttonState[0].isNotEmpty()) {
                    ButtonUI (
                        text = buttonState[0]
                        ) {
                        scope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    }
                }
                }
                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center) {
                    PagerIndicator(pageSize = pages.size, currentPage = pagerState.currentPage)
                }

                Box(modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.CenterEnd) {
                    ButtonUI(
                        text = buttonState[1]
                        ) {
                        scope.launch {
                            if (pagerState.currentPage < pages.size - 1) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            } else {
                                onFinished()
                            }
                        }
                    }
                }
            }
        },
        content = { innerPadding ->
            Column(modifier = Modifier.fillMaxHeight().padding(innerPadding), verticalArrangement = Arrangement.Center) {
                HorizontalPager(state = pagerState) { index ->
                    TutorialGraphUI(tutorialModel = pages[index])
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TutorialPreView() {
    DowrTheme {
        TutorialScreen{}
    }
}