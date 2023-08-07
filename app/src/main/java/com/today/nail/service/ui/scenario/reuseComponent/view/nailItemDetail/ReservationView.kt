package com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail

import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.oneulnail.DetailViewModel
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.util.ToastHelper
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ReservationView(
    navHostController: NavHostController,
    detailViewModel: DetailViewModel = hiltViewModel(),
    activityViewModel: TopLevelViewModel
){
    ReservationScreen(
        onReservation = {
            ToastHelper.showToast("준비중인 기능입니다.")
        },
        onBack = {navHostController.navigateUp()},

    )
}



@Composable
fun ReservationScreen(
    onReservation: () -> Unit,
    onBack: () -> Unit,

    ) {
    //날짜 선택하기
    val isDialogOpen = remember{ mutableStateOf(false) }
    val selectedDate: MutableState<LocalDate?> = remember{ mutableStateOf(null) }
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
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
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
                onClick = onBack,
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
            Button(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    isDialogOpen.value = !isDialogOpen.value
                }
            ) {
                Text("날짜 선택하기")
            }
            if(isDialogOpen.value) {
                MyDatePickerDialog(onDateSelected = {
                    Log.d("TAG", "MyDatePicker: 선택한 날짜: ${it.toString()}")
                    selectedDate.value = it
                }, onDismissRequest = {
                    Log.d("TAG", "MyDatePicker: 닫아짐: ")
                    isDialogOpen.value = false
                })
            }

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

            onClick = onReservation
        ) {
            Text("예약 문의하기")
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyDatePickerDialog(
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit
)
{ val selectedDate = remember { mutableStateOf(LocalDate.now()) }

    Dialog(onDismissRequest = { onDismissRequest()
    }, properties = DialogProperties()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {
            //min sdk 26부터 가능
            Text(text = selectedDate.value.format(DateTimeFormatter.ofPattern("MMM d, YYYY"))

            )
            CustomCalendarView(onDateSelected = {
                selectedDate.value = it
            })
            Spacer(modifier = Modifier.size(8.dp))

            Row (
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp, end = 16.dp)
            ){
                TextButton(onClick = onDismissRequest) {
                    Text(text = "닫기")
                }

                TextButton(onClick = {
                    onDateSelected(selectedDate.value)
                    onDismissRequest()
                }
                ) {
                    Text(text = "선택")
                }
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomCalendarView(
    onDateSelected: (LocalDate) -> Unit
) {
    AndroidView(
        modifier = Modifier.wrapContentSize(),
        factory = {context -> CalendarView(context) },
        update = {view ->
            view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                onDateSelected(
                    LocalDate.of(year, month + 1, dayOfMonth)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewReservationScreen() {
   ReservationScreen(
       onReservation ={},
       onBack ={},
   )
    MyDatePickerDialog(
    onDateSelected ={},
    onDismissRequest ={}
    )
    CustomCalendarView(onDateSelected ={}
    )

}
