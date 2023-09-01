package com.today.nail.service.ui.scenario.home.view.homeCategoryView

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.today.nail.service.data.ServiceConnector
import com.today.nail.service.data.home.dto.categoryItem.PostDTO
import com.today.nail.service.data.home.repository.HomeRepository
import com.today.nail.service.data.home.repository.HomeRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeCategoryItemVIewModel @Inject constructor(): ViewModel() {

    val repository : HomeRepository = HomeRepositoryImpl(ServiceConnector.makeHomeService())

    private val _postList = mutableStateOf<List<PostDTO>>(emptyList())
    val postList: State<List<PostDTO>> = _postList

    init {
        fetchPosts(
            {},
            {},
        )
    }

    private fun fetchPosts(
        onSuccess: () -> Unit,
        onFail : () -> Unit
    ) {
        viewModelScope.launch {
            runCatching {
                //page...
                repository.getPost(0,10)
            }.onSuccess { response ->
                Log.d("post", "post response : $response")
                _postList.value = response.posts
                onSuccess()
            }.onFailure {
                onFail()
            }
        }
    }

}