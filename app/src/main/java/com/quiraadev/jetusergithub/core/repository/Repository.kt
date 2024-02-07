package com.quiraadev.jetusergithub.core.repository

import com.quiraadev.jetusergithub.core.data.local.FavoriteDao
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite
import com.quiraadev.jetusergithub.core.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val dao: FavoriteDao
) {
    fun getAllFavoriteUser() = dao.getAllFavoriteUsers()
    suspend fun updateFavoriteUser(id: Int, isFavorite: Boolean) = dao.updateFavoriteUser(id, isFavorite)
}
