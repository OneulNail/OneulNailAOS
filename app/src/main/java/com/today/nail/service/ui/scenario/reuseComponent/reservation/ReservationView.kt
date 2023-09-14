package com.today.nail.service.ui.scenario.reuseComponent.reservation

import android.content.Context
import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.blue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.today.nail.service.data.TokenSharedPreferences
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.theme.Color7A00C5
import com.today.nail.service.ui.util.ToastHelper
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.time.Duration.Companion.minutes

@Composable
fun ReservationView(
    navHostController: NavHostController,
    viewModel: ReservationViewModel = hiltViewModel(),
    activityViewModel: TopLevelViewModel,
){
    val selectedDate = activityViewModel.selectedDate.collectAsState().value
    val isCalenderOpen = viewModel.isCalenderOpen.collectAsState().value
    val isTimeButtonOpen = viewModel.isTimeButtonOpen.collectAsState().value
    val isDateSelected = viewModel.isDateSelected.collectAsState().value
    val isReadyReservation = viewModel.isReadyReservation.collectAsState().value
    var isButtonClicked by remember { mutableStateOf(true) }
    ReservationScreen(
        selectedDate,
        isCalendarOpen = isCalenderOpen,
        updateSelectedDate = {activityViewModel.updateSelectedDate(it)},
        isTimeButtonOpen = isTimeButtonOpen,
        isDateSelected = isDateSelected,
        isReadyReservation = isReadyReservation,
        activateReservationButton = {viewModel.activateReservationButton()},
        deactivateReservationButton = {viewModel.deactivateReservationButton()},
        onClickCalendarOpen = {viewModel.updateCalenderField(it)},
        timeButtonOpen = {viewModel.updateTimeButtonField(
            onFailed = {ToastHelper.showToast("날짜를 먼저 선택해 주세요.")}, it
        )},
        onClickBackButton = {navHostController.popBackStack()},
        onClickTimeButton = {viewModel.clickedTimeButton(it)},
        //가게 예약 정보 조회
        getReservationTime = {viewModel.getReservationTimeById(activityViewModel.selectedShopId)},
        onClickReservationButton = {date, startT, endT ->
            viewModel.clickedReservationButton(
            shopId = activityViewModel.selectedShopId,
            selectedDate = activityViewModel.selectedDate.value,
            requireDateTime = {},
            onSuccess = {},
            onFailed = {},
            startTime = startT,
            endTime = endT,
        )}
    )

}



@Composable
fun ReservationScreen(
    selectedDateOfTopLevelViewModel: LocalDate?,
    updateSelectedDate: (LocalDate?) -> Unit,
    isCalendarOpen: Boolean,
    isTimeButtonOpen: Boolean,
    isDateSelected: Boolean,
    isReadyReservation: Boolean,
    activateReservationButton: () -> Unit,
    deactivateReservationButton: () -> Unit,
    onClickCalendarOpen: (MutableState<LocalDate?>) -> Unit,
    timeButtonOpen: (MutableState<LocalDate?>) -> Unit,
    onClickBackButton: () -> Unit,
    onClickTimeButton: (LocalDateTime) -> Unit,
    getReservationTime: () -> Unit,
    onClickReservationButton: (date: LocalDate?, startT: LocalTime?, endT: LocalTime?) -> Unit,
) {
    // 선택된 날짜 + 시간
    var selectedDateTime: LocalDateTime

    // 날짜 선택 창
    val isDialogOpen = remember{ mutableStateOf(false) }

    //선택한 날짜
    val selectedDate: MutableState<LocalDate?> = remember{ mutableStateOf(null) }
    val formattedDate = selectedDate.value?.format(DateTimeFormatter.ofPattern("MM 월 dd 일"))

    //선택된 시간
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }
    val formattedTime = selectedTime?.format(DateTimeFormatter.ofPattern("a h:mm", Locale.KOREA))
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
                .padding(start = 15.dp, end = 15.dp)
                .height(50.dp)
                .clickable {
                    onClickCalendarOpen(selectedDate)
                    selectedTime = null
                },
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                modifier = Modifier
                    .size(55.dp)
                    .padding(12.dp),
                imageVector = Icons.Filled.CalendarMonth,
                contentDescription = null,
            )
            if (formattedDate != null) {
                Text(
                    text= formattedDate,
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 0.dp),
                    style = TextStyle(fontSize = 20.sp,
                        fontWeight = FontWeight(700)),)
            }
            else {
                Text(
                    text = "날짜 선택",
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 0.dp),
                    style = TextStyle(fontSize = 20.sp,
                        fontWeight = FontWeight(700)),

                    )
            }
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp),
                imageVector = Icons.Filled.ExpandMore,
                contentDescription = null,
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(1.dp),
            color = Color.Black,
        )
        if(isCalendarOpen) {
            CustomCalendarView(
                _selectedDate = selectedDate.value,
                onDismissRequest = {
                    onClickCalendarOpen(selectedDate)
                    selectedDate.value = null
                },
                onDateSelected = {selectedDate.value = it
                                 updateSelectedDate(selectedDate.value)},

                //선택버튼을 누르면 시간조회 시작
                endDateSelect = {
                    updateSelectedDate(selectedDate.value)
                    onClickCalendarOpen(selectedDate)
                    getReservationTime()
                }
            )
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
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
                .height(50.dp)
                .clickable { timeButtonOpen(selectedDate) },
            verticalAlignment = Alignment.CenterVertically

        ) {
            Icon(
                modifier = Modifier
                    .size(55.dp)
                    .padding(12.dp),
                imageVector = Icons.Filled.Schedule,
                contentDescription = null,
            )
            if (formattedTime != null) {
                Text(
                    text= formattedTime.toString(),
                    modifier = Modifier
                        .width(250.dp)
                        .padding(start = 0.dp),
                    style = TextStyle(fontSize = 20.sp,
                        fontWeight = FontWeight(700)),)
            }
            else {
                Text(
                    text = "시간 선택",
                    modifier = Modifier
                        .width(250.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700)
                    )
                )
            }
            Icon(
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp),
                imageVector = Icons.Filled.ExpandMore,
                contentDescription = null,
            )

        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(1.dp),
            color = Color.Black,
        )

        if (isTimeButtonOpen && (selectedDate.value != null))
