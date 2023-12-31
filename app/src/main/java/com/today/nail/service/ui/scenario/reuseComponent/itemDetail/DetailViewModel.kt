package com.today.nail.service.ui.scenario.reuseComponent.itemDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.home.ContentItem
import com.today.nail.service.data.home.dto.categoryItem.PostDTO
import com.today.nail.service.data.home.dto.shop.Shop
import com.today.nail.service.data.home.repository.HomeRepository
import com.today.nail.service.data.home.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigInteger
import java.sql.Blob
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(): ViewModel() {

    val repository : HomeRepository = HomeRepositoryImpl(ServiceConnector.makeHomeService())

    var currentShopId: Long = 1
    var currentPostId: Long = 1
    var currentContent: String = ""
    var currentName: String = ""
    var currentimageUrl: String = ""
    var currentLikeCount: Int = 0
    var currentPrice: Int = 1

    var shopName: String = ""
    var shopPhoneNumber: String = ""
    var shopLocation: String = ""
    var shopOperationHours:String = ""
    var shopLikesCount: Int = 1
    var shopImgUrl: String = ""
    var shopBasePrice: Int = 1
    var shopInfo: String = ""

    /**
     * postId 를 받아 해당 post 정보를 가져오는 함수
     */
    fun getPost(
        onSuccess: () -> Unit,
        onFail : () -> Unit,
        postId: Long,
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getPostById(postId)
            }.onSuccess { response ->
                Log.d("postById", "post response : $response")
                currentName = response.data.name
                currentimageUrl = response.data.imgUrl
                currentPrice = response.data.price
                currentLikeCount = response.data.likeCount

                getShop(response.data.shopId.toBigInteger())

                onSuccess()
            }.onFailure {res ->
                Log.d("postByid", "$res")
                onFail()
            }
        }
    }

    /**
     * shopId 를 받아 단일 가게 저보를 받아오는 함수
     */
    fun getShop(
        shopId: BigInteger,
    ) {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getShopInfoById(shopId)
            }.onSuccess { response ->
                Log.d("shopById", "shop response : $response")
                shopName = response.data.name
                Log.d("shopById", "shop response : $shopName")
                shopPhoneNumber = response.data.phoneNumber
                shopLocation = response.data.location
                shopOperationHours = response.data.operatingHours
                shopInfo = response.data.shopInfo
//                shopLikesCount = response.data.likesCount
            }.onFailure {res ->
                Log.d("shopById", "failed response : $res ")
            }
        }
    }
}