package com.today.nail.service.data.onBoard.dto.verify

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.Serializable

@Serializable
data class UserStyleTasteResDTO(
    override val msg: String
) : BaseResponseDTO
