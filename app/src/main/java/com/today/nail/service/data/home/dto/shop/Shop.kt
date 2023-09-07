package com.today.nail.service.data.home.dto.shop

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Blob
//
//"id": 1,
//"name": "ShopName",
//"phoneNumber": "0123456789",
//"location": "ShopLocation",
//"operatingHours": "9am-5pm",
//"likesCount": 0,
//"imgUrl": "https://i.pinimg.com/564x/78/3c/6d/783c6dd1fe0a04cba1b6e3927e8b0804.jpg",
//"basePrice": 10000,
//"shopInfo": "This is a shop information."
//

//"result":
// {
// "id":3,
// "name":"수유네일",
// "phoneNumber":"01064419916",
// "location":"서울특별시 강북구  ",
// "operatingHours":"10am-7pm",
// "likesCount":0,
// "imgUrl":"https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-19/83913566_308579786765494_4158693691709456384_n.jpg?stp=dst-jpg_s160x160&_nc_cat=105&ccb=1-7&_nc_sid=8ae9d6&_nc_ohc=O1tsbRFJ-vkAX9sMuQX&_nc_ht=scontent-ssn1-1.cdninstagram.com&oh=00_AfC6cPdT6PVSv6W12Vur_Enndcmrbd5-GrN1r-onj-dv5A&oe=64F93CE0",
// "basePrice":40000,
// "shopInfo":"수유속눈썹 강북토탈뷰티샵 :: 네일의기준 #네일 #패디 #속눈썹펌 #속눈썹연장 #발각질 #내성발톱 #문제성발톱 #손톱연장전문 #네일창업반"}
// }
@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class Shop(
    val Id: Long,
    val name: String,
    val phoneNumber: String,
    val location: String,
    val operatingHours: String,
    val likesCount: Int,
    val imgUrl: String,
    val basePrice: Int,
    val shopInfo: String,
)
