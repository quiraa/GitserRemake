package com.quiraadev.jetusergithub.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE isFavorite = 1 ORDER BY login ASC")
    fun getAllFavoriteUsers() : Flow<List<Favorite>>

    @Query("UPDATE favorite SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteUser(id: Int, isFavorite: Boolean)
}