package com.today.nail.service.data.home.repository

import com.today.nail.service.data.home.dto.categoryItem.CategoryItemByIdResDTO
import com.today.nail.service.data.home.dto.categoryItem.CategoryItemResDTO
import com.today.nail.service.data.home.dto.shop.ShopInfoByIdResDTO
import java.math.BigInteger

interface HomeRepository {

    suspend fun getPost() : CategoryItemResDTO

    suspend fun getPostById(postId: Long) : CategoryItemByIdResDTO

    suspend fun getShopInfoById(shopId: BigInteger): ShopInfoByIdResDTO
}