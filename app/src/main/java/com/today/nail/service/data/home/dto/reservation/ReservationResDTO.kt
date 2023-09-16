package com.today.nail.service.data.home.dto.reservation

import com.google.gson.JsonObject
import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.Serializable

@Serializable
data class ReservationResDTO(
    override val code: String,
    override val message : String,
    val data: JsonObject
) : BaseResponseDTO