package com.today.nail.service.ui.scenario.home.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val AUTO_PAGE_CHANGE_DELAY = 3000L
class homeViewModel: ViewModel() {

    @OptIn(ExperimentalPagerApi::class)
    @Composable
    fun InfiniteLoopPager(
        modifier: Modifier = Modifier,
        list: List<Color> = listOf(
            Color.Red,
            Color.Yellow,
            Color.Green,
            Color.LightGray,
        ),
        clicked: () -> Unit
    ) {
        val pagerState = rememberPagerState()

        // 초기페이지 설정. 한번만 실행되기 원하니 key 는 Unit|true.
        LaunchedEffect(key1 = Unit) {
            // 최대한 많은 페이지 양쪽으로 보여주기 위함.
            var initialPage = Int.MAX_VALUE / 2

            // 초기페이지를 0으로 잡는다.
            while (initialPage % list.size != 0) {
                initialPage++
            }
            pagerState.scrollToPage(initialPage)
        }

        // 지정한 시간마다 auto scroll.
        // 유저의 스크롤해서 페이지가 바뀐경우 다시 실행시키고 싶기 때문에 key는 currentPage.
        LaunchedEffect(key1 = pagerState.currentPage) {
            launch {
                while (true) {
                    delay(AUTO_PAGE_CHANGE_DELAY)
                    // 페이지 바뀌었다고 애니메이션이 멈추면 어색하니 NonCancellable
                    withContext(NonCancellable) {
                        if (pagerState.currentPage + 1 in 0..Int.MAX_VALUE) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            }
        }

        //배너 페이저
        Box(modifier = modifier) {
            HorizontalPager(
                count = Int.MAX_VALUE,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // PageSize.Fill 상태에서 비율만 줘보기.
                state = pagerState,
            ) { index ->
                // index % (list.size) 나머지 값으로 인덱스 가져오기. 안전하게 getOrNull 처리.
                list.getOrNull(index % (list.size))?.let { color ->
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = color)
                            .clickable { clicked() }
                    )
                }
            }
        }
    }
    //카테고리 아이템 그리드
    @Composable
    fun CategoryItemGrid(
        modifier: Modifier = Modifier,
        clicked: () -> Unit
    ) {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier
                .height(150.dp)
                .fillMaxWidth()
                .padding(vertical = 30.dp)
        ) {
            items(16) { item ->
                Text(text = item.toString(),
                    modifier= Modifier
                        .clickable {
                            if (item == 0) {
                                clicked()
                            }
                        }
                        .size(100.dp)
                        .background(Color.LightGray))
            }
        }
    }

}

