package com.today.nail.service.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TopLevelViewModel @Inject constructor(): ViewModel() {

    val phoneNumFieldValue = MutableStateFlow("")
    fun updatePhoneNumField(value : String) {
        if(value.length <= 11) {
            phoneNumFieldValue.value = value


        }
    }
}