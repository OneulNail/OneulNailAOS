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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.today.nail.service.R
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.navigationGraph.HomeRoute
import com.today.nail.service.ui.util.ToastHelper
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

    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        it.calculateBottomPadding()
        HomeScreen(
            onClickCategoryAll = { navController.navigate(HomeRoute.CategoryItem.routes) },
            onClickBanner = {
//                navController.navigate(HomeRoute.Banner.routes)
                            },
            onClickCategory = {},
            onClickCommingSoon = {
                ToastHelper.showToast("준비 중인 기능입니다.")
            }
        )
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
               onClickCategoryAll: () -> Unit,
               onClickBanner: () -> Unit,
               onClickCategory: (Int) -> Unit,
               onClickCommingSoon: () -> Unit,
) {
    val scrollState = rememberScrollState()
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
                    onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .size(24.dp)
                        .padding(3.dp),

                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
                //상단 우측 버튼2
                IconButton(
                    onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .size(24.dp)
                        .padding(3.dp)

                ) {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
                //상단 우측 버튼3
                IconButton(
                    onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .size(24.dp)
                        .padding(3.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingBag,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
            }
        }
        Column(Modifier.verticalScroll(scrollState)) {
            //배너
            val list = listOf(
                R.drawable.home_banner_1,
                R.drawable.home_banner_2,
                R.drawable.home_banner_3,
                R.drawable.home_banner_4,
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
                    list.getOrNull(index % (list.size))?.let { imageResId ->
                        Image(
                            painter = painterResource(id = imageResId),
                            contentDescription = "banner",
                            contentScale = ContentScale.Crop, // 이미지 비율 유지 및 크롭
                            modifier = Modifier.fillMaxSize() // 이미지 전체 크기로 표시
                        )
                    }
                }
                //indicator
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                ) {
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

            val categories = listOf(
                CategoryItem(R.drawable.category_img_1, "이달의 특가"),
                CategoryItem(R.drawable.category_img_2, "원컬러"),
                CategoryItem(R.drawable.category_img_3, "시럽"),
                CategoryItem(R.drawable.category_img_4, "그라데이션"),
                CategoryItem(R.drawable.category_img_5, "프렌치"),
                CategoryItem(R.drawable.category_img_6, "글리터"),
                CategoryItem(R.drawable.category_img_7, "마블"),
                CategoryItem(R.drawable.category_img_8, "파우더"),
                CategoryItem(R.drawable.category_img_9, "파츠"),
                CategoryItem(R.drawable.category_img_10, "캐릭터"),
                CategoryItem(R.drawable.category_img_11, "테라조"),
                CategoryItem(R.drawable.category_img_12, "치크"),
                CategoryItem(R.drawable.category_img_13, "체크"),
                //14번은 추후 이미지 수정
                CategoryItem(R.drawable.category_img_15, "페디"),
                CategoryItem(R.drawable.category_img_15, "네일스티커"),
            )

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
                item {
                    Box(modifier = Modifier.clickable { onClickCategoryAll() }

                    ) {
                        Box(modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .background(
                                color = Color(0xFFE1DCF0),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(1.dp)
                                    .width(25.dp)
                                    .height(25.dp)
                            )
                        }

                        Text(
                            text = "전체보기",
                            modifier = Modifier.align(Alignment.BottomCenter),
                            style = TextStyle(
                                fontSize = 10.sp,
//                                    fontFamily = FontFamily(Font(R.font.inter)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                }
                items(categories.size) { index ->
                    val categoryItem = categories[index]
                    Box(
                        modifier = Modifier
                            .clickable {
                                onClickCategory(index)
                            }

                    ) {

                        Box(modifier = Modifier
                            .size(50.dp)
                        ) {
                            Image(
                                painter = painterResource(id = categoryItem.imageResId),
                                contentDescription = null,
//                                contentScale = ContentScale.Crop, // 이미지 비율 유지 및 크롭
                                modifier = Modifier.fillMaxSize() // 이미지 전체 크기로 표시
                            )
                        }
                        Text(
                            text = categoryItem.text,
                            modifier = Modifier.align(Alignment.BottomCenter),
                            style = TextStyle(
                                fontSize = 10.sp,
//                                    fontFamily = FontFamily(Font(R.font.inter)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Box(Modifier.fillMaxWidth()) {
                Row() {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "6월 특가, 이달의 아트",
                        fontSize = 18.sp,
                    )
                }


                Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { onClickCommingSoon() }
                ) {
                    Text(text = "자세히 보기",
                        fontSize = 10.sp,)
                    Icon(imageVector = Icons.Filled.ArrowForwardIos,
                         contentDescription = null,
                         modifier = Modifier
                             .padding(1.dp)
                             .width(16.dp)
                             .height(19.dp)
                    )
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,) {
                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    ) {

                    Box(modifier = Modifier
                        .clickable {}
                        .width(80.dp)
                        .height(80.dp)
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier
                        .clickable { }
                        .width(80.dp)
                        .height(80.dp)
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier
                        .clickable { }
                        .width(80.dp)
                        .height(80.dp)
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,

                ) {
                    Box(modifier = Modifier
                        .clickable { }
                        .width(80.dp)
                        .height(80.dp)
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier
                        .clickable { }
                        .width(80.dp)
                        .height(80.dp)
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier
                        .clickable { }
                        .width(80.dp)
                        .height(80.dp)
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(size = 15.dp)
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))

            Column(Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,) {
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(28.dp))
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,) {
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(28.dp))
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))
            Box(Modifier.fillMaxWidth()) {
                Row() {
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "추천 네일",
                        fontSize = 18.sp,
                    )
                }


                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable { onClickCommingSoon() }
                ) {
                    Text(text = "자세히 보기",
                        fontSize = 10.sp,)
                    Icon(imageVector = Icons.Filled.ArrowForwardIos,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(1.dp)
                            .width(16.dp)
                            .height(19.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            //추천네일상단
            LazyHorizontalGrid(
                rows = GridCells.Fixed(1),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)

            ) {
                items(8) { item ->
                    Box(
                        modifier = Modifier
                            .clickable { onClickCommingSoon() }
                            .size(80.dp)
                            .background(
                                color = LightGray,
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(50.dp))

            //추천네일 하단부
            Column(Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,) {
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(28.dp))
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,) {
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(28.dp))
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Row(Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,) {
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(28.dp))
                    Box(
                        modifier = Modifier
                            .clickable { }
                            .width(150.dp)
                            .height(150.dp)
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    )
                }
                Spacer(modifier = Modifier.height(100.dp))

            }
        }


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
        .background(color = backgroundcolor.copy(alpha = 0.3f), shape = RoundedCornerShape(10.dp))
        .width(widthsize)
        .height(heightsize),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(text = (currentPage + 1).toString())
        Text(text = " / ")
        Text(count.toString())
    }
}
sealed class BottomNavItem(
    val imageVector: ImageVector, val screenRoute: String
) {
    object Home : BottomNavItem(Icons.Filled.Home, HomeRoute.Home.routes)
    object NailShop : BottomNavItem(Icons.Filled.Storefront, HomeRoute.NailShop.routes)
    object Nail : BottomNavItem(Icons.Filled.ViewCozy, HomeRoute.CategoryItem.routes)
    object Item : BottomNavItem(Icons.Filled.Category, HomeRoute.Item.routes)
    object Favorite : BottomNavItem(Icons.Filled.Favorite, HomeRoute.Favorite.routes)
    object Mypage : BottomNavItem(Icons.Filled.Person, HomeRoute.MyPage.routes)
}
@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf<BottomNavItem>(
        BottomNavItem.Home,
        BottomNavItem.NailShop,
        BottomNavItem.Nail,
        BottomNavItem.Item,
        BottomNavItem.Favorite,
        BottomNavItem.Mypage,
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF3F414E),
        modifier = Modifier.height(100.dp),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = item.imageVector, contentDescription = null)
                },
                selectedContentColor = MaterialTheme.colors.primary,
                unselectedContentColor = Gray,
                selected = currentRoute == item.screenRoute,
                alwaysShowLabel = false,
                onClick = {

                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                modifier = Modifier
                    .height(80.dp)

            )
        }
    }
}
@Preview
@Composable
fun Preview() {
    HomeScreen(
        onClickCategoryAll = { /*TODO*/ },
        onClickBanner = {},
        onClickCommingSoon = {},
        onClickCategory = {},
    )
}

