package com.today.nail.service.data.onBoard.dto.login

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param role only can be "ROLE_USRE" or "ROLE_ADMIN"
 */
@Serializable
data class UserLoginResDTO(
    override val msg : String,
    @SerialName("token")
    val token : String,
) : BaseResponseDTO


