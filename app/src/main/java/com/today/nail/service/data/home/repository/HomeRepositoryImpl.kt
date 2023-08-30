package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.service.HomeService

class HomeRepositoryImpl(
    private val homeService: HomeService
): HomeRepository {
    override suspend fun getPost(): CategoryItemResDTO =
        homeService.
        getPost()

}