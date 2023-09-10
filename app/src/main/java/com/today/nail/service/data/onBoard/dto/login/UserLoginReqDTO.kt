package com.today.nail.service.data.onBoard.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class UserLoginReqDTO(
    val email : String,
    val password : String,
)
