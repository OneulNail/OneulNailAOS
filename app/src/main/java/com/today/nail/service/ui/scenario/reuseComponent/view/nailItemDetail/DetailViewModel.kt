package com.example.oneulnail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.home.dto.categoryItem.PostDTO
import com.today.nail.service.data.home.repository.HomeRepository
import com.today.nail.service.data.home.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(): ViewModel() {

    val repository : HomeRepository = HomeRepositoryImpl(ServiceConnector.makeHomeService())

    private val _postById = MutableStateFlow<List<PostDTO>>(emptyList())
    val postById: StateFlow<List<PostDTO>> = _postById
    val postInfo = postById.value.firstOrNull() // 리스트에서 첫 번째 게시글을 가져옵니다.

    val currentShopId = postInfo?.shopId
    val currentPostId = postInfo?.postId
    val currentContent = postInfo?.content
    val currentName = postInfo?.name
    val currentimageUrl = postInfo?.imageUrl
    val currentLikeCount = postInfo?.likeCount
    val currentPrice = postInfo?.price


    //postId 를 받아 해당 post 를 가져오는 함수
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
                _postById.value = response.posts
                onSuccess()
            }.onFailure {
                onFail()
            }
        }
    }
}