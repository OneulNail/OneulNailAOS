package com.today.nail.service.data.home.service

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.dto.reservation.UserReservaitonReqDTO
import com.today.nail.service.data.home.dto.reservation.UserReservationResDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeService {

    /**
     *  게시글 전체 조회
     */
    @GET("post")
    suspend fun getPost(
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : CategoryItemResDTO

    /**
     *  게시글 단일 조회
     */
    @GET("post/{postid}")
    suspend fun getPostById(
        @Path("postid") postid: Long
    ): CategoryItemResDTO

    /**
     *  가게 조회
     */
    @GET("post")
    suspend fun getShops(
        @Query("page") page: Int,
        @Query("size") size: Int
    )

    /**
     *  가게 예약
     */
    @POST("reservation")
    suspend fun UserReservation(
        @Body requestBody : UserReservaitonReqDTO
    ): UserReservationResDTO

}