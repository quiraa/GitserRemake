package com.quiraadev.jetusergithub.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favorite")
data class Favorite(
    @PrimaryKey
    val id: Int,
    val avatarUrl: String,
    val login: String
)