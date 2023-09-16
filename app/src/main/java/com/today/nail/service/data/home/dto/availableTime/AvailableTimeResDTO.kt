package com.today.nail.service.data.home.dto.availableTime

import com.google.gson.JsonObject
import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.Serializable

@Serializable
data class AvailableTimeResDTO(
    val code: String,
    val message : String,
    val data: AvailableTime
)