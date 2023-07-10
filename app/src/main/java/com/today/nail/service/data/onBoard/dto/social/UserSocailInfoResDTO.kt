package com.today.nail.service.data.onBoard.dto.social

import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.Serializable

@Serializable
data class UserSocialInfoResDTO(
    override val msg : String,
) : BaseResponseDTO
