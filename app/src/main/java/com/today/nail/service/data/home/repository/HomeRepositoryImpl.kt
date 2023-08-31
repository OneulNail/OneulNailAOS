package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.service.HomeService
import com.today.nail.service.ui.TopLevelViewModel
import retrofit2.http.POST
import retrofit2.http.Path

class HomeRepositoryImpl(
    private val homeService: HomeService,
    topLevelViewModel: TopLevelViewModel
): HomeRepository {
    override suspend fun getPost(): CategoryItemResDTO =
        homeService.getPost()

    suspend fun getPostById(postId: Long): CategoryItemResDTO =
        homeService.getPostById(postId)
}