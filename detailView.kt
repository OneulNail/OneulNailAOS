package com.example.oneulnail
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.oneulnail.ui.theme.OneulNailTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("ComposableDestinationInComposeScope")
    override fun onCreate(savedInstanceState: Bundle?):Unit {
        super.onCreate(savedInstanceState)
        setContent {
            val detailViewModel = viewModel<detailViewModel>()
            OneulNailTheme {
                Surface(color = Color.White) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "ItemdetailScreen",) {
                        composable("ItemdetailScreen") {
                            ItemdetailScreen(navController,detailViewModel)
                            composable("reservation") {
                                ReservationScreen(navController,detailViewModel)
                            }
                        }
                    }
                }
            }
        }
    }


//아이템 세부정보 스크린
@Composable
fun ItemdetailScreen(navController:NavController,detailViewModel: detailViewModel =viewModel() ) {
    var numFavorites by remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize()){
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
        Image(
            painter = painterResource(R.drawable.hypeboy),
            contentDescription = "null",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
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
        // 일단은 버튼 누르면 예약완료 토스트메시지 뜨도록 설정
        Row(
            modifier = Modifier.padding(start = 20.dp, end =20.dp, bottom = 16.dp, top = 60.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),

            ){
            Button(
                shape = RoundedCornerShape(0.dp),
                onClick ={android.widget.Toast.makeText(context, "준비중",
                    android.widget.Toast.LENGTH_SHORT).show()},
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
                onClick ={android.widget.Toast.makeText(context, "준비중",
                    android.widget.Toast.LENGTH_SHORT).show()},
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
            onClick ={
                navController.navigate("ReservationScreen")
            },
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




@Composable
fun ReservationScreen(navController: NavController, detailViewModel: detailViewModel =viewModel()) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(top = 40.dp)
        ) {
            Button(
                modifier = Modifier.size(50.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = { navController.navigateUp() }
            ) {
                Text("뒤로")
            }

            Text(
                modifier = Modifier.padding(start = 10.dp, top = 8.dp),
                fontSize = 18.sp,
                text = "예약"
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(top = 10.dp)
        ) {
            Text(
                text = "오늘(일)",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 8.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 10.dp)
                .height(280.dp)
                .fillMaxWidth()
        ) {
            // 여기에 CalendarView를 코틀린 컴포즈에서 구현하는 방법 추가
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(top = 40.dp)
        ) {
            Text(
                text = "시간선택",
                modifier = Modifier.padding(start = 18.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            // 여기에 시간 선택 버튼들을 코틀린 컴포즈에서 구현하는 방법 추가
        }

        Button(
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 40.dp)
                .fillMaxWidth(),

            onClick = { android.widget.Toast.makeText(context, "예약 완료",
                android.widget.Toast.LENGTH_SHORT).show()}
        ) {
            Text("예약 문의하기")
        }
    }
}
}


@Preview
@Composable
fun Preview1() {
MainActivity()
}




