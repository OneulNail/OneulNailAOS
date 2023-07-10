package com.today.nail.service.data.onBoard.dto.verify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserStyleTasteReqDTO(
    @SerialName("style1")
    val styleFirst : String,
    @SerialName("style2")
    val styleSecond : String,
    @SerialName("style3")
    val styleThird : String,
)
