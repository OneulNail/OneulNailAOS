package com.today.nail.service.ui.scenario.reuseComponent.reservation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.home.repository.HomeRepository
import com.today.nail.service.data.home.repository.HomeRepositoryImpl
import com.today.nail.service.ui.TopLevelViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor() : ViewModel() {
    val repository : HomeRepository = HomeRepositoryImpl(ServiceConnector.makeHomeService())


    private val _selectedDate = MutableStateFlow<LocalDate?>(null)
    val selectedDate: StateFlow<LocalDate?> = _selectedDate

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

    fun clickedReservationButton(
        shopId: Long,
        selectedDate: LocalDateTime,
        requireDateTime: () -> Unit,
        onSuccess: () -> Unit,
        onFailed: () -> Unit,
    ) {
        viewModelScope.launch {
        kotlin.runCatching {
            repository.postUserReservation(shopId, selectedDate)
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