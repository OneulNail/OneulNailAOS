package com.today.nail.service.data.onBoard.dto.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterReqDTO(
    @SerialName("phone_num")
    val mobileNo : String,
    @SerialName("password")
    val password : String,
    @SerialName("name")
    val name : String,
    @SerialName("role")
    val role : String,
)
