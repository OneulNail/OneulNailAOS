package com.today.nail.service.ui.scenario.home.view.homeNailShopView

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
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
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.navigationGraph.HomeRoute
import com.today.nail.service.ui.scenario.home.view.homeCategoryView.CategoryItemScreen
import com.today.nail.service.ui.scenario.home.view.homeView.BottomNavigation
import com.today.nail.service.ui.scenario.home.view.homeView.homeViewModel
import com.today.nail.service.ui.util.ToastHelper

@Composable
fun HomeNailShopView(activityViewModel : TopLevelViewModel,
                     navController: NavController,
                     nailSHopViewModel: HomeNailShopViewModel = hiltViewModel(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        it.calculateBottomPadding()
        NailShopScreen(
            onClickBackButton = {navController.popBackStack()},
            onClickNailShop = {navController.navigate(HomeRoute.ItemDetail.routes)},
            onClickCommingSoon = {
                ToastHelper.showToast("준비 중인 기능입니다.")
            },
            onClickCalendar = {
                ToastHelper.showToast("준비 중인 기능입니다.")
            }
        )
    }
}

@Composable
fun NailShopScreen(
    onClickBackButton :()-> Unit,
    onClickNailShop : () -> Unit,
    onClickCommingSoon : () -> Unit,
    onClickCalendar: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(1f)) {
            Row(modifier= Modifier
                .align(alignment = Alignment.BottomStart)
                .offset(x = 5.dp)
                .fillMaxWidth(0.3f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {onClickBackButton()},
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 15.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIos,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
                //상단 텍스트
                Text(
                    text = "네일샵",
                    color = Color(0xFF7A00C5),
                    fontSize = 20.sp,
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
                        .size(34.dp)
                        .padding(5.dp)
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
                        .size(34.dp)
                        .padding(5.dp)

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
                        .size(34.dp)
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
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(1.dp),
            color = Color.LightGray,
        )
        //일정 선택
        Box(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 11.dp)
            .height(46.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    modifier = Modifier
                        .padding(1.dp)
                        .width(15.dp)
                        .height(15.dp),
                    onClick = { onClickCalendar()  }
                ) {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5),
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier.clickable { onClickCalendar() },
                    text = "오늘(토)",
                    style = TextStyle(
                        fontSize = 12.sp,
//                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )
                Spacer(modifier = Modifier.width(10.dp))
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxHeight()  //fill the max height
                        .width(1.dp)
                        .padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier.clickable { onClickCalendar() },
                    text = "오후 3:00",
                    style = TextStyle(
                        fontSize = 12.sp,
//                        fontFamily = FontFamily(Font(R.font.roboto)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(1.dp),
            color = Color.LightGray,
        )

        //스타일 카테고리
        Box(modifier = Modifier
            .fillMaxWidth(1f)
            .padding(horizontal = 11.dp)
            .height(40.dp)){
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xFFA4A4A4))
                        .padding(1.dp)
                        .width(24.dp)
                        .height(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Map,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5)
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                IconButton(onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .border(width = 1.dp, color = Color(0xFFA4A4A4))
                        .padding(1.dp)
                        .width(24.dp)
                        .height(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Tune,
                        contentDescription = null,
                        tint = Color(0xFF7A00C5)
                    )
                }
                Spacer(modifier = Modifier.width(13.dp))
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxHeight()  //fill the max height
                        .width(1.dp)
                        .padding(vertical = 8.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Box(modifier = Modifier.clickable { onClickCommingSoon() }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SwapVert,
                            contentDescription = null,
                            tint = Color.LightGray,
                        )
                        Text(
                            text = "인기순",
                            color = Color.LightGray,
                        )
                    }
                }
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                ) {
                    item {
                        Box(modifier = Modifier
                            .clickable { onClickCommingSoon() }
                            .border(
                                0.1.dp,
                                color = Color.LightGray,
                                RoundedCornerShape(size = 15.dp)
                            )
                        ) {
                            Text(text = "    내 주변    ")
                        }
                    }
                    item {
                        Box(modifier = Modifier
                            .clickable { onClickCommingSoon() }
                            .border(
                                0.1.dp,
                                color = Color.LightGray,
                                RoundedCornerShape(size = 15.dp)
                            )
                        ) {
                            Text(text = "    지역    ")
                        }
                    }
                    item {
                        Box(modifier = Modifier
                            .clickable { onClickCommingSoon() }
                            .border(
                                0.1.dp,
                                color = Color.LightGray,
                                RoundedCornerShape(size = 15.dp)
                            )
                        ) {
                            Text(text = "    종류    ")
                        }
                    }
                    item {
                        Box(modifier = Modifier
                            .clickable { onClickCommingSoon() }
                            .border(
                                0.1.dp,
                                color = Color.LightGray,
                                RoundedCornerShape(size = 15.dp)
                            )
                        ) {
                            Text(text = "    가격    ")
                        }
                    }
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 16.dp),
            color = Color.LightGray,
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp)
        ) {
            items(count = 16) { item ->
                if (item % 2 == 0) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                onClickNailShop()
                            }
                            .size(150.dp)
                            .background(Color.LightGray, RoundedCornerShape(size = 15.dp)))
                    Box {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(10.dp)
                                .clickable { onClickCommingSoon() }
                        )
                    }
                }
                else{
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClickNailShop()
                        }
                    ) {
                        Column() {
                            Text(
                                text = "네일샵이름",
                                style = TextStyle(
                                    fontSize = 15.sp,
//                                    fontFamily = FontFamily(Font(R.font.roboto)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF000000),
                                )
                            )
                            Text(
                                text = "[네일 설명]",
                                style = TextStyle(
                                    fontSize = 11.sp,
//                                    fontFamily = FontFamily(Font(R.font.roboto)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFA4A4A4),
                                )
                            )
                            Text(
                                text = "￦ 69,000",
                                style = TextStyle(
                                    fontSize = 13.sp,
//                                    fontFamily = FontFamily(Font(R.font.roboto)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF000000),
                                )
                            )
                            Row() {
                                Spacer(modifier = Modifier.width(43.dp))
                                Text(
                                    text = "홍대",
                                    style = TextStyle(
                                        fontSize = 11.sp,
//                                    fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(500),
                                        color = Color(0xFFA4A4A4),
                                    )
                                )
                            }

                        }
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Preview
@Composable
fun Preview() {
    NailShopScreen(
        onClickBackButton = { /*TODO*/ },
        onClickCommingSoon = {},
        onClickNailShop = {},
    )
}
