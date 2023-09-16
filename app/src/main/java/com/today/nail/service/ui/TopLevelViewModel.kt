package com.today.nail.service.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TopLevelViewModel @Inject constructor(): ViewModel() {
    val _postId: Long = 1

    var selectedPostId: Long = 1
    var selectedShopId: Long = 1
    val phoneNumFieldValue = MutableStateFlow("")
    fun updatePhoneNumField(value : String) {
        if(value.length <= 11) {
            phoneNumFieldValue.value = value
        }
    }

    /**
     * 선택된 post의 postId 와 shopId 저장
     */
    fun updateSelectedPost(postId: Long, shopId: Long) {
        selectedPostId = postId
        selectedShopId = shopId
    }

    fun getShopInfo(shopId: Long) {

    }

    private val _selectedDate = MutableStateFlow<LocalDate?>(null)
    val selectedDate: StateFlow<LocalDate?> = _selectedDate

    fun updateSelectedDate(date: LocalDate?) {
        _selectedDate.value = date
    }
}