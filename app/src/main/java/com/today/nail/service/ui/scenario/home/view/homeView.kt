package com.today.nail.service.ui.scenario.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.today.nail.service.R


@Composable
fun FirstScreen(navController: NavController,homeViewModel: homeViewModel = viewModel()) {
    Column {
        //상단 구성
        HomeScreenTop()
        //배너
        homeViewModel.InfiniteLoopPager(
            clicked = {
                //your onclick code here
                navController.navigate("BannerScreen")
            })
        //배너 페이지로 이동
        Button(onClick = {
            //your onclick code here
            navController.navigate("BannerScreen")
        }) {
            Text(text = "banner")
        }
        //카테고리
        homeViewModel.CategoryItemGrid(clicked = {navController.navigate("CategoryItemScreen")})
        //제품 카테고리 페이지로 이동
        Button(onClick = {
            //your onclick code here
            navController.navigate("CategoryItemScreen")
        }) {
            Text(text = "product")
        }
    }
}

@Composable
fun BannerScreen(navController: NavController) {
    Column {
        Button(onClick = {navController.navigate("FirstScreen")}) {
            Text(text = "뒤로가기")
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.LightGray,
        )
        //클릭하면 달력으로
        Button(onClick = { /*TODO*/ }) {
            Text(text = "오늘")
        }
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "내 주변")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "지역")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "종류")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "가격")
            }
        }
        Row {
            //클릭하면 상품 상세 페이지로
            Button (onClick = { /*TODO*/ }) {
                Text(text = "사진")
            }
            Text(modifier = Modifier.weight(1f),
                text = "Detail")
        }

    }
}

@Composable
fun CategoryItemScreen(navController: NavController) {
    Column {
        Button(onClick = {navController.navigate("FirstScreen")}) {
            Text(text = "뒤로가기")
        }
        Row {
            Text(text = "네일")
            //검색
            Button(onClick = { /*TODO*/ }) {

            }
            //달력
            Button(onClick = { /*TODO*/ }) {

            }
            //장바구니?
            Button(onClick = { /*TODO*/ }) {

            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.LightGray,
        )
        //스타일 카테고리
        Row {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "전체")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "스타일")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "스타일")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "스타일")
            }
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = "인기순")
        }
        //네일 사진
        Button(onClick = { /*TODO*/ }) {
            Text(text = "네일 사진")
        }
    }
}
@Composable
fun HomeScreenTop(modifier: Modifier = Modifier) {
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
                modifier = modifier
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
                modifier = modifier
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
                modifier = modifier
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
}

@Preview
@Composable
fun Preview(homeViewModel: homeViewModel = viewModel()) {
    HomeScreenTop()
    homeViewModel.InfiniteLoopPager(clicked = {})
    homeViewModel.CategoryItemGrid(clicked = {})
}

