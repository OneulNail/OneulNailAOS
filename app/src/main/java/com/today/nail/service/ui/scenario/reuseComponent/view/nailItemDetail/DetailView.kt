package com.example.oneulnail
import android.icu.util.Calendar
import com.example.oneulnail.ui.theme.OneulNailTheme
import androidx.lifecycle.viewmodel.compose.viewModel
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val detailViewModel = viewModel<DetailViewModel>()
            OneulNailTheme {
                Surface(color = Color.White) {

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "ItemdetailScreen",) {
                        composable("ItemdetailScreen") {
                            ItemdetailScreen(navController = navController)
                        }
                        composable("ReservationScreen") {
                                ReservationScreen(navController = navController)
                            }
                        }
                    }
                }

            }
        }
    }


//아이템 세부정보 스크린
@Composable
fun ItemdetailScreen(navController: NavController, detailViewModel: DetailViewModel = viewModel()) {
    val navController = rememberNavController()
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
fun ReservationScreen(navController: NavController, detailViewModel: DetailViewModel =viewModel()) {
    val context = LocalContext.current
    val firstRowButtons = listOf("14:00", "15:00", "16:00", "17:00")
    val secondRowButtons = listOf("18:00", "19:00", "20:00", "21:00")
    //버튼 누르면 색깔 변하는 설정
    val isButtonClicked = remember { mutableStateOf(false) }
    val buttonColor = if (isButtonClicked.value) {
        Color.Green
    } else {
        Color.Gray
    }
    fun getToday() = Calendar.getInstance().let {
        val year = it.get(Calendar.YEAR)
        val month = it.get(Calendar.MONTH) + 1
        val day = it.get(Calendar.DAY_OF_MONTH)
        "${year}년 ${month}월 ${day}일 "
    }



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
                .padding(10.dp, bottom = 8.dp)
                .padding(top = 50.dp)
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
                .height(45.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            firstRowButtons.forEach { buttonLabel ->
                Button(
                    onClick = {

                    },
                    shape = RectangleShape,
                    modifier = Modifier
                        .width(80.dp)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = buttonColor
                    )
                ) {
                    Text(
                        text = buttonLabel,
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(fontSize = 12.sp,
                            fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp)
                .height(45.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            secondRowButtons.forEach { buttonLabel ->
                Button(
                    onClick = { /* TODO: Handle button click */ },
                    shape = RectangleShape,
                    modifier = Modifier
                        .width(80.dp)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = buttonColor
                    )
                ) {
                    Text(
                        text = buttonLabel,
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 20.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth(),

            onClick = { android.widget.Toast.makeText(context, "예약 완료",
                android.widget.Toast.LENGTH_SHORT).show()}
        ) {
            Text("예약 문의하기")
        }
    }
}



@Preview
@Composable
fun PreviewItemdetail() {
    val counterState = remember { mutableStateOf(0) }
    val navController = rememberNavController()

    OneulNailTheme {
        NavHost(navController = navController, startDestination = "ItemdetailScreen") {
            composable("ItemdetailScreen") {
                ItemdetailScreen(navController = navController, detailViewModel = DetailViewModel())
            }
            composable("ReservationScreen") {
                ReservationScreen(navController = navController, detailViewModel = DetailViewModel())
            }
        }
    }
}

@Preview
@Composable
fun PreviewReservationScreen() {
    val navController = rememberNavController()
    OneulNailTheme {
        ReservationScreen(navController = navController, detailViewModel = DetailViewModel())
    }
}






