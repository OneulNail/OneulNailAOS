package com.today.nail.service.data.home.dto.categoryItem

import com.today.nail.service.data.BaseRequestDTO
import com.today.nail.service.data.BaseResponseDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryItemResDTO(
    @SerialName("post")
    val posts: List<PostDTO>,
)