package com.today.nail.service.ui.scenario.home.view.homeItemView

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
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
import com.today.nail.service.ui.scenario.home.view.homeView.BottomNavigation
import com.today.nail.service.ui.util.ToastHelper

@Composable
fun HomeItemView(
    activityViewModel : TopLevelViewModel,
    navController: NavController,
    itemViewModel: HomeItemViewModel = hiltViewModel(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }

    ) {
        it.calculateBottomPadding()
        ItemScreen(
            onClickBackButton = {navController.popBackStack()},
            onClickItem = {navController.navigate(HomeRoute.ItemDetail.routes)},
            onClickCommingSoon = {
                ToastHelper.showToast("준비 중인 기능입니다.")
            },
        )
    }

}
@Composable
fun ItemScreen(
    onClickBackButton :()-> Unit,
    onClickItem : () -> Unit,
    onClickCommingSoon: () -> Unit,

) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(1f)) {
            Row(modifier= Modifier
                .align(alignment = Alignment.CenterStart)
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
                    text = "아이템",
                    color = Color(0xFF7A00C5),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.offset(x = (-15).dp),
                )
            }
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
                .height(3.dp),
            color = Color.LightGray,
        )

        //아이템
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
                if (item % 4 < 2) {
                    Box(
                        modifier = Modifier
                            .clickable {
                                onClickItem()
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
                            onClickItem()
                        }
                    ) {
                        Column() {
                            Row(){
                                Text(
                                    text = "01",
                                    style = TextStyle(
                                        fontSize = 15.sp,
//                                        fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFFA4A4A4),
                                    )
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "네일 샵 이름",
                                    style = TextStyle(
                                        fontSize = 13.sp,
//                                        fontFamily = FontFamily(Font(R.font.roboto)),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFF000000),
                                    )
                                )
                            }

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

@Preview
@Composable
fun Preview() {
    ItemScreen(
        onClickBackButton = { /*TODO*/ },
        onClickItem = {},
        onClickCommingSoon = {},
    )
}