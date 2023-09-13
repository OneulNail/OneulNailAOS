package com.today.nail.service.data.home.dto.reservation

import android.content.Context
import com.today.nail.service.data.TokenSharedPreferences
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import kotlin.coroutines.coroutineContext

@Serializable
data class ReservationReqDTO(
    val shopId : Long,
    val date : LocalDateTime,
)
