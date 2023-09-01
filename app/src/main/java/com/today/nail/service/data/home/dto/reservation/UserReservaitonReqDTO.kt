package com.today.nail.service.data.home.dto.reservation

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class UserReservaitonReqDTO(
    @SerialName("shopId")
    val shopId : Long,
    @SerialName("date")
    val date: LocalDateTime
)