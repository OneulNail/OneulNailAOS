package com.today.nail.service.data.home.service

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {

    /**
     *  게시글 전체 조회
     */
    @GET("post")
    suspend fun getPost() : CategoryItemResDTO

    /**
     *  게시글 단일 조회
     */
    @GET("post/{postid}")
    suspend fun getPostById(@Path("postid") postid: Long): CategoryItemResDTO
}