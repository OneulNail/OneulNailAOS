package com.today.nail.service.data.onBoard.dto.styleTaste

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserVerifyResDTO(
    override val code: String,
    override val message: String

) : BaseResponseDTO
