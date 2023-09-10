package com.today.nail.service.data.home.dto.reservationTime

import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class ShopReservationTime (
    val Id: Long,
    val dateTime: LocalDateTime,
)