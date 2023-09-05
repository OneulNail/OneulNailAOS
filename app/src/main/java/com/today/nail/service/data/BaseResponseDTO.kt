package com.today.nail.service.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface BaseResponseDTO {
    val isSuccess: Boolean
    val code: String
    val msg : String
}

