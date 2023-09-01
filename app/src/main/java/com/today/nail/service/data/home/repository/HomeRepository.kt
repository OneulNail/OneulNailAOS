package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO

interface HomeRepository {

    suspend fun getPost(page: Int, size: Int,) : CategoryItemResDTO

    suspend fun getPostById(postId: Long) : CategoryItemResDTO
}