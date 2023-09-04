package com.today.nail.service.data.home

data class ContentItem(
    val shopId: Long,
    val postId: Long,
    val name: String,
    val likeCount: Int,
    val imgUrl: String?,
    val price: Int,
    val category: String,
    val content: String,
)