package com.today.nail.service.data.onBoard.dto.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterReqDTO(
    val email: String,
    val phoneNum : String,
    val password : String,
    val name : String,
    val role : String,
)
