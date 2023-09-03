package com.today.nail.service.data.home.dto.shop

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Blob

@Serializable
class ShopInfoByIdResDTO (
    @SerialName("shop_id")
    val shopId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("location")
    val location: String,
    @SerialName("operatingHours")
    val operatingHours: String,
    @SerialName("phone_num")
    val phoneNum: String,
    @SerialName("likesCount")
    val likesCount: Int,
    @SerialName("imgUrl")
    val imgUrl: String,
    @SerialName("basePrice")
    val basePrice: Int,
    @SerialName("shopInfo")
    val shopInfo: String,
)