package com.today.nail.service.ui.scenario.home.view.homeMyPageView

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.PersonPinCircle
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.SupervisedUserCircle
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
import com.today.nail.service.ui.scenario.home.view.homeFavoriteView.FavoriteScreen
import com.today.nail.service.ui.scenario.home.view.homeView.BottomNavigation
import com.today.nail.service.ui.util.ToastHelper

@Composable
fun HomeMyPageView(activityViewModel : TopLevelViewModel,
                     navController: NavController,
                   myPageViewModel: HomeMyPageViewModel = hiltViewModel(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController)
        }

    ) {
        it.calculateBottomPadding()
        MyPageScreen(
            onClickBackButton = {navController.popBackStack()},
            onClickItem = {navController.navigate(HomeRoute.MyPage.routes)},
            onClickCommingSoon = {
                ToastHelper.showToast("준비 중인 기능입니다.")
            },
        )
    }
}

@Composable
fun MyPageScreen(
    onClickBackButton: () -> Unit,
    onClickItem: () -> Unit,
    onClickCommingSoon: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(1f)) {
            Row(
                modifier = Modifier
                    .align(alignment = Alignment.CenterStart)
                    .padding(5.dp)
                    .fillMaxWidth(0.3f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                //상단 텍스트
                Text(
                    text = "마이페이지",
                    color = Color(0xFF7A00C5),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.offset(x = 10.dp)
                )
            }
            //상단 버튼
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 15.dp)
            ) {
                //상단 우측 버튼1
                IconButton(
                    onClick = { onClickCommingSoon() },
                    modifier = Modifier
                        .size(40.dp)
                        .padding(5.dp)

                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarMonth,
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
                        imageVector = Icons.Outlined.ShoppingBag,
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

        //프로필
        Row(
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(40.dp))
            Box(contentAlignment = Center) {
                Icon(
                    modifier = Modifier
                        .padding(1.dp)
                        .width(50.dp)
                        .height(50.dp),
                    imageVector = Icons.Outlined.PersonOutline,
                    contentDescription = null,
                    tint = Color(0xFF7A00C5),
                )
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .border(3.dp, Color(0xFF7A00C5), shape = CircleShape)
                )

            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "유저",
                style = TextStyle(
                    fontSize = 20.sp,
//                    fontFamily = FontFamily(Font(R.font.roboto)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF7A00C5),
                )
            )
        }
        //포인트, 쿠폰
        Row(
            modifier = Modifier
                .height(130.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(40.dp))
            //포인트
            Box(modifier = Modifier
                .width(115.dp)
                .height(72.dp)
                .background(color = Color(0xFFF4F2FE), shape = RoundedCornerShape(size = 10.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 5.dp)
                        .offset(x = 10.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "포인트",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "320P",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.width(40.dp))
            //쿠폰
            Box(modifier = Modifier
                .width(115.dp)
                .height(72.dp)
                .background(color = Color(0xFFF4F2FE), shape = RoundedCornerShape(size = 10.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 5.dp)
                        .offset(x = 10.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "쿠폰",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "0 개",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )
                }
            }

        }

        Box(
            modifier = Modifier
                .align(CenterHorizontally)
                .width(327.dp)
                .height(100.dp)
                .background(color = Color(0xFFF4F2FE), shape = RoundedCornerShape(size = 10.dp)),

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Center),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically,
                ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "예약 내역",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "16",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF7A00C5),
                        )
                    )
                }
                Spacer(modifier = Modifier.width(26.dp))
                Divider(modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp))
                Spacer(modifier = Modifier.width(26.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "나의 리뷰",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "16",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF7A00C5),
                        )
                    )
                }
                Spacer(modifier = Modifier.width(26.dp))
                Divider(modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp))
                Spacer(modifier = Modifier.width(26.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "주문 내역",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF000000),
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "16",
                        style = TextStyle(
                            fontSize = 15.sp,
//                            fontFamily = FontFamily(Font(R.font.roboto)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF7A00C5),
                        )
                    )
                }

            }


        }

    }
}
@Preview
@Composable
fun Preview() {
    MyPageScreen(
        onClickBackButton = { /*TODO*/ },
        onClickItem = { /*TODO*/ },

        ) {

    }
}