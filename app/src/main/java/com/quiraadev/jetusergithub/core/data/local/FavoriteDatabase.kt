package com.quiraadev.jetusergithub.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.quiraadev.jetusergithub.core.data.local.entity.Favorite

@Database(entities = [Favorite::class], version = 2, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract val dao: FavoriteDao
}