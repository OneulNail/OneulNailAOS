package com.today.nail.service.data.onBoard.dto.register

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterResDTO(
    override val msg : String
) : BaseResponseDTO
