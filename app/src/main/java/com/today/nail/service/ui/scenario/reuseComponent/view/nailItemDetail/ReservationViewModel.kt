package com.today.nail.service.ui.scenario.reuseComponent.view.nailItemDetail

import androidx.lifecycle.ViewModel
import com.today.nail.service.ui.TopLevelViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ReservationViewModel @Inject constructor() : ViewModel() {
    val selectedDate = MutableStateFlow("날짜를 선택해주세요")
}