package com.today.nail.service.data.home.dto.reservation

import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class ReservationReqDTO(
    val shopId : Long,
    val date : LocalDateTime,
)