package com.quiraadev.jetusergithub.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("DELETE FROM favorite")
    suspend fun deleteAllFavoriteUsers()

    @Query("SELECT * FROM favorite ORDER BY login ASC")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Upsert
    suspend fun upsertFavorite(favorites: List<Favorite>)

    @Insert
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}