package com.today.nail.service.data.onBoard.dto.social

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserSocialLoginResDTO(
    override val msg : String,
    @SerialName("token")
    val token : String
) : BaseResponseDTO
