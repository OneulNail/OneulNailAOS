package com.today.nail.service.data.home.service

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import retrofit2.http.GET

interface HomeService {

    /**
     *  게시글 전체 조회
     */

    @GET("post")
    suspend fun getPost() : CategoryItemResDTO
}