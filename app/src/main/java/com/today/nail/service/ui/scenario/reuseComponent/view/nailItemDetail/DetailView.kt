package com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.oneulnail.DetailViewModel
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.navigationGraph.HomeRoute
import com.today.nail.service.ui.theme.ColorBEA3EA
import com.today.nail.service.ui.util.ToastHelper

@Composable
fun DetailView(
    navController: NavController,
    detailViewModel: DetailViewModel = hiltViewModel(),
    activityViewModel: TopLevelViewModel
    ){
    ItemDetailScreen(
        onCall = {
            ToastHelper.showToast("준비중인 기능입니다.")
        },
        onInquire = {
            ToastHelper.showToast("준비중인 기능입니다.")
        }
    ) {
        navController.navigate(HomeRoute.Reservation.routes)
    }
}


//아이템 세부정보 스크린(디자인)
@Composable
fun ItemDetailScreen(
    onCall: () -> Unit,
    onInquire: () -> Unit,
    onClickReservation: () -> Unit

) {
    var numFavorites by remember { mutableStateOf(0) }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        val context = LocalContext.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Spacer(modifier = Modifier.weight(1f))

        }
        //사진은 일단 임시로 저장
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(ColorBEA3EA)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "네일샵이름",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            //하트 누르면 명수 올라가게끔!
            IconButton(
                onClick ={  numFavorites++
                },
                modifier = Modifier.size(60.dp)
            ){
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "favorite",
                    tint = if (numFavorites > 0) Color.Red else LocalContentColor.current                )
            }
        }
        Column(modifier = Modifier.padding(start = 40.dp),){
            Text(text = "샵위치", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(text = "매일 10:00~22:00", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(text = "별 표시", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(text = "가격", fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(
                text = "$numFavorites 명이 찜했어요!", fontSize = 16.sp,
                fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 20.dp)
            )

        }
        // 일단은 버튼 누르면 토스트메시지 뜨도록 설정
        Row(
            modifier = Modifier.padding(start = 20.dp, end =20.dp, bottom = 16.dp, top = 60.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),

            ){
            Button(
                shape = RoundedCornerShape(0.dp),
                onClick = onCall,
                modifier = Modifier
                    .weight(1f)
                    .border(
                        border = BorderStroke(
                            width = 5.dp,
                            color = Color.Gray
                        ),
                    )

            ){ Text(text = "전화하기",
                style = TextStyle(Color.White, 16.sp, FontWeight.Bold)) }
            Button(
                onClick = onInquire,
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .weight(1f)
                    .border(
                        border = BorderStroke(
                            width = 5.dp,
                            color = Color.Gray
                        ),
                    )
            ){ Text(text = "문의하기",
                style = TextStyle(Color.White, 16.sp, FontWeight.Bold)) }
        }

        Button(
            onClick = onClickReservation,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth()
        )
        {
            Text(text = "예약 일시",
                style = TextStyle(Color.White, 16.sp, FontWeight.Bold))

    }
}

}





@Preview(showBackground = true)
@Composable
fun PreviewItemDetailView() {
    Column {
        ItemDetailScreen(
            onCall  = {},
            onInquire  = {}
        ) {}
    }
}







