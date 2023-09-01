package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.dto.reservation.UserReservationResDTO
import java.time.LocalDateTime

interface HomeRepository {

    suspend fun getPost(page: Int, size: Int,) : CategoryItemResDTO

    suspend fun getPostById(postId: Long) : CategoryItemResDTO

    suspend fun userReservation(shopId: Long, date: LocalDateTime): UserReservationResDTO
}