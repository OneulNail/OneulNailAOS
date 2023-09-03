package com.today.nail.service.data.home.dto.shop

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Blob

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class Shop(
    @SerialName("shop_id")
    val shopId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("longitude")
    val longitude: Double,
    @SerialName("latitude")
    val latitude: Double,
    @SerialName("time")
    val time: String,
    @SerialName("phone_num")
    val phoneNum: String,
    @SerialName("like_cnt")
    val likeCnt: Int,
    @SerialName("img_url")
    val imgUrl: Blob,
    @SerialName("price")
    val price: Int,
    @SerialName("shop_info")
    val shopInfo: String,
)

//"shop_id": Long
//"name": String
//"longitude": double
//"latitude": double
//"time": String(운영 시간)
//"phone_num": String
//"like_cnt": int
//"img_url": BLOB
//"price": int
//"shop_info": String