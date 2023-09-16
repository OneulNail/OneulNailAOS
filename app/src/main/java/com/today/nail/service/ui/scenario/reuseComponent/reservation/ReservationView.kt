package com.today.nail.service.ui.scenario.reuseComponent.reservation

import android.util.Log
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.today.nail.service.data.home.dto.availableTime.AvailableTimeData
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.home.navigationGraph.HomeRoute
import com.today.nail.service.ui.theme.Color7A00C5
import com.today.nail.service.ui.theme.PurpleGrey40
import com.today.nail.service.ui.util.ToastHelper
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ReservationView(
    navHostController: NavHostController,
    viewModel: ReservationViewModel = hiltViewModel(),
    activityViewModel: TopLevelViewModel,
){
    val contentList = viewModel.contentList.collectAsState().value
    val isCalenderOpen = viewModel.isCalenderOpen.collectAsState().value
    val isTimeButtonOpen = viewModel.isTimeButtonOpen.collectAsState().value
    ReservationScreen(
        contentList = contentList,
        isCalendarOpen = isCalenderOpen,
        updateSelectedDate = {activityViewModel.updateSelectedDate(it)},
        isTimeButtonOpen = isTimeButtonOpen,
        onClickCalendarOpen = {viewModel.updateCalenderField()},
        timeButtonOpen = {viewModel.updateTimeButtonField(
            onFailed = {ToastHelper.showToast("날짜를 먼저 선택해 주세요.")}, it
        )},
        onClickBackButton = {navHostController.popBackStack()},
        onClickTimeButton = {viewModel.clickedTimeButton(it)},
        //가게 예약 정보 조회
        getReservationTime = {
            Log.d("reservation", "$it")
            viewModel.getReservationTimeById(it,activityViewModel.selectedShopId)},
        onClickReservationButton = {date, startT, endT ->
            viewModel.clickedReservationButton(
            shopId = activityViewModel.selectedShopId,
            selectedDate = activityViewModel.selectedDate.value,
            onSuccess = {},
            onFailed = {},
            startTime = startT,
            endTime = endT,
        ) },
        onClickedDisAbleButton = {ToastHelper.showToast("해당 시간은 예약이 불가능한 시간입니다. \n 다른 시간을 선택해주세요.")},
        endReservation = {navHostController.navigate(HomeRoute.Home.routes)}
    )

}



