package com.today.nail.service.ui.scenario.home.view.homeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.today.nail.service.R
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.navigationGraph.HomeRoute
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val AUTO_PAGE_CHANGE_DELAY = 3000L
@Composable
fun HomeView(
    activityViewModel : TopLevelViewModel,
    navController: NavController,
    homeViewModel: homeViewModel = hiltViewModel(),
    ) {
    
    HomeScreen(
        onClickCategoryAll = {navController.navigate(HomeRoute.CategoryItem.routes)},
        onClickBanner = {navController.navigate(HomeRoute.Banner.routes)},
    )
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
               onClickCategoryAll: () -> Unit,
               onClickBanner: () -> Unit,
) {

    Column(modifier = Modifier.fillMaxSize()) {
        //상단 구성
        Box(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(1f)) {
            Row(modifier= Modifier
                .align(Alignment.CenterStart)
                .padding(start = 5.dp)) {
                //오늘네일 로고 이미지
                val logo = painterResource(R.drawable.home_logo_img)
                Image(
                    painter = logo,
                    contentDescription = null,
                    modifier = Modifier
                        .height(31.dp)
                        .width(87.dp)
                )
            }
            Row(modifier= Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 5.dp)
            ){
                //상단 우측 버튼1
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(24.dp)
                        .padding(3.dp),

                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                    )
                }
                //상단 우측 버튼2
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(24.dp)
                        .padding(3.dp)

                ) {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = null,
                    )
                }
                //상단 우측 버튼3
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(24.dp)
                        .padding(3.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingBag,
                        contentDescription = null,
                    )
                }
            }
        }
        //배너
        val list = listOf(
        Color.Red,
        Color.Yellow,
        Color.Green,
        Color.LightGray,
        )
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
        Box(modifier = Modifier) {
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
                            .clickable { onClickBanner() }
                    )
                }
            }
            //indicator
            Box(modifier = Modifier
                .align(Alignment.BottomEnd)) {
                PagerIndicator(
                    modifier = Modifier
                        .padding(5.dp),
                    count = list.size,
                    currentPage = pagerState.currentPage % list.size,
                    backgroundcolor = Color.LightGray,
                    widthsize = 50.dp,
                    heightsize = 30.dp,
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        //카테고리
//        CategoryItemGrid(clicked = {navController.navigate(HomeRoute.CategoryItem.routes)})
        //제품 카테고리 페이지로 이동
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth()

        ) {
            items(16) { item ->
                androidx.compose.material.Text(text = item.toString(),
                    modifier= Modifier
                        .clickable {
                            if (item == 0) {
                                //카테고리 전체보기
                                onClickCategoryAll()
                            }
                        }
                        .size(50.dp)
                        .background(Color.LightGray))
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
    }
}
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    count: Int,
    currentPage: Int,
    backgroundcolor: Color,
    widthsize: Dp,
    heightsize: Dp,
) {
    Row(modifier = modifier
        .background(color = backgroundcolor.copy(alpha = 0.3f))
        .width(widthsize)
        .height(heightsize)
    ) {
        Text(text = "   ")
        Text(text = (currentPage + 1).toString())
        Text(text = " / ")
        Text(count.toString())
        Text(text = "       ")
    }
}
@Preview
@Composable
fun Preview() {
    HomeScreen(onClickCategoryAll = { /*TODO*/ }) {

    }
}

