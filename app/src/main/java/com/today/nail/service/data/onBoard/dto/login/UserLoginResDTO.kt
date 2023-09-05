package com.today.nail.service.data.onBoard.dto.login

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param role only can be "ROLE_USRE" or "ROLE_ADMIN"
 */
@Serializable
data class UserLoginResDTO(
    override val isSuccess: Boolean,
    override val msg : String,
    override val code: String,
    val result: List<UserLoginRes>
) : BaseResponseDTO


