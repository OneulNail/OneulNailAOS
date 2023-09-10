package com.today.nail.service.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface BaseResponseDTO {
    val code: String
    val message : String
}

