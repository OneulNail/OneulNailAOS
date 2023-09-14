package com.today.nail.service.data.home.dto.reservation

import android.content.Context
import com.today.nail.service.data.TokenSharedPreferences
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.coroutines.coroutineContext

@Serializable
data class ReservationReqDTO(
    val shopId : Long,
    val date : String,
    val startTime: String,
    val endTime: String,
)
