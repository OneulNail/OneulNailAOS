package com.today.nail.service.ui.scenario.reuseComponent.reservation

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.TokenSharedPreferences
import com.today.nail.service.data.home.dto.availableTime.AvailableTimeData
import com.today.nail.service.data.home.dto.availableTime.AvailableTimeResDTO
import com.today.nail.service.data.home.repository.HomeRepository
import com.today.nail.service.data.home.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor(
    private val application: Application
) : ViewModel() {
    val repository : HomeRepository = HomeRepositoryImpl(ServiceConnector.makeHomeService())
    private val applicationContext: Context = application.applicationContext

    private val _contentList = MutableStateFlow<List<AvailableTimeData>>(emptyList())
    val contentList = _contentList.asStateFlow()

    private val _isCalenderOpen = MutableStateFlow(false)
    val isCalenderOpen = _isCalenderOpen.asStateFlow()

    private val _isTimeButtonOpen = MutableStateFlow(false)
    val isTimeButtonOpen = _isTimeButtonOpen.asStateFlow()

    private lateinit var _selectedDateTime: LocalDateTime
    fun updateCalenderField() {
        this._isCalenderOpen.value = !this._isCalenderOpen.value
    }

    fun updateTimeButtonField(onFailed: () -> Unit, selectedDate: MutableState<LocalDate?>) {
        if (selectedDate.value == null) {
            onFailed()
        }
        else {
            this._isTimeButtonOpen.value = !this._isTimeButtonOpen.value
        }
    }
    fun clickedTimeButton(selectedDateTime: LocalDateTime) {
        _selectedDateTime = selectedDateTime
    }
    fun clickedReservationButton(
        shopId: Long,
        selectedDate: LocalDate?,
        startTime: LocalTime?,
        endTime: LocalTime?,
        onSuccess: () -> Unit,
        onFailed: () -> Unit,
    ) {
        val prefs = TokenSharedPreferences(applicationContext)

        viewModelScope.launch {
        kotlin.runCatching {
                Log.d("reservationDate_vm", "$selectedDate")
                repository.postUserReservation(accessToken = prefs.accessToken, shopId = shopId, date = selectedDate.toString(), startTime = startTime.toString(), endTime=endTime.toString())
            }.onSuccess { response ->
                Log.d("postReservation success", "$response")

                onSuccess()
            }.onFailure { response ->
                Log.d("postReservation failed", "$response")
                onFailed()
            }
    }
    }

    fun getReservationTimeById(date: LocalDate?, shopId: Long) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getAvailableTime(shopId, date)

            }.onFailure {res->
                Log.d("availableTime", "response : $res")
            }.onSuccess {
                res ->
                Log.d("availableTime", "response : $res")
                _contentList.value = res.data.content
            }
        }
    }
}