package com.today.nail.service.ui.scenario.home.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

//val navController = rememberNavController()
//
//NavHost(navController = navController,
//startDestination = "FirstScreen",
//) {
//    composable("FirstScreen") {
//        FirstScreen(navController)
//    }
//    composable("BannerScreen") {
//        BannerScreen(navController)
//    }
//    composable("CategoryItemScreen") {
//        CategoryItemScreen(navController)
//    }


@Composable
fun FirstScreen(navController: NavController) {
    Column {
        //배너 페이지로 이동
        Button(onClick = {
            //your onclick code here
            navController.navigate("BannerScreen")
        }) {
            Text(text = "banner")
        }
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
