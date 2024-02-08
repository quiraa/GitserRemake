package com.quiraadev.jetusergithub.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quiraadev.jetusergithub.core.ResultState
import com.quiraadev.jetusergithub.core.data.remote.ApiService
import com.quiraadev.jetusergithub.core.data.remote.response.ListUserResponse
import com.quiraadev.jetusergithub.utils.errorParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    private val _allUserState = MutableStateFlow<ResultState<ListUserResponse>>(ResultState.Loading)
    val allUserState = _allUserState.asStateFlow()

    fun getListUser() {
        _allUserState.value = ResultState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.fetchListUser()
                _allUserState.value = ResultState.Success(response)
            } catch (e: Exception) {
                _allUserState.value = ResultState.Error(errorParser(e))
            }
        }

    }

    fun searchUser(query: String) {
        _allUserState.value = ResultState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.searchUser(query)
                _allUserState.value = ResultState.Success(response.items)
            } catch (e: Exception) {
                _allUserState.value = ResultState.Error(errorParser(e))
            }
        }
    }

    fun setQuery(query: String) {
        _homeState.value = _homeState.value.copy(query = query)
    }
}
