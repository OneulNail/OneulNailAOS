package com.today.nail.service.data.onBoard.dto.login

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserLoginRes(
    val msg: String,
    val accessToken: String,
    val refreshToken: String,
)