package com.today.nail.service.ui.scenario.home.view.homeBannerView

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

@Composable
fun BannerScreen(navController: NavController) {
    Column {
        Button(onClick = {navController.popBackStack()}) {
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