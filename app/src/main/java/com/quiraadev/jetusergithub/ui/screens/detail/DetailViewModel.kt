package com.quiraadev.jetusergithub.ui.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quiraadev.jetusergithub.core.ResultState
import com.quiraadev.jetusergithub.core.data.remote.ApiService
import com.quiraadev.jetusergithub.core.data.remote.response.DetailUserResponse
import com.quiraadev.jetusergithub.core.data.remote.response.ListUserResponse
import com.quiraadev.jetusergithub.utils.errorParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    private val _detailUserState =
        MutableStateFlow<ResultState<DetailUserResponse>>(ResultState.Loading)
    val detailUserState = _detailUserState.asStateFlow()

    private val _detailUserFollowState =
        MutableStateFlow<ResultState<ListUserResponse>>(ResultState.Loading)
    val detailUserFollowState = _detailUserFollowState.asStateFlow()

    private val _detailUserEvent = MutableStateFlow(DetailEvent.OnFollower)

    fun getUserDetail(username: String) {
        _detailUserState.value = ResultState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.fetchUserDetail(username)
                _detailUserState.value = ResultState.Success(response)
            } catch (e: Exception) {
                Log.d("debugDetail", "Error Detail ${e.message} $username")
                _detailUserState.value = ResultState.Error(errorParser(e))
            }
        }
    }

    fun onFollowsEvent(username: String) {
        when (_detailUserEvent.value) {
            DetailEvent.OnFollower -> {
                _detailUserFollowState.value = ResultState.Loading
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = api.fetchUserFollowers(username)
                        _detailUserFollowState.value = ResultState.Success(response)
                    } catch (e: Exception) {
                        _detailUserFollowState.value = ResultState.Error(errorParser(e))
                    }
                }
            }

            DetailEvent.OnFollowing -> {
                _detailUserFollowState.value = ResultState.Loading
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        val response = api.fetchUserFollowing(username)
                        _detailUserFollowState.value = ResultState.Success(response)
                    } catch (e: Exception) {
                        _detailUserFollowState.value = ResultState.Error(errorParser(e))
                    }

                }
            }
        }
    }

    fun setFollowsEvent(value: DetailEvent) {
        _detailUserEvent.value = value
    }
}