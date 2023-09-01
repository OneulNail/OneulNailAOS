package com.today.nail.service.data.home.dto.reservation

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserReservationResDTO(
    override val msg : String,
): BaseResponseDTO