@Composable
fun ReservationScreen(
    onClickedDisAbleButton: () -> Unit,
    contentList: List<AvailableTimeData>,
    updateSelectedDate: (LocalDate?) -> Unit,
    isCalendarOpen: Boolean,
    isTimeButtonOpen: Boolean,
    onClickCalendarOpen: () -> Unit,
    timeButtonOpen: (MutableState<LocalDate?>) -> Unit,
    onClickBackButton: () -> Unit,
    onClickTimeButton: (LocalDateTime) -> Unit,
    getReservationTime: (LocalDate?) -> Unit,
    onClickReservationButton: (date: LocalDate?, startT: LocalTime?, endT: LocalTime?) -> Unit,
    endReservation: () -> Unit,
) {
    // 선택된 날짜 + 시간
    var selectedDateTime: LocalDateTime

    //선택한 날짜
    val selectedDate: MutableState<LocalDate?> = remember{ mutableStateOf(null) }
    val formattedDate = selectedDate.value?.format(DateTimeFormatter.ofPattern("MM 월 dd 일"))

    //선택된 시간
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }
    val formattedTime = selectedTime?.format(DateTimeFormatter.ofPattern("a h:mm", Locale.KOREA))

    val showDialog = remember { mutableStateOf(false) }

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
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(1.dp),
            color = Color.LightGray,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
                .height(50.dp)
                .clickable {
                    onClickCalendarOpen()
                    selectedTime = null
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(55.dp)
                    .padding(12.dp),
                tint = Color7A00C5,
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
                    color = PurpleGrey40,
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
                tint = Color7A00C5,
                imageVector = Icons.Filled.ExpandMore,
                contentDescription = null,
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(1.dp),
            color = Color.LightGray,
        )
        if(isCalendarOpen) {
            CustomCalendarView(
                onDismissRequest = {
                    onClickCalendarOpen()
                    selectedDate.value = null
                },
                onDateSelected = {
                        selectedDate.value = it
                        updateSelectedDate(selectedDate.value) },
                //선택버튼을 누르면 시간조회 시작
                endDateSelect = {
                    getReservationTime(selectedDate.value)
                    updateSelectedDate(selectedDate.value)
                    onClickCalendarOpen()
                }
            )
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .height(1.dp),
            color = Color.LightGray,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
                .height(50.dp)
                .clickable {
                    timeButtonOpen(selectedDate)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(55.dp)
                    .padding(12.dp),
                imageVector = Icons.Filled.Schedule,
                contentDescription = null,
                tint = Color7A00C5,
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
                    color = PurpleGrey40,
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
                tint = Color7A00C5,
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
        if (isTimeButtonOpen && (selectedDate.value != null)) {
            val startTimeList = contentList.map { it.startTime }
            Log.d("startTimeList", "$startTimeList")
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
                        var backgroundColor = if (isSelected) {
                            Color7A00C5
                        } else {
                            Color.White
                        }
                        val availableTimesButton = LocalTime.of(availableTimes[index].hour, availableTimes[index].minute)
                        val _availableTimesButton = availableTimesButton.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                        if (startTimeList.contains(_availableTimesButton)) {
                            backgroundColor = Color.LightGray
                        }
                        if (LocalDateTime.of(selectedDate.value, LocalTime.of(availableTime.hour, availableTime.minute)) < LocalDateTime.now()) {
                            backgroundColor = Color.LightGray
                        }
                        var textColor = if (isSelected) {
                            Color.White
                        } else {
                            Color.Black
                        }
                        if (LocalDateTime.of(selectedDate.value, LocalTime.of(availableTime.hour, availableTime.minute)) < LocalDateTime.now()) {
                            textColor = Color.Black
                        }
                        Box(modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color7A00C5,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(5.dp)
                            )
                            .height(40.dp)
                            .width(55.dp)
                            .clickable {
                                val clickedTime =
                                    LocalTime.of(availableTime.hour, availableTime.minute)
                                val _formattedTime =
                                    clickedTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))

                                // 버튼이 클릭되었을 때 선택된 시간을 처리
                                selectedDateTime = LocalDateTime.of(
                                    selectedDate.value,
                                    LocalTime.of(availableTime.hour, availableTime.minute)
                                )
                                //지난 시간
                                if (LocalDateTime.of(
                                        selectedDate.value,
                                        LocalTime.of(availableTime.hour, availableTime.minute)
                                    ) < LocalDateTime.now()
                                ) {
                                    onClickedDisAbleButton()
                                }
                                //이미 예약된 시간
                                if (startTimeList.contains(_formattedTime.toString())) {
                                    onClickedDisAbleButton()
                                } else {
                                    selectedTime = clickedTime
                                    //뷰모델로 전달해서 예약정보 전달
                                    onClickTimeButton(selectedDateTime)
                                }
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
            color = Color.LightGray,
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
                val time = selectedTime
                if (date != null && time != null) {
                    Log.d("reservationdate", "$date")
                    showDialog.value = true

                } else {
                    ToastHelper.showToast("날짜와 시간을 선택해주세요.")
                } }
        ) {
            Text(text = "예약하기", style = TextStyle(color = Color.White))
        }

        if (showDialog.value == true) {
            AlertDialogExample(
                onDismissRequest = {showDialog.value=false},
                onConfirmation = {
                    onClickReservationButton(selectedDate.value , selectedTime, selectedTime?.plusHours(1))
                    ToastHelper.showToast("예약이 완료되었습니다 !")
                    endReservation() },
                dialogTitle = "예약 내용을 다시 확인해 주세요.",
                dialogText = "날짜 : ${selectedDate.value} \n시간 : ${selectedTime}"
            )
        }
    }
}

@Composable
fun CustomCalendarView(
    onDismissRequest: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    endDateSelect: () -> Unit
) {
    Column() {
        AndroidView(
            modifier = Modifier.wrapContentSize(),
            factory = { context -> CalendarView(context) },
            update = { view ->
                val today = LocalDate.now()
                view.minDate = today.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                    val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                    //년,월,일
                    onDateSelected(selectedDate)
                }
            }
        )
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {
            TextButton(onClick = { onDismissRequest() }
            ) {
                Text(text = "닫기")
            }
            TextButton(onClick = { endDateSelect() }
            ) {
                Text(text = "선택")
            }
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
) {
    AlertDialog(
        title = {
            Text(
                text = dialogTitle,
                color = Color(0xFF7A00C5),
                fontSize = 20.sp,
                fontWeight = FontWeight(700),
            )
        },
        text = {
            Text(text = dialogText,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(400),
                    color =  PurpleGrey40,
                )
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("예")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("아니오")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewReservationScreen() {
    ReservationScreen(
        contentList = emptyList(),
        updateSelectedDate = {},
        isCalendarOpen = true,
        isTimeButtonOpen = true,
        onClickBackButton = {},
        onClickTimeButton={},
        onClickCalendarOpen = {},
        getReservationTime = {},
        timeButtonOpen = {},
        onClickReservationButton = {a,b,c ->},
        onClickedDisAbleButton = {},
        endReservation = {},
    )
}