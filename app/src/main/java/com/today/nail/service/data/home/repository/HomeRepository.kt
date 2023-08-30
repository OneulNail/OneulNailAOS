package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO

interface HomeRepository {

    suspend fun getPost() : CategoryItemResDTO
}