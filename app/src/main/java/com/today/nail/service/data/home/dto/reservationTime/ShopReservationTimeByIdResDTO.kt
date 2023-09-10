package com.today.nail.service.data.home.dto.reservationTime

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.Serializable

@Serializable
data class ShopReservationTimeByIdResDTO (
    override val code: String,
    override val message: String,
    val data: ShopReservationTime
): BaseResponseDTO