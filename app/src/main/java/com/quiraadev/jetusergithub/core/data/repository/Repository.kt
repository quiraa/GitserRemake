package com.quiraadev.jetusergithub.core.data.repository

import com.quiraadev.jetusergithub.core.data.local.FavoriteDao
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val dao: FavoriteDao
) {
    suspend fun deleteAllFavorite() = dao.deleteAllFavoriteUsers()
    fun getAllFavorite() = dao.getAllFavorites()
    suspend fun insertFavorite(favorite: Favorite) = dao.insertFavorite(favorite)
    suspend fun deleteFavorite(favorite: Favorite) = dao.deleteFavorite(favorite)
}