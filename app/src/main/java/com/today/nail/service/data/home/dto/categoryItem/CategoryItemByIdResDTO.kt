package com.today.nail.service.data.home.dto.categoryItem

import com.today.nail.service.data.home.ContentItem
import com.today.nail.service.data.home.PostResData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Blob

@Serializable
data class CategoryItemByIdResDTO (
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: ContentItem
)
