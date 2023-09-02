package com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail

import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import android.widget.CalendarView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.RectangleShape
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
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.theme.Color7A00C5
import com.today.nail.service.ui.theme.ColorCAC9FF
import com.today.nail.service.ui.util.ToastHelper
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ReservationView(
    navHostController: NavHostController,
    detailViewModel: ReservationViewModel = hiltViewModel(),
    activityViewModel: TopLevelViewModel,
){
    var isButtonClicked by remember { mutableStateOf(true) }
    ReservationScreen(
        onReservation = {
            },
        onBack = {navHostController.navigateUp()},
        onClickBackButton = {navHostController.popBackStack()},
        onTimeButton={
            isButtonClicked = !isButtonClicked
        },
        isButtonClicked = isButtonClicked,
        onClickDateClick={}
    )

}



@Composable
fun ReservationScreen(
    onReservation: () -> Unit,
    onBack: () -> Unit,
    onClickBackButton: () -> Unit,
    onTimeButton: () -> Unit,
    isButtonClicked: Boolean,
    onClickDateClick:()-> Unit


) {
    // 선택한 날짜와 시간을 저장하기 위한 변수들
    val isDialogOpen = remember{ mutableStateOf(false) }
    val selectedDate: MutableState<LocalDate?> = remember{ mutableStateOf(null) }
    val selectedTime = remember { mutableStateOf<String?>(null) }
    var selectedTimeButton by remember { mutableStateOf(-1) }
    val time = selectedTimeButton

    val firstRowButtons = listOf("14:00", "15:00", "16:00", "17:00")
    val secondRowButtons = listOf("18:00", "19:00", "20:00", "21:00")

    val selectedButtons = remember { mutableStateListOf<Int>() }

    // 첫 번째 행 버튼에 대한 배경 색상 리스트
    val firstRowButtonColors = remember { mutableStateListOf<Color>() }
    firstRowButtonColors.addAll(List(firstRowButtons.size) { index ->
        if (index == selectedTimeButton) {
            ColorCAC9FF
        } else {
            Color.LightGray
        }
    })

    // 두 번째 행 버튼에 대한 배경 색상 리스트
    val secondRowButtonColors = remember { mutableStateListOf<Color>() }
    secondRowButtonColors.addAll(List(secondRowButtons.size) { index ->
        if (index == selectedTimeButton - firstRowButtons.size) {
            ColorCAC9FF
        } else {
            Color.LightGray
        }
    })



    val formattedDate = selectedDate.value?.format(DateTimeFormatter.ofPattern("MMM d, YYYY"))


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
                    onClick = { onClickBackButton() },
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBackIos,
                        contentDescription = null,
                        tint = Color7A00C5
                    )
                }
                androidx.compose.material.Text(
                    text = "예약",
                    color = Color(0xFF7A00C5),
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                )
            }
            Row(modifier= Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 8.dp)

            ){
                //1. 검색버튼
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color7A00C5

                    )
                }
                //2. 달력버튼
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(5.dp)

                ) {
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = null,
                        tint = Color7A00C5

                    )
                }
                //3. 장바구니 버튼
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(34.dp)
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingBag,
                        contentDescription = null,
                        tint = Color7A00C5

                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(1.dp),
            color = Color.DarkGray,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 10.dp)
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(55.dp)
                    .padding(end = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = null,
                )
            }
            Text(
                text = "오늘(일)",
                modifier = Modifier
                    .width(250.dp)
                    .padding(start = 0.dp),
                style = TextStyle(fontSize = 20.sp,
                    fontWeight = FontWeight(700))
            )
            IconButton(
                onClick = {isDialogOpen.value = !isDialogOpen.value
                },
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 2.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ExpandMore,
                    contentDescription = null,
                )
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
        //날짜 선택하는 박스
        Row(
            modifier = Modifier
                .padding(top = 5.dp, start = 15.dp, end = 15.dp)
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color.Gray
                    ),
                )
                .height(250.dp)
                .fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .fillMaxSize(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = {isDialogOpen.value = !isDialogOpen.value}
            ) {
                if (formattedDate != null) {
                    Text(
                        text= formattedDate,
                        color = Color.Black,
                        style = TextStyle(fontSize = 17.sp,
                            fontWeight = FontWeight(700)))
                }
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
                .padding(10.dp)
                .height(50.dp)
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(55.dp)
                    .padding(end = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Schedule,
                    contentDescription = null,
                )
            }
            Text(
                text = "시간선택",
                modifier = Modifier
                    .width(250.dp),
                style = TextStyle(fontSize = 20.sp,
                    fontWeight = FontWeight(700))
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ExpandMore,
                    contentDescription = null,
                )
            }

        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(1.dp),
            color = Color.Black,
        )

        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 18.dp)
                .height(40.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            firstRowButtons.forEachIndexed { index,buttonLabel ->
                Button(
                    onClick = {
                        if (selectedButtons.contains(index)) {
                            selectedButtons.remove(index)
                        } else {
                            selectedButtons.add(index)
                        }
                        onTimeButton()
                    },
                    shape = RectangleShape,
                    modifier = Modifier
                        .width(80.dp)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = if (selectedButtons.contains(index)) ColorCAC9FF else Color.LightGray// 버튼 클릭 상태에 따라 배경 색상 설정
                    )
                ) {
                    Text(
                        text = buttonLabel,
                        modifier = Modifier
                            .fillMaxWidth(),
                        style = TextStyle(fontSize = 12.sp,
                            fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp, top = 18.dp)
                .height(40.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            secondRowButtons.forEachIndexed { index, buttonLabel ->
                Button(
                    onClick = {
                        val adjustedIndex = firstRowButtons.size + index
                        if (selectedButtons.contains(adjustedIndex)) {
                            selectedButtons.remove(adjustedIndex)
                        } else {
                            selectedButtons.add(adjustedIndex)
                        }
                        onTimeButton()
                    },
                    shape = RectangleShape,
                    modifier = Modifier
                        .width(80.dp)
                        .height(45.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = if (selectedButtons.contains(firstRowButtons.size + index)) ColorCAC9FF else Color.LightGray // 버튼 클릭 상태에 따라 배경 색상 설정
                    )
                ) {
                    Text(
                        text = buttonLabel,
                        modifier = Modifier.fillMaxSize(),
                        style = TextStyle(fontSize = 12.sp,
                            fontWeight = FontWeight.Bold)
                    )
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.End
        ) {
            androidx.compose.material.IconButton(
                onClick = {},
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 2.dp, top = 10.dp),

                ) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.CheckBoxOutlineBlank,
                    contentDescription = null,
                    tint=ColorCAC9FF,

                    )
            }
            Text(text = "선택",
                fontSize = 11.sp,
                modifier = Modifier.padding(top=10.dp),
                fontWeight = FontWeight.Medium)

            androidx.compose.material.IconButton(
                onClick = {},
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 2.dp, start = 5.dp, top = 10.dp)
            ) {
                androidx.compose.material.Icon(
                    imageVector = Icons.Filled.CheckBoxOutlineBlank,
                    contentDescription = null,
                    tint= Color.LightGray

                )
            }
            Text(text = "불가",
                modifier = Modifier.padding(top=10.dp),
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium)



        }

        Divider(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 40.dp)
                .height(1.dp),
            color = Color.Black,
        )

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color7A00C5
            ),
            modifier = Modifier
                .padding(10.dp)
                .padding(top = 20.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth(),

            onClick = {
                val date = selectedDate.value
                val time = selectedTime.value

                if (date != null && time != null) {
                    val selectedDateAndTime = "날짜: ${date}, 시간: ${time}"
                    ToastHelper.showToast(selectedDateAndTime)
                } else {
                    ToastHelper.showToast("날짜와 시간을 선택해주세요.")
                } }
        ) {
            Text("예약하기")
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
    val formattedDate = selectedDate.value.format(DateTimeFormatter.ofPattern("MMM d, YYYY"))



    Dialog(onDismissRequest = { onDismissRequest() }, properties = DialogProperties()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(size = 16.dp)
                )
        ) {
            //min sdk 26부터 가능
            Text( text = formattedDate,
                modifier = Modifier.padding(16.dp)
            )
            CustomCalendarView(onDateSelected = {
                selectedDate.value = it
            })
            Spacer(modifier = Modifier.size(3.dp))

            Row (
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 16.dp, end = 16.dp)
            ){
                TextButton(onClick = {
                    onDismissRequest()}
                )
                {
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
        onClickBackButton = {},
        onTimeButton={},
        isButtonClicked = false,
        onClickDateClick = {}
    )


}