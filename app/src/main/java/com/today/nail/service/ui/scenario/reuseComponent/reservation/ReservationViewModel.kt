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
    fun updateCalenderField() {
        this._isCalenderOpen.value = !this._isCalenderOpen.value
    }

    fun updateTimeButtonField() {
        this._isTimeButtonOpen.value = !this._isTimeButtonOpen.value
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