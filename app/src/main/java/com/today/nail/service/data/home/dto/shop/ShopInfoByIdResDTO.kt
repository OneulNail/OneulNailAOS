package com.today.nail.service.data.home.dto.shop

import com.today.nail.service.data.BaseResponseDTO
import com.today.nail.service.data.home.PostResData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Blob

@Serializable
data class ShopInfoByIdResDTO (
    override val code: String,
    override val message: String,
    val data: Shop
): BaseResponseDTO