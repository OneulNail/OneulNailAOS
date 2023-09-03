package com.today.nail.service.data.home.dto.categoryItem

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Blob

@Serializable
data class CategoryItemByIdResDTO (
    @SerialName("shop_id")
    val shopId: Long,
    @SerialName("post_id")
    val postId: Long,
    val name: String,
    @SerialName("like_cnt")
    val likeCount: Int,
    @SerialName("img_url")
    val imageUrl: Blob, // Assuming the image URL is a string
    val price: Int,
    val content: String
)
