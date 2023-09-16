package com.today.nail.service.data.home.dto.reservationTime

import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class ShopReservationTime (
    val reservationId: Long,
    val date: LocalDateTime,
)