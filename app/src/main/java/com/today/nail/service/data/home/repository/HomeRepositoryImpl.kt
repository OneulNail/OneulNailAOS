package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.dto.reservation.UserReservaitonReqDTO
import com.today.nail.service.data.home.dto.reservation.UserReservationResDTO
import com.today.nail.service.data.home.service.HomeService
import java.time.LocalDateTime

class HomeRepositoryImpl(
    private val homeService: HomeService,
): HomeRepository {
    override suspend fun getPost(page: Int, size: Int): CategoryItemResDTO =
        homeService.getPost(page = page, size = size)

    override suspend fun getPostById(postId: Long): CategoryItemResDTO =
        homeService.getPostById(postId)

    override suspend fun getShopInfoById(shopId: BigInteger): ShopInfoByIdResDTO =
        homeService.getShopInfoById(shopId)

    override suspend fun userReservation(
        shopId: Long,
        date: LocalDateTime
    ): UserReservationResDTO = homeService.UserReservation(
        UserReservaitonReqDTO(
            shopId = shopId,
            date = date,
        )
    )
}