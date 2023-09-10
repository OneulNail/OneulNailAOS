package com.today.nail.service.data.home.dto.categoryItem

import com.today.nail.service.data.BaseRequestDTO
import com.today.nail.service.data.BaseResponseDTO
import com.today.nail.service.data.home.PostResData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryItemResDTO(
    val code: String,
    val message: String,
    val data: PostResData
)