package com.today.nail.service.ui.scenario.reuseComponent.reservation

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.TokenSharedPreferences
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



    private val _isCalenderOpen = MutableStateFlow(false)
    val isCalenderOpen = _isCalenderOpen.asStateFlow()

    private val _isTimeButtonOpen = MutableStateFlow(false)
    val isTimeButtonOpen = _isTimeButtonOpen.asStateFlow()

    private val _isDateSelected = MutableStateFlow(false)
    val isDateSelected = _isDateSelected.asStateFlow()

    private val _isReadyReservation = MutableStateFlow(false)
    val isReadyReservation = _isReadyReservation.asStateFlow()

    private lateinit var _selectedDateTime: LocalDateTime
//    private val _isTimeButtonClicked = MutableStateFlow(false)
//    val isTimeButtonClicked = _isTimeButtonClicked.asStateFlow()
    fun updateCalenderField(selectedDate: MutableState<LocalDate?>) {
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

    fun updateIsDateSelected() {
        this._isDateSelected.value = !this._isDateSelected.value
    }

    fun activateReservationButton() {
        this._isReadyReservation.value = true
    }
    fun deactivateReservationButton() {
        this._isReadyReservation.value = false
    }

    fun clickedTimeButton(selectedDateTime: LocalDateTime) {
        _selectedDateTime = selectedDateTime
    }

    val date:LocalDate = LocalDate.now()
    val stime = LocalTime.now()
    val eTime = LocalTime.now().plusHours(1)
    fun clickedReservationButton(
        shopId: Long,
        selectedDate: LocalDate?,
        startTime: LocalTime?,
        endTime: LocalTime?,
        requireDateTime: () -> Unit,
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

    fun getReservationTimeById(shopId: Long) {
        viewModelScope.launch {
            runCatching {
                repository.getShopReservationTimeById(shopId)

            }.onSuccess {data ->
                Log.d("reservationTime", "response : $data")

            }.onFailure {

            }
        }
    }
}