package com.today.nail.service.data.onBoard.dto.login

import com.google.gson.JsonObject
import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param role only can be "ROLE_USRE" or "ROLE_ADMIN"
 */
@Serializable
data class UserLoginResDTO(
    override val code: String,
    override val message : String,
    //JsonObject 형태로 받아옴.. 토큰에 접근하려면..?
    val result: JsonObject
) : BaseResponseDTO


