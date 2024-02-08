package com.quiraadev.jetusergithub.ui.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quiraadev.jetusergithub.core.ResultState
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite
import com.quiraadev.jetusergithub.core.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _allFavoriteUser = MutableStateFlow<ResultState<List<Favorite>>>(ResultState.Loading)
    val allFavoriteUser = _allFavoriteUser.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavorite()
                .catch { error -> _allFavoriteUser.value = ResultState.Error(error.localizedMessage) }
                .collect { favorites -> _allFavoriteUser.value = ResultState.Success(favorites)}
        }
    }

    fun deleteAllFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllFavorite()
        }
    }

    fun insertFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavorite(favorite)
        }
    }
}