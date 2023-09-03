package com.today.nail.service.data.home.service

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemByIdResDTO
import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.dto.shop.ShopInfoByIdResDTO
import retrofit2.http.GET
import retrofit2.http.Path
import java.math.BigInteger

interface HomeService {

    /**
     *  게시글 전체 조회
     */
    @GET("post")
    suspend fun getPost() : CategoryItemResDTO

    /**
     *  게시글 단일 조회
     */
    @GET("post/{post_id}")
    suspend fun getPostById(@Path("post_id") postId: Long): CategoryItemByIdResDTO

    /**
     * 가게 정보 단일 조회
     */
    @GET("shop/{shop_id}")
    suspend fun getShopInfoById(@Path("shop_id") shopId: BigInteger): ShopInfoByIdResDTO
}