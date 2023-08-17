package com.today.nail.service.ui.scenario.home.view.homeFavoriteView

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.navigationGraph.HomeRoute
import com.today.nail.service.ui.scenario.home.view.homeView.BottomNavigation
import com.today.nail.service.ui.util.ToastHelper
import kotlinx.coroutines.launch

@Composable
fun HomeFavoriteView(activityViewModel : TopLevelViewModel,
                     navController: NavController,
                     favoriteViewModel: HomeFavoriteViewModel = hiltViewModel(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }

    ) {
        it.calculateBottomPadding()
        FavoriteScreen(
            onClickBackButton = {navController.popBackStack()},
            onClickItem = {navController.navigate(HomeRoute.ItemDetail.routes)},
            onClickCommingSoon = {
                ToastHelper.showToast("준비 중인 기능입니다.")
            },
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FavoriteScreen(
    onClickBackButton: () -> Unit,
    onClickItem: () -> Unit,
    onClickCommingSoon: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize(),
    ) {
        Box(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(1f)) {
            Row(modifier= Modifier
                .align(alignment = Alignment.BottomStart)
                .padding(5.dp)
                .fillMaxWidth(0.3f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                //상단 텍스트
                Text(
                    text = "찜",
                    color = Color(0xFF7A00C5),
                    fontSize = 25.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.offset(x = (-15).dp)
                )
            }
            //상단 버튼
            Row(modifier= Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 15.dp)
            ){
                //상단 우측 버튼1
                IconButton(
                    onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .size(40.dp)
                        .padding(5.dp)

                ) {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
                //상단 우측 버튼2
                IconButton(
                    onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .size(40.dp)
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingBag,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
            }
        }
        FavoriteTapLayout()

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FavoriteTapLayout() {
    val pages = listOf("네일", "네일샵", "아이템")
    val pagerState = rememberPagerState()

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),

    ) {
        TabRow(
            modifier = Modifier.height(64.dp),
            backgroundColor = Color(0xFFFFFFF),
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                )
            }
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(text = title)},
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.scrollToPage(index)
                        }
                    }
                )
                
            }
        }
        HorizontalPager(
            count = pages.size,
            state = pagerState,
        ) {page ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 30.dp)
            ) {
                items(count = 18) { item ->
                    if (item % 6 < 3) {
                        Box(
                            modifier = Modifier
                                .clickable {

                                }
                                .size(100.dp)
                                .background(Color.LightGray, RoundedCornerShape(size = 15.dp)))
                        Box {
                            Icon(
                                imageVector = Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(10.dp)
                                    .clickable {}
                            )
                        }
                    }
                    else{
                        Box(modifier = Modifier

                            .fillMaxWidth()
                            .clickable {
//                                onClickItem()
                            }
                        ) {
                            Column() {
                                Text(
                                    text = "네일 샵 이름",
                                    style = TextStyle(
                                        fontSize = 13.sp,
//                                        fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "설명",
                                    style = TextStyle(
                                        fontSize = 13.sp,
//                                    fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFA4A4A4),
                                    )
                                )
                                Box(modifier = Modifier.align(Alignment.End)) {
                                    Text(
                                        text = "가격",
                                        style = TextStyle(
                                            fontSize = 13.sp,
//                                    fontFamily = FontFamily(Font(R.font.roboto)),
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFF000000),
                                        )
                                    )
                                }



                            }
                        }
                    }

                }
            }
        }
    }

}


@Preview
@Composable
fun Preview() {
    FavoriteScreen(
        onClickBackButton = { /*TODO*/ },
        onClickItem = { /*TODO*/ },

    ) {
        
    }
}
