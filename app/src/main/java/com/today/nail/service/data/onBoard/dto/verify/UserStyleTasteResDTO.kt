package com.today.nail.service.data.onBoard.dto.verify

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.Serializable

@Serializable
data class UserStyleTasteResDTO(
    override val isSuccess: Boolean, override val code: String, override val message: String
) : BaseResponseDTO
