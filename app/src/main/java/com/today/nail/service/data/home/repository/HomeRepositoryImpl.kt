package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.service.HomeService
import com.today.nail.service.ui.TopLevelViewModel
import retrofit2.http.POST
import retrofit2.http.Path

class HomeRepositoryImpl(
    private val homeService: HomeService,
): HomeRepository {
    override suspend fun getPost(page: Int, size: Int): CategoryItemResDTO =
        homeService.getPost(page = page, size = size)

    override suspend fun getPostById(postId: Long): CategoryItemResDTO =
        homeService.getPostById(postId)

}