package com.today.nail.service.data.onBoard.dto.social

import kotlinx.serialization.Serializable

@Serializable
data class UserSocialLoginRes(
    val name: String,
    val phoneNum: String,
)