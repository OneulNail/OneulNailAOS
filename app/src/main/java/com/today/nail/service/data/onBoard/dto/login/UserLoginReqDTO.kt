package com.today.nail.service.data.onBoard.dto.login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class UserLoginReqDTO(
    @SerialName("phone_num")
    val mobileNo : String,
    @SerialName("password")
    val password : String,
)
