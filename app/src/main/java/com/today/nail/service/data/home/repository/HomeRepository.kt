package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemByIdResDTO
import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.dto.reservation.ReservationResDTO
import com.today.nail.service.data.home.dto.reservationTime.ShopReservationTimeByIdResDTO
import com.today.nail.service.data.home.dto.shop.ShopInfoByIdResDTO
import java.math.BigInteger
import java.time.LocalDateTime

interface HomeRepository {

    suspend fun getPost() : CategoryItemResDTO

    suspend fun getPostById(postId: Long) : CategoryItemByIdResDTO

    suspend fun getShopInfoById(shopId: BigInteger): ShopInfoByIdResDTO

    suspend fun getShopReservationTimeById(shopId: Long): ShopReservationTimeByIdResDTO

    suspend fun postUserReservation(shopId: Long, date: LocalDateTime): ReservationResDTO
}