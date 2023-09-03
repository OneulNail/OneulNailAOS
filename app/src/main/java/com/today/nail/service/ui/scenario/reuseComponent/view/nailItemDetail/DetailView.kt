package com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.StarBorderPurple500
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.oneulnail.DetailViewModel
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.navigationGraph.HomeRoute
import com.today.nail.service.ui.scenario.home.view.homeView.BottomNavigation
import com.today.nail.service.ui.theme.Color7A00C5
import com.today.nail.service.ui.theme.ColorA4A4A4
import com.today.nail.service.ui.theme.ColorBEA3EA
import com.today.nail.service.ui.util.ToastHelper
import java.sql.Blob

@Composable
fun DetailView(
    navController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel(),
    activityViewModel: TopLevelViewModel,

){
    //선택된 포스트 id
    val postId = activityViewModel.selectedPostId.collectAsState().value

    ItemDetailScreen(
        onCall = {
            ToastHelper.showToast("준비중인 기능입니다.")
        },
        onInquire = {
            ToastHelper.showToast("준비중인 기능입니다.")
        },
        onClickReservation = {
            navController.navigate(HomeRoute.Reservation.routes)
        },
        onClickBackButton = {navController.popBackStack()},
        selectedPostId = postId,
        getPostInfo = {
            detailViewModel.getPost(
                onSuccess = {
                    detailViewModel.getShop(
                        onSuccess = {},
                        onFail = {},
                        it,
                    )
                    ToastHelper.showToast("게시물 조회")
                },
                onFail = {
                    ToastHelper.showToast("게시물 조회 실패")
                },
                postId = it,
            )
        },
        shopId = detailViewModel.currentShopId,
        shopName = detailViewModel.shopName,
        likeCount = detailViewModel.currentLikeCount,
        price = detailViewModel.currentPrice,
        content = detailViewModel.currentContent,
    )
}

//"shop_id": Long
//"post_id": Long
//"name": String
//"like_count": int
//"img_url": BLOB
//"price": int
//"content": String


//아이템 세부정보 스크린(디자인)
@Composable
fun ItemDetailScreen(
    onCall: () -> Unit,
    onInquire: () -> Unit,
    onClickBackButton :()-> Unit,
    onClickReservation: () -> Unit,
    selectedPostId: Long,
    getPostInfo: (Long) -> Unit,
    shopId: Long,
    shopName: String,
    likeCount : Int,
//    imgUrl: Blob?,
    price: Int,
    content: String,
) {
    getPostInfo(selectedPostId)
    var numFavorites by remember { mutableStateOf(0) }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())){
        val context = LocalContext.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(top = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier
                .height(81.dp)
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
                        text = "네일",
                        color = Color(0xFF7A00C5),
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier.offset(x = (-15).dp)
                    )
                }
                Row(modifier= Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 8.dp)

                ){
                    //1. 검색버튼
                    androidx.compose.material.IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(34.dp)
                            .padding(5.dp)
                    ) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = Color7A00C5

                        )
                    }
                    //2. 달력버튼
                    androidx.compose.material.IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(34.dp)
                            .padding(5.dp)

                    ) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = null,
                            tint = Color7A00C5

                        )
                    }
                    //3. 장바구니버튼
                    androidx.compose.material.IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(34.dp)
                            .padding(5.dp)
                    ) {
                        androidx.compose.material.Icon(
                            imageVector = Icons.Filled.ShoppingBag,
                            contentDescription = null,
                            tint = Color7A00C5

                        )
                    }
                }
            }

        }
        //사진은 일단 임시로 저장
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color.LightGray)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = shopName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                ),
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
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription = "favorite",
                    tint = if (numFavorites > 0) Color.Red else LocalContentColor.current                )
            }
        }
        Column(modifier = Modifier.padding(start = 25.dp),){

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material.IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 8.dp)
                ) {
                    androidx.compose.material.Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
                Text(
                    text = "샵 위치",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFA4A4A4),
                    )
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material.IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 8.dp)
                ) {
                    androidx.compose.material.Icon(
                        imageVector = Icons.Filled.Schedule,
                        contentDescription = null,
                    )
                }
                Text(
                    text = "매일 10:00-22:00, 월요일 휴무",
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFFA4A4A4),
                    )
                )

            }

            //리뷰는 안하기로 했으니.. 없애도 될 것 같죠..
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ){
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.StarBorderPurple500,
                    contentDescription = null,
                )
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.StarBorderPurple500,
                    contentDescription = null,
                )
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.StarBorderPurple500,
                    contentDescription = null,
                )
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.StarBorderPurple500,
                    contentDescription = null,
                )
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.StarBorderPurple500,
                    contentDescription = null,
                )
                Text(
                    text = "리뷰보기",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(600),
                    modifier = Modifier.padding(top = 5.dp)
                )


            }
            Text(
                text = "$price 원",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .padding(1.dp)
                        .width(16.dp)
                        .height(16.dp)
                )
                Text(
                    text = "$numFavorites 명이 찜했어요!",
                    style = TextStyle(
                        fontSize = 13.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )
            }
        }
        // 일단은 버튼 누르면 토스트메시지 뜨도록 설정
        Row(
            modifier = Modifier.padding(start = 20.dp, end =20.dp, bottom = 16.dp, top = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
        ){
            Button(
                shape = RoundedCornerShape(0.dp),
                onClick = onCall,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Gray
                        ),
                    )
            ){ Text(text = "전화하기",
                style = TextStyle(Color.Black, 16.sp, FontWeight.Bold)) }
            Button(
                shape = RoundedCornerShape(0.dp),
                onClick = onInquire,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .weight(1f)
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.Gray
                        ),
                    )
            ){ Text(text = "문의하기",
                style = TextStyle(Color.Black, 16.sp, FontWeight.Bold)) }
        }

        Button(
            onClick = onClickReservation,
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorBEA3EA
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
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
            onCall = {},
            onInquire = {},
            onClickReservation ={},
            onClickBackButton = {},
            getPostInfo = {},
            selectedPostId = 1,
            shopId = 1,
            shopName  = "네일샵이름",
            content = "",
            likeCount = 1,
            price = 60000,
        )
    }
}