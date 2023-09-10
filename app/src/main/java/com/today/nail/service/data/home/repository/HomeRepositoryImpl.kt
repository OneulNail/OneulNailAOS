package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemByIdResDTO
import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.dto.reservation.ReservationReqDTO
import com.today.nail.service.data.home.dto.reservation.ReservationResDTO
import com.today.nail.service.data.home.dto.reservationTime.ShopReservationTimeByIdResDTO
import com.today.nail.service.data.home.dto.shop.ShopInfoByIdResDTO
import com.today.nail.service.data.home.service.HomeService
import com.today.nail.service.ui.TopLevelViewModel
import retrofit2.http.POST
import retrofit2.http.Path
import java.math.BigInteger
import java.time.LocalDateTime

class HomeRepositoryImpl(
    private val homeService: HomeService,
): HomeRepository {
    override suspend fun getPost(): CategoryItemResDTO =
        homeService.getPost()

    override suspend fun getPostById(postId: Long): CategoryItemByIdResDTO =
        homeService.getPostById(postId)

    override suspend fun getShopInfoById(shopId: BigInteger): ShopInfoByIdResDTO =
        homeService.getShopInfoById(shopId)

    override suspend fun getShopReservationTimeById(shopId: Long): ShopReservationTimeByIdResDTO =
        homeService.getShopReservationTimeById(shopId)

    override suspend fun postUserReservation(
        shopId: Long,
        date: LocalDateTime
    ): ReservationResDTO =
        homeService.postUserReservation(
            ReservationReqDTO(
                shopId,date
            )
        )
}