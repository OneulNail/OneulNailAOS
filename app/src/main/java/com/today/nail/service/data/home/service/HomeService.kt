package com.today.nail.service.data.home.service

import com.today.nail.service.data.TokenSharedPreferences
import com.today.nail.service.data.home.dto.categoryItem.CategoryItemByIdResDTO
import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.dto.reservation.ReservationReqDTO
import com.today.nail.service.data.home.dto.reservation.ReservationResDTO
import com.today.nail.service.data.home.dto.reservationTime.ShopReservationTimeByIdResDTO
import com.today.nail.service.data.home.dto.shop.ShopInfoByIdResDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.math.BigInteger
import java.time.LocalDateTime

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

    /**
     * 가게별 예약 조회
     */
    @GET("reservation/{shop_id}")
    suspend fun getShopReservationTimeById(@Path("shop_id") shopId: Long): ShopReservationTimeByIdResDTO

    /**
     * 예약 등록
     */
    @POST("reservation")
    suspend fun postUserReservation(
        @Header("Authorization") accessToken: String?,
        @Body requestBody: ReservationReqDTO
    ): ReservationResDTO
}