//        else if(true)
        {
            Column() {
                val availableTimes: List<AvailableTime> = (11..20).flatMap { hour ->
                    listOf(AvailableTime(hour, 0))
                }

                Spacer(modifier = Modifier.height(20.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(5),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .height(100.dp)
                        .fillMaxWidth(),
                ) {
                    items(availableTimes.size) {index ->
                        val availableTime = availableTimes[index]

                        val isSelected = selectedTime?.hour == availableTime.hour && selectedTime?.minute == availableTime.minute

                        val backgroundColor = if (isSelected) {
                            Color7A00C5
                        } else {
                            Color.LightGray
                        }
                        val textColor = if (isSelected) {
                            Color.White
                        } else {
                            Color.Black
                        }
                        Box(modifier = Modifier
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .height(40.dp)
                            .width(55.dp)
                            .clickable {
                                // 버튼이 클릭되었을 때 선택된 시간을 처리
                                selectedDateTime = LocalDateTime.of(
                                    selectedDate.value,
                                    LocalTime.of(availableTime.hour, availableTime.minute)
                                )
                                selectedTime =
                                    LocalTime.of(availableTime.hour, availableTime.minute)
                                //뷰모델로 전달해서 예약정보 전달
                                onClickTimeButton(selectedDateTime)

                                Log.d("selectedDateTime", "$selectedDateTime")

                            }
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = "${availableTime.hour}:${availableTime.minute.toString().padStart(2, '0')}",
                                style = TextStyle(color = textColor)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
                .height(1.dp),
            color = Color.Black,
        )
//        val reservationButtonBackgroundColor = if (isReadyReservation) {
//            Color7A00C5
//        } else {
//            Color.LightGray
//        }
//        val reservationButtonTextColor = if (isReadyReservation) {
//            Color.White
//        } else {
//            Color.Black
//        }
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
                val time = selectedTime
                if (date != null && time != null) {
                    val selectedDateAndTime = "날짜: ${date}, 시간: ${time}"
                    ToastHelper.showToast(selectedDateAndTime)
                    Log.d("reservationdate", "$date")
                    onClickReservationButton(date , time, time.plusHours(1))
                } else {
                    ToastHelper.showToast("날짜와 시간을 선택해주세요.")
                } }
        ) {
            Text(text = "예약하기", style = TextStyle(color = Color.White))
        }
    }
}

@Composable
fun CustomCalendarView(
    _selectedDate: LocalDate?,
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    endDateSelect: () -> Unit
) {
    Column() {

        AndroidView(
            modifier = Modifier.wrapContentSize(),
            factory = {context -> CalendarView(context) },
            update = {view ->
                val today = LocalDate.now()

                // 오늘 이전의 날짜를 선택할 수 없도록 설정
                view.minDate = today.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
//                    onDateSelected(today)
                view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                    val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                    //년,월,일
                    onDateSelected(selectedDate)
                }

            }
        )
        Row (
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 16.dp, end = 16.dp)
        ){
            TextButton(onClick = {
                onDismissRequest()
            }
            )
            {
                Text(text = "닫기")
            }

            TextButton(onClick = {
//                onDateSelected(selectedDate.value)
                endDateSelect()
            }
            ) {
                Text(text = "선택")
            }
        }
    }
}
@Composable
fun TimeSelectionView(
    selectedDate: LocalDate,
    availableTimes: List<AvailableTime>,
    onTimeSelected: (LocalDateTime) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // 2열로 시간대 버튼을 표시하기 위해 2개의 Row를 사용합니다.
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            availableTimes.take(5).forEach { time ->
                val localDateTime = LocalDateTime.of(selectedDate, LocalTime.of(time.hour, time.minute))
                TimeSelectionButton(
                    time = localDateTime,
                    onTimeSelected = onTimeSelected
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            availableTimes.drop(5).forEach { time ->
                val localDateTime = LocalDateTime.of(selectedDate, LocalTime.of(time.hour, time.minute))
                TimeSelectionButton(
                    time = localDateTime,
                    onTimeSelected = onTimeSelected
                )
            }
        }
    }
}


@Composable
fun TimeSelectionButton(
    time: LocalDateTime,
    onTimeSelected: (LocalDateTime) -> Unit
) {
    Button(
        onClick = { onTimeSelected(time) },
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = time.format(DateTimeFormatter.ofPattern("HH:mm")))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReservationScreen() {
    ReservationScreen(
        selectedDateOfTopLevelViewModel = null,
        updateSelectedDate = {},
        isCalendarOpen = true,
        isTimeButtonOpen = true,
        isDateSelected = true,
        isReadyReservation = true,
        activateReservationButton = {},
        deactivateReservationButton = {},
        onClickBackButton = {},
        onClickTimeButton={},
        onClickCalendarOpen = {},
        getReservationTime = {},
        timeButtonOpen = {},
        onClickReservationButton = {a,b,c ->}
    )


}