package com.today.nail.service.data.home

data class PostRes(
    val isSuccess: Boolean,
    val code: String,
    val message: String,
    val result: PostResData
)