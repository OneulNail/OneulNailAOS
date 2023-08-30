package com.today.nail.service.data.home.dto.categoryItem

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class PostDTO(
    @SerialName("shop_id")
    val shopId: Long,
    @SerialName("post_id")
    val postId: Long,
    val name: String,
    @SerialName("like_cnt")
    val likeCount: Int,
    @SerialName("img_url")
    val imageUrl: String, // Assuming the image URL is a string
    val price: Int,
    val content: String
)